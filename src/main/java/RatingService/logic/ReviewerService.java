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
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampBefore(email,filterValue, Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByTimeFrom(String filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampAfter(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMinRating(int filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingGreaterThanEqual(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMaxRating(int filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingLessThanEqual(email,filterValue,Sort.by(sortBy));
    }

    @Override
    public Flux<Review> getAllReviews(String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmail(email,Sort.by(sortBy));
    }


}
