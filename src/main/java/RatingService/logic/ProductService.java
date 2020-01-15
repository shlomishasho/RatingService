package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;

@Service
public class ProductService implements ServicePattern{
    private ReviewCrud reviewCrud;

    @Autowired
    public ProductService(ReviewCrud reviewCrud) {
        this.reviewCrud = reviewCrud;
    }

    @Override
    public Flux<Review> getAllReviewsByTimeTo(Date filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndReviewTimestampBefore(productId,filterValue, Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByTimeFrom(Date filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndReviewTimestampAfter(productId,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMinRating(int filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndRatingGreaterThanEqual(productId,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMaxRating(int filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndRatingLessThanEqual(productId,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviews(String sortBy, String productId) {
        return this.reviewCrud.findAllByProductId(productId,Sort.by(Sort.Direction.ASC,sortBy));
    }

}
