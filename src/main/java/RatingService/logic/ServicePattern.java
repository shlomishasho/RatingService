package RatingService.logic;

import RatingService.data.Review;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ServicePattern {

    Flux<Review> getAllReviewsByTimeTo(Date filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByTimeFrom(Date filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByMinRating(int filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByMaxRating(int filterValue, String sortBy, String value);
    Flux<Review> getAllReviews(String sortBy, String value);
}
