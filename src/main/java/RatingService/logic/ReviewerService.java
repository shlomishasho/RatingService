package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.Review;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ReviewerService implements ServicePattern {
    private ReviewCrud reviewCrud;


    @Override
    public Flux<Review> getAllReviewsByTimeTo(String filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampLessThan(email,filterValue, Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByTimeFrom(String filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampGreaterThan(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMinRating(String filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingGreaterThan(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMaxRating(String filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingLessThan(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviews(String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmail(email,Sort.by(sortBy));
    }


}
