package coderefactory.net.popmovies;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import coderefactory.net.popmovies.data.Movie;
import coderefactory.net.popmovies.data.MovieAdapter;

public class MovieListFragment extends Fragment implements MovieTargetFragment, AdapterView.OnItemClickListener {

    private static final String TAG = MovieListFragment.class.getSimpleName();

    private MovieAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        final GridView moviesListView = (GridView) fragmentView.findViewById(R.id.grid_view_movies);
        movieAdapter = new MovieAdapter(fragmentView.getContext(), new ArrayList<Movie>());
        moviesListView.setAdapter(movieAdapter);
        moviesListView.setOnItemClickListener(this);

        return fragmentView;
    }

    @Override
    public void clearMovieList() {
        Log.d(TAG, "clearMovieList");
        movieAdapter.clear();

        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateMovieList(final List<Movie> movies) {
        Log.d(TAG, "updateMovieList");
        movieAdapter.clear();
        movieAdapter.addAll(movies);

        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, int position, final long id) {
        Log.d(TAG, "onItemClick");
        final Movie selected = movieAdapter.getItem(position);

        final Intent detailIntent = new Intent(this.getActivity(), DetailActivity.class);
        detailIntent.putExtra(Movie.TAG, selected);

        startActivity(detailIntent);
    }
}
