/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

public class AudioFragmentActivity extends PreferenceFragment {

	private static final String TAG = "GalaxyAce2_Settings_Audio";
	
	public static final String FILE_ANAGAIN3 = "/sys/kernel/abb-codec/anagain3";
	
	public static final String FILE_HSLDIGGAIN = "/sys/kernel/abb-codec/hsldiggain";
	
	public static final String FILE_HSRDIGGAIN = "/sys/kernel/abb-codec/hsrdiggain";
	
	public static final String FILE_HSLOWPOW = "/sys/kernel/abb-codec/hslowpow";
	
	public static final String FILE_HSDACLOWPOW = "/sys/kernel/abb-codec/hsdaclowpow";
	
	public static final String FILE_HSHPEN = "/sys/kernel/abb-codec/hshpen";
	
	public static final String FILE_CLASSDHPG = "/sys/kernel/abb-codec/classdhpg";
	
	public static final String FILE_CLASSDWG = "/sys/kernel/abb-codec/classdwg";
	
	public static final String FILE_ADDIGGAIN2 = "/sys/kernel/abb-codec/addiggain2";
	
	public static final String FILE_EARDIGGAIN = "/sys/kernel/abb-codec/eardiggain";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.audio_preferences);

		getActivity().getActionBar().setTitle(getResources().getString(R.string.audio_name));
		getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.audio_icon));
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		String key = preference.getKey();

		Log.w(TAG, "key: " + key);

		if (key.equals(DeviceSettings.KEY_ENABLE_ANAGAIN3)) {

			Utils.writeValue(FILE_ANAGAIN3, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_HSLDIGGAIN)) {
			Utils.writeValue(FILE_HSLDIGGAIN, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_HSRDIGGAIN)) {
			Utils.writeValue(FILE_HSRDIGGAIN, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_HSLOWPOW)) {
			Utils.writeValue(FILE_HSLOWPOW, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_HSDACLOWPOW)) {
			Utils.writeValue(FILE_HSDACLOWPOW, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_HSHPEN)) {
			Utils.writeValue(FILE_HSHPEN, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_CLASSDHPG)) {
			Utils.writeValue(FILE_CLASSDHPG, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_CLASSDWG)) {
			Utils.writeValue(FILE_CLASSDWG, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_ADDIGGAIN2)) {
			Utils.writeValue(FILE_ADDIGGAIN2, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		} else if (key.equals(DeviceSettings.KEY_ENABLE_EARDIGGAIN)) {
			Utils.writeValue(FILE_EARDIGGAIN, (((CheckBoxPreference) preference).
					isChecked() ? "on" : "off"));
		}

		return true;
	}
	
	public static void restore(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		Utils.writeValue(FILE_ANAGAIN3, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_ANAGAIN3, false) ? "on" : "off");

		Utils.writeValue(FILE_HSLDIGGAIN, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_HSLDIGGAIN, false) ? "on" : "off");
		
		Utils.writeValue(FILE_HSRDIGGAIN, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_HSRDIGGAIN, false) ? "on" : "off");

		Utils.writeValue(FILE_HSLOWPOW, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_HSLOWPOW, false) ? "on" : "off");

		Utils.writeValue(FILE_HSDACLOWPOW, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_HSDACLOWPOW, false) ? "on" : "off");

		Utils.writeValue(FILE_HSHPEN, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_HSHPEN, false) ? "on" : "off");

		Utils.writeValue(FILE_CLASSDHPG, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_CLASSDHPG, false) ? "on" : "off");

		Utils.writeValue(FILE_CLASSDWG, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_CLASSDWG, false) ? "on" : "off");
		
		String addiggain2value = sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_ADDIGGAIN2, false) ? "on" : "off";
		Utils.writeValue(FILE_ADDIGGAIN2, addiggain2value);

		Utils.writeValue(FILE_EARDIGGAIN, sharedPrefs.getBoolean(
				DeviceSettings.KEY_ENABLE_EARDIGGAIN, false) ? "on" : "off");
	}

}
