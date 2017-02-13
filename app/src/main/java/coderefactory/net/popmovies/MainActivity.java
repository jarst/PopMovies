package coderefactory.net.popmovies;

import android.content.Intent;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import coderefactory.net.popmovies.data.FetchMovieTask;
import coderefactory.net.popmovies.data.Movie;
import coderefactory.net.popmovies.data.MovieProvider;
import coderefactory.net.popmovies.data.NetworkUtil;


public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private NetworkUtil networkUtil;

    private MovieProvider movieProvider;
    private MovieTargetFragment movieTargetFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkUtil = new NetworkUtil(this);

        movieProvider = new MovieProvider(this);

        movieTargetFragment = (MovieListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_movie_list);
        movieTargetFragment.updateMovieList(new ArrayList<Movie>());

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
                movieTargetFragment.clearMovieList();
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
        if (networkUtil.isNetworkAvailable()) {
            new FetchMovieTask(this, movieProvider, movieTargetFragment).execute();
        } else {
            Log.d(TAG, "Network is not available");
            Toast.makeText(MainActivity.this, getString(R.string.message_no_network), Toast.LENGTH_LONG).show();
        }
    }

}
