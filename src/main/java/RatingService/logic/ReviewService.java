package RatingService.logic;

import RatingService.data.FilterTypes;
import RatingService.data.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;

public interface ReviewService {
    Mono<Review> create(Review team);

    ReviewService validateFilterArgs(FilterTypes filterType, String filterValue, String sortAttr);

    Flux<Review> getReviewsByProductAndFilter(FilterTypes filterType, String filterValue, String sortBy, String productId) throws ParseException;

    Flux<Review> getReviewsBetweenRating(FilterTypes filterType, String filterValue, String sortBy, int minRatingInclusive, int maxRatingInclusice) throws ParseException;

    Flux<Review> getReviewsByEmailAndFilter(FilterTypes filterType, String filterValue, String sortBy, String email) throws ParseException;

    Mono<Void> cleanup();
}
