package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.FilterTypes;
import RatingService.data.Review;
import RatingService.logic.Exceptions.CommonErrors;
import RatingService.logic.Exceptions.FieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService {

    private Validator validator;
    private ProductService productService;
    private ReviewerService reviewerService;
    private ReviewCrud reviewCrud;


    @Autowired
    public ReviewServiceImpl(Validator validator, ReviewCrud reviewCrud,ProductService productService) {
        this.validator = validator;
        this.reviewCrud = reviewCrud;
        this.productService = productService;
    }

    @Override
    public Mono<Review> create(Review review) {
        return this.reviewCrud.save(review);
    }

    @Override
    public Flux<Review> getReviewsByProductAndFilter(FilterTypes filterType, String filterValue, String sortBy, String productId) {
        switch (filterType) {
            case ALL:
                return this.productService.getAllReviews(sortBy,productId);
            case BY_MAX_RATING:
                return this.productService.getAllReviewsByMaxRating(Integer.parseInt(filterValue), sortBy,productId);
            case BY_MIN_RATING:
                return this.productService.getAllReviewsByMinRating(Integer.parseInt(filterValue), sortBy,productId);
            case BY_TIMESTAMP_FROM:
                return this.productService.getAllReviewsByTimeFrom(filterValue, sortBy,productId);
            case BY_TIMESTAMP_TO:
                return this.productService.getAllReviewsByTimeTo(filterValue, sortBy,productId);
            default:
                throw new FieldException(CommonErrors.NULL_FILTER_TYPE_ERR);
        }
    }

    @Override
    public Flux<Review> getReviewsByEmailAndFilter(FilterTypes filterType, String filterValue, String sortBy, String email) {
        switch (filterType) {
            case ALL:
                return this.reviewerService.getAllReviews(sortBy,email);
            case BY_MAX_RATING:
                return this.reviewerService.getAllReviewsByMaxRating(Integer.parseInt(filterValue), sortBy,email);
            case BY_MIN_RATING:
                return this.reviewerService.getAllReviewsByMinRating(Integer.parseInt(filterValue), sortBy,email);
            case BY_TIMESTAMP_FROM:
                return this.reviewerService.getAllReviewsByTimeFrom(filterValue, sortBy,email);
            case BY_TIMESTAMP_TO:
                return this.reviewerService.getAllReviewsByTimeTo(filterValue, sortBy,email);
            default:
                throw new FieldException(CommonErrors.NULL_FILTER_TYPE_ERR);
        }
    }

    @Override
    public Flux<Review> getReviewsBetweenRating(FilterTypes filterType, String filterValue, String sortBy, int minRatingInclusive, int maxRatingInclusice) {
        switch (filterType) {
            case BY_TIMESTAMP_FROM:
                return this.reviewCrud.findAllByRatingBetweenAndReviewTimestampBefore(minRatingInclusive,maxRatingInclusice,filterValue, Sort.by(sortBy));
            case BY_TIMESTAMP_TO:
                return this.reviewCrud.findAllByRatingBetweenAndReviewTimestampAfter(minRatingInclusive,maxRatingInclusice,filterValue, Sort.by(sortBy));
            default:
                return this.reviewCrud.findAllByRatingBetween(minRatingInclusive,maxRatingInclusice, Sort.by(sortBy));
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
