package RatingService.logic;

import RatingService.data.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewServiceImpl extends ReactiveMongoRepository<Review,String> {
}
