package RatingService.logic;

import RatingService.data.FilterTypes;
import RatingService.data.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {
    Mono<Review> create(Review team);

    Flux<Review> getReviewsByProductId(String productId);
    ReviewService validateFilterArgs(FilterTypes filterType, String filterValue, String sortAttr);

    Flux<Review> getReviewsByProductAndFilter(FilterTypes filterType, String filterValue, String sortBy, String productId);

    Flux<Review> getReviewsBetweenRating(FilterTypes filterType, String filterValue, String sortBy, String minRatingInclusive, String maxRatingInclusice);

    Flux<Review> getReviewsByEmailAndFilter(FilterTypes filterType, String filterValue, String sortBy, String email);
    Mono<Void> cleanup();
}
