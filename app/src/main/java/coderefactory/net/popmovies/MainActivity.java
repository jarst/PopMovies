package coderefactory.net.popmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Movie> movies = Arrays.asList(
            new Movie("The Godfather", 1972),
            new Movie("The Shawshank Redemption", 1994),
            new Movie("Casablanca", 1942)
        );


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMovieList();
    }

    private void populateMovieList() {
        final ListView lvMovies = (ListView) findViewById(R.id.list_view_movies);
        final MovieAdapter moviewAdapter = new MovieAdapter(this, movies);
        lvMovies.setAdapter(moviewAdapter);
    }
}
