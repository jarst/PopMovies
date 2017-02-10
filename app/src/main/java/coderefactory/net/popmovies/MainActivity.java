package coderefactory.net.popmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import java.util.List;

import coderefactory.net.popmovies.data.Movie;
import coderefactory.net.popmovies.data.MovieProvider;
import coderefactory.net.popmovies.settings.Settings;
import coderefactory.net.popmovies.settings.SortOrder;


public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MovieProvider movieProvider;
    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieProvider = new MovieProvider(this);

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
                movieListFragment.clearMovieList();
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
        if (isNetworkAvailable()) {
            new FetchMovieTask().execute();
        } else {
            Log.d(TAG, "Network is not available");
            Toast.makeText(MainActivity.this, getString(R.string.message_no_network), Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {
        FetchMovieTask() {
            super();
        }

        @Override
        protected List<Movie> doInBackground(final String... params) {
            final SortOrder sortOder = Settings.getSortOder(MainActivity.this);
            return movieProvider.fetchMovies(sortOder);
        }

        @Override
        protected void onPostExecute(final List<Movie> movies) {
            if (movies == null) {
                Log.d(TAG, "fetchMovies failed");
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.message_api_error),
                        Toast.LENGTH_LONG).show();
                movieListFragment.clearMovieList();
            } else {
                Log.d(TAG, "fetchMovies success");
                movieListFragment.updateMovieList(movies);
            }

        }
    }
}
