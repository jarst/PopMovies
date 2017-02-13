package coderefactory.net.popmovies;

import java.util.List;

import coderefactory.net.popmovies.data.Movie;

public interface MovieTargetFragment {

    void clearMovieList();

    void updateMovieList(List<Movie> movies);
}
