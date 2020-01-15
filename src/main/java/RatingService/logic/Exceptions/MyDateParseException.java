package RatingService.logic.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MyDateParseException extends RuntimeException {

    public MyDateParseException(String timeStr) {
        super(timeStr + " not in the required format," +
                "please send it by this format : yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }
}
