package coderefactory.net.popmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

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

    protected Movie(final Parcel in) {
        title = in.readString();
        released = in.readString();
        posterUrl = in.readString();
        rating = in.readDouble();
        plot = in.readString();
    }

    @Override
    public void writeToParcel(final Parcel out, final  int flags) {
        out.writeString(title);
        out.writeString(released);
        out.writeString(posterUrl);
        out.writeDouble(rating);
        out.writeString(plot);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(final Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(final int size) {
            return new Movie[size];
        }
    };

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

    public boolean hasPosterUrl() {
        return posterUrl != null && !posterUrl.trim().equals("");
    }
}
