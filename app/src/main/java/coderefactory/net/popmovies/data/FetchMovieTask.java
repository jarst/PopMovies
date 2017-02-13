package coderefactory.net.popmovies.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import coderefactory.net.popmovies.MovieTargetFragment;
import coderefactory.net.popmovies.R;
import coderefactory.net.popmovies.settings.Settings;
import coderefactory.net.popmovies.settings.SortOrder;

public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

    private static final String TAG = FetchMovieTask.class.getSimpleName();

    private final Context context;
    private final MovieProvider movieProvider;
    private final MovieTargetFragment targetFragment;

    public FetchMovieTask(final Context context, final MovieProvider movieProvider, final MovieTargetFragment targetFragment) {
        this.context = context;
        this.movieProvider = movieProvider;
        this.targetFragment = targetFragment;
    }

    @Override
    protected List<Movie> doInBackground(final String... params) {
        final byte sortOder = Settings.getSortOder(context);
        return movieProvider.fetchMovies(sortOder);
    }

    @Override
    protected void onPostExecute(final List<Movie> movies) {
        if (movies == null) {
            Log.d(TAG, "fetchMovies failed");
            Toast.makeText(context, context.getString(R.string.message_api_error),
                    Toast.LENGTH_LONG).show();
            targetFragment.clearMovieList();
        } else {
            Log.d(TAG, "fetchMovies success");
            targetFragment.updateMovieList(movies);
        }
    }
}