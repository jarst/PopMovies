package coderefactory.net.popmovies;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;


public class SettingsFragment extends PreferenceFragmentCompat
    implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(final Bundle savedInstanceState, final String rootKey) {
        Log.d(TAG, "onCreatePreferences");
        addPreferencesFromResource(R.xml.preferences);

        setPreferenceSummaries();
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        Log.d(TAG, "onSharedPreferenceChanged");
        final Preference preference = findPreference(key);
        setPreferenceSummary(sharedPreferences, preference, key);
    }

    private void setPreferenceSummaries() {
        final PreferenceScreen preferenceScreen = getPreferenceScreen();
        final SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        final int count = preferenceScreen.getPreferenceCount();
        for (int index = 0; index < count; index += 1) {
            final Preference preference = preferenceScreen.getPreference(index);
            setPreferenceSummary(sharedPreferences, preference, preference.getKey());
        }
    }

    private void setPreferenceSummary(final SharedPreferences sharedPreferences,
                                      final Preference preference, final String key) {
        if (preference != null && (preference instanceof ListPreference)) {
            final ListPreference listPreference = (ListPreference) preference;
            final String value = sharedPreferences.getString(key, "");
            final int index = listPreference.findIndexOfValue(value);
            if (index != -1) {
                final CharSequence valueLabel = listPreference.getEntries()[index];
                listPreference.setSummary(valueLabel);
            }
        } else {
            Log.e(TAG, "Trying to set summary on unknown preference");
        }
    }


}
