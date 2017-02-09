package coderefactory.net.popmovies;

public class Movie {

    private final String title;
    private final String released;

    public Movie(final String title, final String released) {
        this.title = title;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }
}
