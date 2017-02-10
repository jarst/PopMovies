package coderefactory.net.popmovies.data;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import coderefactory.net.popmovies.R;
import coderefactory.net.popmovies.settings.SortOrder;

public class MovieProvider {

    private static final String TAG = MovieProvider.class.getSimpleName();

    private final String baseUrl;

    private final String paramApiKey;
    private final String apiKey;

    private final String paramSortBy;
    private final String sortByPopularity;
    private final String sortByRating;

    private final String posterBaseUrl;

    private final String jsonResults;
    private final String jsonTitle;
    private final String jsonReleased;
    private final String jsonPoster;
    private final String jsonRating;
    private final String jsonPlot;

    public MovieProvider(final Context context) {
        baseUrl = context.getString(R.string.api_base_url);

        paramApiKey = context.getString(R.string.param_api_key);
        apiKey = context.getString(R.string.api_key_value);

        paramSortBy = context.getString(R.string.param_sort_by);
        sortByPopularity = context.getString(R.string.sort_by_popularity);
        sortByRating = context.getString(R.string.sort_by_rating);
        posterBaseUrl = context.getString(R.string.poster_base_url);

        jsonResults = context.getString(R.string.json_response_results);
        jsonTitle = context.getString(R.string.json_response_title);
        jsonReleased = context.getString(R.string.json_response_release);
        jsonPoster = context.getString(R.string.json_response_poster);
        jsonRating = context.getString(R.string.json_response_rating);
        jsonPlot = context.getString(R.string.json_response_overview);
    }

    @Nullable
    public List<Movie> fetchMovies(final SortOrder order) {
        try {
            final String json = fetchData(buildUrl(order));
            final List<Movie> movies = parseResponse(json);

            return movies;
        } catch (Exception e) {
            Log.e(TAG, "Failed to retrieve movie data", e);
            return null;
        }
    }

    private URL buildUrl(final SortOrder order) {
        final String sortValue = SortOrder.Popularity == order ? sortByPopularity : sortByRating;

        final Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(paramApiKey, apiKey)
                .appendQueryParameter(paramSortBy, sortValue)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String fetchData(final URL url) throws IOException {
        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            final InputStream in = urlConnection.getInputStream();

            final Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            final boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private List<Movie> parseResponse(final String json) throws JSONException {
        final JSONObject rootObject = new JSONObject(json);
        final JSONArray results = rootObject.getJSONArray(this.jsonResults);

        final List<Movie> movies = new ArrayList<>(results.length());

        for (int i = 0; i < results.length(); i += 1) {
            final JSONObject movieObject = results.getJSONObject(i);
            final Movie movie = buildMovie(movieObject);

            movies.add(movie);
        }

        return movies;
    }

    private Movie buildMovie(final JSONObject movieObject) throws JSONException {
        final String originalTitle = movieObject.getString(jsonTitle);
        final String releaseDate = movieObject.getString(jsonReleased);
        final String posterUrl = posterBaseUrl + movieObject.getString(jsonPoster);
        final double rating = movieObject.getDouble(jsonRating);
        final String plot = movieObject.getString(jsonPlot);

        return new Movie(originalTitle, releaseDate, posterUrl, rating, plot);
    }

}
