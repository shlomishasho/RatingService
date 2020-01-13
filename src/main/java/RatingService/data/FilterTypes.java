package RatingService.data;

public enum FilterTypes {
    BY_MIN_RATING("byMinRating"),
    BY_MAX_RATING("byMaxRating"),
    BY_TIMESTAMP_FROM("byTimestampFrom"),
    BY_TIMESTAMP_TO("byTimestampTo"),
    ALL("all");

    private String typeString;

    FilterTypes(String typeString){
        this.typeString = typeString;
    }

    public static FilterTypes getByStr(String name){
        for (FilterTypes filter:FilterTypes.values()) {
            if(filter.typeString.equals(name))
                return filter;
        }
        return null;
    }
}
