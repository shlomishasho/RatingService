package RatingService.logic;

import RatingService.data.Review;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;

public interface ServicePattern {

    Flux<Review> getAllReviewsByTimeTo(String filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByTimeFrom(String filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByMinRating(String filterValue, String sortBy, String value);
    Flux<Review> getAllReviewsByMaxRating(String filterValue, String sortBy, String value);
    Flux<Review> getAllReviews(String sortBy, String value);
}
