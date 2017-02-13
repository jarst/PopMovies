package coderefactory.net.popmovies.settings;

public class SortOrder {

    public static final byte POPULARITY = (byte) 1;
    public static final byte RATING = (byte) 2;

    private SortOrder () {}

    public static boolean isByPopularity(final byte order) {
        return order == POPULARITY;
    }
}
