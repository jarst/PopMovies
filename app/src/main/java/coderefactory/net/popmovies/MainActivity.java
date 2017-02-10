package coderefactory.net.popmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListFragment = (MovieListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_movie_list);
        movieListFragment.updateMovieList(new ArrayList<Movie>());

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        fetchMovies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_refresh:
                Log.d(TAG, "actionRefresh");
                movieListFragment.updateMovieList(Collections.<Movie>emptyList());
                fetchMovies();
                return true;
            case R.id.action_settings:
                Log.d(TAG, "actionSettings");
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMovies() {
        Log.d(TAG, "fetchMovies");
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
            final Settings.SortOrder sortOder = Settings.getSortOder(MainActivity.this);
            return movieProvider.fetchMovies(sortOder);
        }

        @Override
        protected void onPostExecute(final List<Movie> movies) {
            if (movies == null) {
                Log.d(TAG, "fetchMovies failed");
                Toast.makeText(MainActivity.this, "Error while retrieving movies...", Toast.LENGTH_LONG).show();
                movieListFragment.clearMovieList();
            } else {
                Log.d(TAG, "fetchMovies success");
                movieListFragment.updateMovieList(movies);
            }

        }
    }
}
