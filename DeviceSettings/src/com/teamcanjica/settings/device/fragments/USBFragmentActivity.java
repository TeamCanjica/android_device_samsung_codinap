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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.teamcanjica.settings.device.DeviceSettings;
import com.teamcanjica.settings.device.R;
import com.teamcanjica.settings.device.Utils;

public class USBFragmentActivity extends PreferenceFragment {

	private static final String TAG = "GalaxyAce2_Settings_USB";
	private static final String FILE_VOTG = "/sys/kernel/abb-regu/VOTG";
	private static final String FILE_CHARGER_CONTROL = "/sys/kernel/abb-charger/charger_curr";
	private static final String FILE_CYCLE_CHARGING_CONTROL = "/sys/kernel/abb-fg/cyc_fg";
	private static final String FILE_EOC = "/sys/kernel/abb-chargalg/eoc_status";
	private static final String FILE_EOC_FIRST = "/sys/kernel/abb-chargalg/eoc_first";
	private static final String FILE_EOC_REAL = "/sys/kernel/abb-chargalg/eoc_real";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.usb_preferences);

		getPreferenceScreen().findPreference(DeviceSettings.KEY_CHARGER_CURRENCY).setEnabled(
				((CheckBoxPreference) findPreference("use_charger_control")).isChecked());

		getPreferenceScreen().findPreference(DeviceSettings.KEY_DISCHARGING_THRESHOLD).setEnabled(
				((CheckBoxPreference) findPreference("use_cycle_charging")).isChecked());

		getPreferenceScreen().findPreference(DeviceSettings.KEY_RECHARGING_THRESHOLD).setEnabled(
				((CheckBoxPreference) findPreference("use_cycle_charging")).isChecked());

		getActivity().getActionBar().setTitle(getResources().getString(R.string.usb_name));
		getActivity().getActionBar().setIcon(getResources().getDrawable(R.drawable.usb_icon));

	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		// String boxValue;
		String key = preference.getKey();

		Log.w(TAG, "key: " + key);

		if (key.equals(DeviceSettings.KEY_USB_OTG_POWER)) {
				Utils.writeValue(FILE_VOTG,
						((CheckBoxPreference) preference).isChecked() ? "1" : "0");
		}

		if (key.equals(DeviceSettings.KEY_USE_CHARGER_CONTROL)) {
				Utils.writeValue(FILE_CHARGER_CONTROL,
						((CheckBoxPreference) preference).isChecked() ? "on" : "off");
				getPreferenceScreen().findPreference(DeviceSettings.KEY_CHARGER_CURRENCY).setEnabled(
						((CheckBoxPreference) preference).isChecked());
		}

		if (key.equals(DeviceSettings.KEY_USE_CYCLE_CHARGING)) {
			Utils.writeValue(FILE_CYCLE_CHARGING_CONTROL,
					((CheckBoxPreference) preference).isChecked() ? "on" : "off");
			getPreferenceScreen().findPreference(DeviceSettings.KEY_DISCHARGING_THRESHOLD).setEnabled(
					((CheckBoxPreference) preference).isChecked());
			getPreferenceScreen().findPreference(DeviceSettings.KEY_RECHARGING_THRESHOLD).setEnabled(
					((CheckBoxPreference) preference).isChecked());
		}

		if (key.compareTo(DeviceSettings.KEY_EOC) == 0) {
			String eoc = null;
			BufferedReader buffread;
			try {
				buffread = new BufferedReader(new FileReader(FILE_EOC));
				eoc = buffread.readLine();
				buffread.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Utils.showDialog((Context) getActivity(),
					getString(R.string.eoc_title),
					(String) eoc);
			// Reset EOC status when Real EOC is reached
			if (((String) eoc) == "Real EOC reached") {
				Utils.writeValue(FILE_EOC_REAL, "0");
				Utils.writeValue(FILE_EOC_FIRST, "0");
			}

		}

		return true;
	}

	public static void restore(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		Utils.writeValue(FILE_VOTG, sharedPrefs.getBoolean(
				DeviceSettings.KEY_USB_OTG_POWER, false) ? "1" : "0");

		Utils.writeValue(FILE_CHARGER_CONTROL, sharedPrefs.getBoolean(
				DeviceSettings.KEY_USE_CHARGER_CONTROL, false) ? "on" : "off");

		Utils.writeValue(FILE_CYCLE_CHARGING_CONTROL, sharedPrefs.getBoolean(
				DeviceSettings.KEY_USE_CYCLE_CHARGING, false) ? "on" : "off");

	}
}
