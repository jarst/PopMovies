package coderefactory.net.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderefactory.net.popmovies.data.Movie;


public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    // Not thread-safe, but should be ok here
    private static final DecimalFormat RATING_FORMAT = new DecimalFormat("#.#");

    @BindView(R.id.movie_title)
    TextView titleView;

    @BindView(R.id.movie_poster)
    ImageView posterView;

    @BindView(R.id.movie_released)
    TextView releasedView;

    @BindView(R.id.movie_rating)
    TextView ratingView;

    @BindView(R.id.movie_plot)
    TextView plotView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        final Intent detailIntent = getIntent();

        if (detailIntent != null && detailIntent.hasExtra(Movie.TAG)) {
            final Movie movie = detailIntent.getParcelableExtra(Movie.TAG);
            displayDetails(movie);
        } else {
            Log.e(TAG, "Expected intent with movie details, but got nothing");
        }
    }

    private void displayDetails(final Movie movie) {
        Log.d(TAG, "displayDetails");

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
