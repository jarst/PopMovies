package coderefactory.net.popmovies;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


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

    class FetchMovieTask extends AsyncTask<String, Void, String> {

        FetchMovieTask() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            movieListFragment.updateMovieList(TestData.threeMovies);
        }
    }
}
