package com.teamcanjica.settings.device.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.teamcanjica.settings.device.DeviceSettings;
import com.teamcanjica.settings.device.R;
import com.teamcanjica.settings.device.Utils;

public class GPUFragmentActivity extends PreferenceFragment {

	private static final String TAG = "GalaxyAce2_Settings_GPU";
	
	public static final String FILE_AUTOBOOST = "/sys/kernel/mali/mali_auto_boost";
	public static final String FILE_FULLSPEED = "/sys/kernel/mali/mali_gpu_fullspeed";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.gpu_preferences);
		// Will add a GPU icon soon 
		getActivity().getActionBar().setTitle(getResources().getString(R.string.gpu_name));
		getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.screen_icon));

		getPreferenceScreen().findPreference(DeviceSettings.KEY_SET_GPU_CLOCK).setEnabled(
				((CheckBoxPreference) findPreference("disable_autoboost")).isChecked());
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		// String boxValue;
		String key = preference.getKey();

		Log.w(TAG, "key: " + key);

		if (key.equals(DeviceSettings.KEY_DISABLE_AUTOBOOST)) {
				Utils.writeValue(FILE_AUTOBOOST, !((CheckBoxPreference) preference)
						.isChecked());
				getPreferenceScreen().findPreference(DeviceSettings.KEY_SET_GPU_CLOCK).setEnabled(
						((CheckBoxPreference) preference).isChecked());
		}

		if (key.equals(DeviceSettings.KEY_DISABLE_FULLSPEED)) {
			Utils.writeValue(FILE_FULLSPEED, !((CheckBoxPreference) preference)
					.isChecked());
		}

		return true;
	}
	
	public static void restore(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		Utils.writeValue(FILE_AUTOBOOST, sharedPrefs.getBoolean(
				DeviceSettings.KEY_DISABLE_AUTOBOOST, false) ? "0" : "1");
		
		Utils.writeValue(FILE_FULLSPEED, sharedPrefs.getBoolean(
				DeviceSettings.KEY_DISABLE_FULLSPEED, false) ? "0" : "1");
	}
}
