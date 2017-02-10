package coderefactory.net.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import coderefactory.net.popmovies.data.Movie;


public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private static final DecimalFormat RATING_FORMAT = new DecimalFormat("#.#");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent detailIntent = getIntent();

        if (detailIntent != null && detailIntent.hasExtra(Movie.TAG)) {
            final Movie movie = (Movie) detailIntent.getSerializableExtra(Movie.TAG);
            displayDetails(movie);
        }
    }

    private void displayDetails(final Movie movie) {
        Log.d(TAG, "displayDetails");
        final TextView titleView = (TextView) findViewById(R.id.movie_title);
        final ImageView posterView = (ImageView) findViewById(R.id.movie_poster);
        final TextView releasedView = (TextView) findViewById(R.id.movie_released);
        final TextView ratingView = (TextView) findViewById(R.id.movie_rating);
        final TextView plotView = (TextView) findViewById(R.id.movie_plot);

        titleView.setText(movie.getTitle());
        releasedView.setText(movie.getReleased());
        ratingView.setText(RATING_FORMAT.format(movie.getRating()));
        plotView.setText(movie.getPlot());

        if (movie.getPosterUrl() != null) {
            Picasso.with(this)
                    .load(movie.getPosterUrl())
                    .into(posterView);
        }
    }
}
