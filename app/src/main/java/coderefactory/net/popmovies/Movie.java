package coderefactory.net.popmovies;

public class Movie {

    private final String title;
    private final int released;

    public Movie(final String title, final int released) {
        this.title = title;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }
}
