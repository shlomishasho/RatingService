package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductService implements ServicePattern{
    private ReviewCrud reviewCrud;

    @Autowired
    public ProductService(ReviewCrud reviewCrud) {
        this.reviewCrud = reviewCrud;
    }

    @Override
    public Flux<Review> getAllReviewsByTimeTo(String filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndReviewTimestampLessThan(productId,filterValue, Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByTimeFrom(String filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndReviewTimestampGreaterThan(productId,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMinRating(String filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndRatingGreaterThan(productId,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMaxRating(String filterValue, String sortBy, String productId) {
        return this.reviewCrud.findAllByProductIdAndRatingLessThan(productId,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviews(String sortBy, String productId) {
        return this.reviewCrud.findAllByProductId(productId,Sort.by(sortBy));
    }

}
