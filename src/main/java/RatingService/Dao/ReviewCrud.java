package RatingService.Dao;

import RatingService.data.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ReviewCrud extends ReactiveMongoRepository<Review, String> {

    //TODO: We maybe need to change the review timestamp type to Date instead of String
    // As you can see on the methods below.

    //By Product id
    Flux<Review> findAllByProductId(String productId, Sort by);

    Flux<Review> findAllByProductIdAndRatingLessThanEqual(String productId, int rating, Sort by);

    Flux<Review> findAllByProductIdAndRatingGreaterThanEqual(String productId, int rating, Sort by);

    Flux<Review> findAllByProductIdAndReviewTimestampBefore(String productId, Date reviewTimestmap, Sort by);

    Flux<Review> findAllByProductIdAndReviewTimestampAfter(String productId, Date reviewTimestmap, Sort by);

    //By Customer email
    Flux<Review> findAllByCustomerEmail(String email, Sort by);

    Flux<Review> findAllByCustomerEmailAndRatingLessThanEqual(String email, int rating, Sort by);

    Flux<Review> findAllByCustomerEmailAndRatingGreaterThanEqual(String email, int rating, Sort by);

    Flux<Review> findAllByCustomerEmailAndReviewTimestampBefore(String email, Date reviewTimestmap, Sort by);

    Flux<Review> findAllByCustomerEmailAndReviewTimestampAfter(String email, Date reviewTimestmap, Sort by);

    //Between rating
    Flux<Review> findAllByRatingBetween(int minRatingInclusive, int maxRatingInclusive, Sort sortBy);

    Flux<Review> findAllByRatingBetweenAndReviewTimestampBefore(int minRatingInclusive, int maxRatingInclusive, Date reviewTimestmap, Sort sortBy);

    Flux<Review> findAllByRatingBetweenAndReviewTimestampAfter(int minRatingInclusive, int maxRatingInclusive, Date reviewTimestmap, Sort sortBy);

//    Flux<Review> findAllByRatingBetweenInclusive(int minRatingInclusive, int maxRatingInclusice, Sort by);
}



