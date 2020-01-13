package RatingService.layout;

import RatingService.data.FilterTypes;
import RatingService.data.Review;
import RatingService.logic.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Review> create (@RequestBody Review team){
        return this.reviewService
                .create(team);
    }


    @RequestMapping(path = "/reviews/byProduct/{productId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Review> getReviewsByProductId(
            @PathVariable("productId") String productId,
            @RequestParam(name = "filterType", required = false, defaultValue = "all") FilterTypes filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsByProductAndFilter(filterType,filterValue,sortBy,productId);
    }


    @RequestMapping(path = "/reviews/byReviewer/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Review> getReviewsByReviwerEmail(
            @PathVariable("email") String email,
            @RequestParam(name = "filterType", required = false, defaultValue = "all") FilterTypes filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy){
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsByEmailAndFilter(filterType,filterValue,sortBy,email);
    }

    @RequestMapping(path = "/reviews/byRatingBetween/{minRatingInclusive}/{maxRatingInclusice}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Review> getReviewsBetweenRating(
            @PathVariable("minRatingInclusive") String minRatingInclusive,
            @PathVariable("maxRatingInclusice") String maxRatingInclusice,
            @RequestParam(name = "filterType", required = false, defaultValue = "all") FilterTypes filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy){
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsBetweenRating(filterType,filterValue,sortBy,minRatingInclusive,maxRatingInclusice);
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.DELETE)
    public Mono<Void> cleanup(){
        return this.reviewService
                .cleanup();
    }







}













