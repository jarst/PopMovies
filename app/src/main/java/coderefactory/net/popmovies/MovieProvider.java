package coderefactory.net.popmovies;

import android.content.Context;
import android.net.Uri;

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

public class MovieProvider {

    private String BASE_URL = "https://api.themoviedb.org/3/discover/movie";

    private String paramApiKey = "api_key";
    private String apiKey =  null;

    private String paramSortBy = "sort_by";
    private String sortByPopularity = "popularity.desc";

    private String posterUrlPrefix = "http://image.tmdb.org/t/p/w185";

    public MovieProvider(final Context context) {
        apiKey = context.getString(R.string.api_key);
    }

    public List<Movie> fetchMovies() {
        try {
            final String json = fetchData(buildUrl());
            final List<Movie> movies = parseResponse(json);

            return movies;
        } catch (Exception e) {
            return null;
        }
    }

    private URL buildUrl() {
        final Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(paramApiKey, apiKey)
                .appendQueryParameter(paramSortBy, sortByPopularity)
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
        final JSONArray results = rootObject.getJSONArray("results");

        final List<Movie> movies = new ArrayList<>(results.length());

        for (int i = 0; i < results.length(); i += 1) {
            final JSONObject movieObject = results.getJSONObject(i);
            final Movie movie = buildMovie(movieObject);

            movies.add(movie);
        }

        return movies;
    }

    private Movie buildMovie(final JSONObject movieObject) throws JSONException {
        final String originalTitle = movieObject.getString("original_title");
        final String releaseDate = movieObject.getString("release_date");
        final String posterUrl = posterUrlPrefix + movieObject.getString("poster_path");

        return new Movie(originalTitle, releaseDate, posterUrl);
    }


}
