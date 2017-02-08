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

    private ViewHolder viewHolder;

    public MovieAdapter(final Activity context, final List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View itemView;
        if (convertView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
            viewHolder = new ViewHolder(itemView);
            itemView.setTag(viewHolder);
        } else {
            itemView = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        populateView(position);

        return itemView;
    }

    private void populateView(final int position) {
        final Movie movie = getItem(position);

        viewHolder.titleView.setText(movie.getTitle());
        viewHolder.releaseView.setText(String.valueOf(movie.getReleased()));
    }

    private static class ViewHolder {
        private final TextView titleView;
        private final TextView releaseView;

        private ViewHolder(final View itemView) {
            titleView = (TextView) itemView.findViewById(R.id.movie_title);
            releaseView = (TextView) itemView.findViewById(R.id.movie_released);
        }
    }
}
