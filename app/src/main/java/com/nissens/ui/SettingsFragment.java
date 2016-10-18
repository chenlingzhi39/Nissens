package com.nissens.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;



import com.nissens.BuildConfig;
import com.nissens.R;

/**
 * Created by PC-20160514 on 2016/7/2.
 */
public class SettingsFragment extends PreferenceFragment {
    public static SettingsFragment newInstance(){
        SettingsFragment settingsFragment=new SettingsFragment();
        return settingsFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName("settings");
        addPreferencesFromResource(R.xml.settings);
        Preference version=findPreference("version");
        version.setSummary(BuildConfig.VERSION_NAME);
    }

}
