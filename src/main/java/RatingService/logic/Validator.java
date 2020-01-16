package RatingService.logic;

import RatingService.data.EnhancedFilterTypes;
import RatingService.data.FilterTypes;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public Validator() {
    }

    public boolean validate(String str) {
        if (str != null)
            return !str.trim().isEmpty();
        return false;
    }

    public boolean validate(int rating) {
        return  rating >= 1 && rating <=5;
    }

    public boolean validate(FilterTypes filterType) {
        return filterType != null;
    }


    public boolean validate(EnhancedFilterTypes filterType) {
        return filterType != null;
    }
}
