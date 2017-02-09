package coderefactory.net.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final TextView titleView = (TextView) findViewById(R.id.movie_title);

        final Intent detailIntent = getIntent();

        if (detailIntent != null && detailIntent.hasExtra("movie")) {
            final Movie movie = (Movie) detailIntent.getSerializableExtra("movie");

            titleView.setText(movie.getTitle());
        }
    }
}
