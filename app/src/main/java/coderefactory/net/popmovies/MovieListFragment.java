package coderefactory.net.popmovies;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MovieListFragment extends Fragment {

    private final List<Movie> movies = Arrays.asList(
            new Movie("The Godfather", 1972),
            new Movie("The Shawshank Redemption", 1994),
            new Movie("Casablanca", 1942)
    );

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        populateMovieList(fragmentView);

        return fragmentView;
    }

    private void populateMovieList(final View fragmentView) {
        final ListView lvMovies = (ListView) fragmentView.findViewById(R.id.list_view_movies);
        final MovieAdapter moviewAdapter = new MovieAdapter(fragmentView.getContext(), movies);
        lvMovies.setAdapter(moviewAdapter);
    }
}
