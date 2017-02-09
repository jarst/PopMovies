package coderefactory.net.popmovies;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {

    private MovieAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        final ListView moviesListView = (ListView) fragmentView.findViewById(R.id.list_view_movies);
        movieAdapter = new MovieAdapter(fragmentView.getContext(), new ArrayList<Movie>());
        moviesListView.setAdapter(movieAdapter);

        return fragmentView;
    }

    public void clearMovieList() {
        movieAdapter.clear();

        movieAdapter.notifyDataSetChanged();
    }

    public void updateMovieList(final List<Movie> movies) {
        movieAdapter.clear();
        movieAdapter.addAll(movies);

        movieAdapter.notifyDataSetChanged();
    }
}
