package RatingService.layout;

import RatingService.data.FilterTypes;
import RatingService.data.Review;
import RatingService.logic.Exceptions.FieldException;
import RatingService.logic.Exceptions.MyDateParseException;
import RatingService.logic.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

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
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) throws ParseException {
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsByProductAndFilter(filterType,filterValue,sortBy,productId);
    }


    @RequestMapping(path = "/reviews/byReviewer/{email}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Review> getReviewsByReviwerEmail(
            @PathVariable("email") String email,
            @RequestParam(name = "filterType", required = false, defaultValue = "all") FilterTypes filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) throws ParseException {
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsByEmailAndFilter(filterType,filterValue,sortBy,email);
    }

    @RequestMapping(path = "/reviews/byRatingBetween/{minRatingInclusive}/{maxRatingInclusive}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Review> getReviewsBetweenRating(
            @PathVariable("minRatingInclusive") int minRatingInclusive,
            @PathVariable("maxRatingInclusive") int maxRatingInclusive,
            @RequestParam(name = "filterType", required = false, defaultValue = "all") FilterTypes filterType,
            @RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) throws ParseException {
        return this.reviewService
                .validateFilterArgs(filterType,filterValue,sortBy)
                .getReviewsBetweenRating(filterType,filterValue,sortBy,minRatingInclusive-1,maxRatingInclusive+1);
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.DELETE)
    public Mono<Void> cleanup(){
        return this.reviewService
                .cleanup();
    }


    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValid (FieldException e){
        String message = e.getMessage();
        if (message == null) {
            message = "Arguments are not valid";
        }
        return Collections.singletonMap("error", message);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleWrongDateTypeFormat(MyDateParseException e){
        String message = e.getMessage();
        if (message == null) {
            message = "Bad type format Found";
        }
        return Collections.singletonMap("error", message);
    }







}













