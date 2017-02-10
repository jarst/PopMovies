package coderefactory.net.popmovies.data;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final String TAG = Movie.class.getSimpleName();

    private final String title;
    private final String released;
    private final String posterUrl;
    private final double rating;
    private final String plot;

    public Movie(final String title, final String released, final String posterUrl,
                 final double rating, final String plot) {
        this.title = title;
        this.released = released;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getRating() {
        return rating;
    }

    public String getPlot() {
        return plot;
    }
}
