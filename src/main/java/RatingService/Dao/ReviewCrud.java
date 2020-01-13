package RatingService.Dao;

import RatingService.data.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ReviewCrud extends ReactiveMongoRepository<Review,String> {

    Flux<Review> findAllByProductId(
            String productId);

    Flux<Review> findAllByProductId(String productId, Sort by);

    Flux<Review> findAllByProductIdAndReviewTimestampLessThan(
            @Param("reviewTimestmap")String reviewTimestmap,
            @Param("product.id") String productId,
            Sort by);

    Flux<Review> findAllByProductIdAndReviewTimestampGreaterThan(String productId, String filterValue, Sort by);

    Flux<Review> findAllByProductIdAndRatingGreaterThan(
            @Param("product.id") String productId,
            @Param("rating") String rating,
            Sort by);

    Flux<Review> findAllByProductIdAndRatingLessThan(String productId, String filterValue, Sort by);


    Flux<Review> findAllByRatingBetweenAndReviewTimestampGreaterThan(String minRatingInclusive, String maxRatingInclusice, String filterValue, Sort sortBy);


    Flux<Review> findAllByRatingBetweenAndReviewTimestampLessThan(String minRatingInclusive, String maxRatingInclusice, String filterValue, Sort sortBy);

    Flux<Review> findAllByRatingBetween(String minRatingInclusive, String maxRatingInclusice, String filterValue, Sort sortBy);

    Flux<Review> findAllByCustomerEmailAndReviewTimestampLessThan(String email, String filterValue, Sort by);

    Flux<Review> findAllByCustomerEmailAndReviewTimestampGreaterThan(String email, String filterValue, Sort by);

    Flux<Review> findAllByCustomerEmailAndRatingLessThan(String email, String filterValue, Sort by);

    Flux<Review> findAllByCustomerEmailAndRatingGreaterThan(String email, String filterValue, Sort by);

    Flux<Review> findAllByCustomerEmail(String email, Sort by);
}
