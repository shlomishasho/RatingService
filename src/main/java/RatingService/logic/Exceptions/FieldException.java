package RatingService.logic.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FieldException extends RuntimeException{
    public FieldException(String message) {
        super(message);
    }
}
