package coderefactory.net.popmovies;

public class Movie {

    private final String title;
    private final String released;
    private final String posterUrl;

    public Movie(final String title, final String released, String posterUrl) {
        this.title = title;
        this.released = released;
        this.posterUrl = posterUrl;
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
}
