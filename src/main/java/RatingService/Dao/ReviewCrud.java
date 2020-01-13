package RatingService.Dao;

import RatingService.data.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewCrud extends ReactiveMongoRepository<Review,String> {
}
