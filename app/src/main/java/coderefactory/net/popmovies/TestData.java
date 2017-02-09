package coderefactory.net.popmovies;

import java.util.Arrays;
import java.util.List;

public class TestData {

    static final List<Movie> oneMovie = Arrays.asList(new Movie("The Godfather", 1972));

    static final List<Movie> threeMovies = Arrays.asList(
        new Movie("The Godfather", 1972),
        new Movie("The Shawshank Redemption", 1994),
        new Movie("Casablanca", 1942)
    );
}
