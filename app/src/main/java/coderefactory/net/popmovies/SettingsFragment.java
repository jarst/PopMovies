package coderefactory.net.popmovies;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;


public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Log.d(TAG, "onCreatePreferences");
        addPreferencesFromResource(R.xml.preferences);

        setPreferenceSummaries();
    }

    private void setPreferenceSummaries() {
        final PreferenceScreen preferenceScreen = getPreferenceScreen();
        final SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        final int count = preferenceScreen.getPreferenceCount();
        for (int index = 0; index < count; index += 1) {
            final Preference preference = preferenceScreen.getPreference(index);
            if (preference instanceof ListPreference) {
                final String value = sharedPreferences.getString(preference.getKey(), "");
                setListPreferenceSummary((ListPreference) preference, value);
            }
        }
    }

    private void setListPreferenceSummary(final ListPreference listPreference, final String value) {
        final int index = listPreference.findIndexOfValue(value);
        if (index != -1) {
            final CharSequence valueLabel = listPreference.getEntries()[index];
            listPreference.setSummary(valueLabel);
        }
    }
}
