package RatingService.logic;

import RatingService.data.EnhancedFilterTypes;
import RatingService.data.FilterTypes;
import RatingService.data.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;

public interface ReviewService {
    Mono<Review> create(Review team);

    ReviewService validateFilterArgs(FilterTypes filterType, String filterValue, String sortAttr);

    ReviewService validateFilterArgs(EnhancedFilterTypes filterType, String filterValue, String sortAttr);

    Flux<Review> getReviewsByProductAndFilter(EnhancedFilterTypes filterType, String filterValue, String sortBy, String productId) ;

    Flux<Review> getReviewsByEmailAndFilter(EnhancedFilterTypes filterType, String filterValue, String sortBy, String email) ;

    Flux<Review> getReviewsBetweenRating(FilterTypes filterType, String filterValue, String sortBy, int minRatingInclusive, int maxRatingInclusice) ;

    Mono<Void> cleanup();
}
