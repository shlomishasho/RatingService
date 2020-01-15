package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.FilterTypes;
import RatingService.data.Review;
import RatingService.logic.Exceptions.CommonErrors;
import RatingService.logic.Exceptions.FieldException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    private Validator validator;
    private ProductService productService;
    private ReviewerService reviewerService;
    private ReviewCrud reviewCrud;

    public ReviewServiceImpl(Validator validator, ProductService productService, ReviewerService reviewerService, ReviewCrud reviewCrud) {
        this.validator = validator;
        this.productService = productService;
        this.reviewerService = reviewerService;
        this.reviewCrud = reviewCrud;
    }

    @Override
    public Mono<Review> create(Review review) {
        return this.reviewCrud.save(review);
    }

    @Override
    public Flux<Review> getReviewsByProductAndFilter(FilterTypes filterType, String filterValue, String sortBy, String productId)throws ParseException {
        switch (filterType) {
            case ALL:
                return this.productService.getAllReviews(sortBy,productId);
            case BY_MAX_RATING:
                return this.productService.getAllReviewsByMaxRating(Integer.parseInt(filterValue), sortBy,productId);
            case BY_MIN_RATING:
                return this.productService.getAllReviewsByMinRating(Integer.parseInt(filterValue), sortBy,productId);
            case BY_TIMESTAMP_FROM:
                return this.productService.getAllReviewsByTimeFrom(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue), sortBy,productId);
            case BY_TIMESTAMP_TO:
                return this.productService.getAllReviewsByTimeTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue), sortBy,productId);
            default:
                throw new FieldException(CommonErrors.NULL_FILTER_TYPE_ERR);
        }

    }

    @Override
    public Flux<Review> getReviewsByEmailAndFilter(FilterTypes filterType, String filterValue, String sortBy, String email) throws ParseException {
        switch (filterType) {
            case ALL:
                return this.reviewerService.getAllReviews(sortBy,email);
            case BY_MAX_RATING:
                return this.reviewerService.getAllReviewsByMaxRating(Integer.parseInt(filterValue), sortBy,email);
            case BY_MIN_RATING:
                return this.reviewerService.getAllReviewsByMinRating(Integer.parseInt(filterValue), sortBy,email);
            case BY_TIMESTAMP_FROM:
                return this.reviewerService.getAllReviewsByTimeFrom(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue), sortBy,email);
            case BY_TIMESTAMP_TO:
                return this.reviewerService.getAllReviewsByTimeTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue), sortBy,email);
            default:
                throw new FieldException(CommonErrors.NULL_FILTER_TYPE_ERR);
        }
    }

    @Override
    public Flux<Review> getReviewsBetweenRating(FilterTypes filterType, String filterValue, String sortBy, int minRatingInclusive, int maxRatingInclusice) throws ParseException {
        switch (filterType) {
            case BY_TIMESTAMP_FROM:
                return this.reviewCrud.findAllByRatingBetweenAndReviewTimestampBefore
                        (minRatingInclusive,maxRatingInclusice,
                                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue),
                                Sort.by(Sort.Direction.ASC,sortBy));
            case BY_TIMESTAMP_TO:
                return this.reviewCrud.findAllByRatingBetweenAndReviewTimestampAfter(minRatingInclusive,maxRatingInclusice,
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(filterValue),
                        Sort.by(Sort.Direction.ASC,sortBy));
            default:
                return this.reviewCrud.findAllByRatingBetween(minRatingInclusive,maxRatingInclusice, Sort.by(Sort.Direction.ASC,sortBy));
        }
    }

    @Override
    public Mono<Void> cleanup() {
        return reviewCrud.deleteAll();
    }

    @Override
    public ReviewService validateFilterArgs(FilterTypes filterType, String filterValue, String sortAttr) {
        boolean allFieldsValid = validator.validate(filterType)
                && validator.validate(sortAttr);
        if (allFieldsValid) {
            boolean isFilterValueValid = validator.validate(filterValue);
            if (!filterType.equals(FilterTypes.ALL) && !isFilterValueValid)
                throw new FieldException(CommonErrors.FILTER_VALUE_ERR);

            return this;
        }
        throw new FieldException(CommonErrors.FILTER_ARGS_ERR);
    }
}
