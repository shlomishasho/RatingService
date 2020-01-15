package RatingService.logic;

import RatingService.Dao.ReviewCrud;
import RatingService.data.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;

@Service
public class ReviewerService implements ServicePattern {
    private ReviewCrud reviewCrud;

    @Autowired
    public ReviewerService(ReviewCrud reviewCrud) {
        this.reviewCrud = reviewCrud;
    }

    @Override
    public Flux<Review> getAllReviewsByTimeTo(Date filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampBefore(email,filterValue, Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByTimeFrom(Date filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndReviewTimestampAfter(email,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMinRating(int filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingGreaterThanEqual(email,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviewsByMaxRating(int filterValue, String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmailAndRatingLessThanEqual(email,filterValue,Sort.by(Sort.Direction.ASC,sortBy));
    }

    @Override
    public Flux<Review> getAllReviews(String sortBy, String email) {
        return this.reviewCrud.findAllByCustomerEmail(email,Sort.by(Sort.Direction.ASC,sortBy));
    }


}
