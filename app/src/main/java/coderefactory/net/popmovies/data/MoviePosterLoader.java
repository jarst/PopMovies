package coderefactory.net.popmovies.data;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import coderefactory.net.popmovies.R;

public class MoviePosterLoader {

    public static void load(@NonNull final Movie movie, @NonNull final ImageView targetView) {
        final Picasso picasso = Picasso.with(targetView.getContext());

        final RequestCreator requestCreator;
        if (movie.hasPosterUrl()) {
            requestCreator = picasso.load(movie.getPosterUrl());
        } else {
            requestCreator = picasso.load(R.drawable.poster_missing);
        }
        //TODO Without resizing to fixed dimensions, placeholder in GridView appears bigger than posters
        requestCreator.resize(185, 277)
                .placeholder(R.drawable.poster_placeholder)
                .into(targetView);
    }

}
