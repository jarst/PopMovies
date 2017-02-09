package coderefactory.net.popmovies;

import java.util.Arrays;
import java.util.List;

public class TestData {

    static final List<Movie> oneMovie = Arrays.asList(new Movie("The Godfather", "1972", null));

    static final List<Movie> threeMovies = Arrays.asList(
        new Movie("The Godfather", "1972", null),
        new Movie("The Shawshank Redemption", "1994", null),
        new Movie("Casablanca", "1942", null)
    );
}
