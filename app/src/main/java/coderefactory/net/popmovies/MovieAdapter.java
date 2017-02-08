package coderefactory.net.popmovies;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(final Activity context, final List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View rootView;
        if (convertView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
        } else {
            rootView = convertView;
        }
        final Movie movie = getItem(position);

        final TextView titleView = (TextView) rootView.findViewById(R.id.movie_title);
        titleView.setText(movie.getTitle());

        final TextView releaseView = (TextView) rootView.findViewById(R.id.movie_released);
        releaseView.setText(String.valueOf(movie.getReleased()));

        return rootView;
    }
}
