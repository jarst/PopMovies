package coderefactory.net.popmovies;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListFragment = (MovieListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_movie_list);

        movieListFragment.updateMovieList(TestData.oneMovie);

        fetchMovies();
    }

    private void fetchMovies() {
        new FetchMovieTask().execute();
    }

    class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {
        private MovieProvider movieProvider;

        FetchMovieTask() {
            super();
            movieProvider = new MovieProvider(MainActivity.this);
        }

        @Override
        protected List<Movie> doInBackground(final String... params) {
               return movieProvider.fetchMovies();
        }

        @Override
        protected void onPostExecute(final List<Movie> movies) {
            if (movies == null) {
                Toast.makeText(MainActivity.this, "Error while retrieving movies...", Toast.LENGTH_LONG).show();
            } else {
                movieListFragment.updateMovieList(movies);
            }

        }
    }
}
