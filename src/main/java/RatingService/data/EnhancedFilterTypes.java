package RatingService.data;

public enum EnhancedFilterTypes {

    BY_MIN_RATING("byMinRating"),
    BY_MAX_RATING("byMaxRating"),
    BY_TIMESTAMP_FROM("byTimestampFrom"),
    BY_TIMESTAMP_TO("byTimestampTo"),
    ALL("all");

    private String typeString;

    EnhancedFilterTypes(String typeString){
        this.typeString = typeString;
    }

    public static EnhancedFilterTypes getByStr(String name){
        for (EnhancedFilterTypes filter:EnhancedFilterTypes.values()) {
            if(filter.typeString.equals(name))
                return filter;
        }
        return null;
    }
}
