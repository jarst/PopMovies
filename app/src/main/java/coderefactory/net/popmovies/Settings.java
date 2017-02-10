package coderefactory.net.popmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class Settings {

    public static SortOrder getSortOder(final Context context) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final  String sortValue = sharedPreferences.getString(
                context.getString(R.string.pref_sort_key),
                context.getString(R.string.pref_sort_default_value)
        );

        if (sortValue.equals(context.getString(R.string.pref_sort_value_popularity) ) ) {
            return SortOrder.Popularity;
        } else {
            return SortOrder.Rating;
        }

    }

    public enum SortOrder {
        Popularity,
        Rating
    }
}
