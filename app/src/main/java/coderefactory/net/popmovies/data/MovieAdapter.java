package coderefactory.net.popmovies.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import java.util.List;

import coderefactory.net.popmovies.R;


public class MovieAdapter extends ArrayAdapter<Movie> {

    private ViewHolder viewHolder;

    public MovieAdapter(final Context context, final List<Movie> movies) {
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

        final Movie movie = getItem(position);
        if (movie != null) {
            populateView(movie);
        }

        return itemView;
    }

    private void populateView(@NonNull final Movie movie) {
        MoviePosterLoader.load(movie, viewHolder.imageView);
    }

    private static class ViewHolder {
        private final ImageView imageView;

        private ViewHolder(final View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.movie_poster);
        }
    }
}
