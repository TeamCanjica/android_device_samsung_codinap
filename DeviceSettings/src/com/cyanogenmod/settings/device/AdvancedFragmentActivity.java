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

package com.cyanogenmod.settings.device;

import com.cyanogenmod.settings.device.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import java.lang.Runtime;
import java.io.IOException;

public class AdvancedFragmentActivity extends PreferenceFragment {

	private static final String TAG = "GalaxyAce2_Settings_Advanced";

	public static final String FILE_SWEEP2WAKE = "/sys/kernel/bt404/sweep2wake";

	public static final String FILE_SPI_CRC = "/sys/module/mmc_core/parameters/use_spi_crc";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.advanced_preferences);

		PreferenceScreen prefSet = getPreferenceScreen();
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		String boxValue;
		String key = preference.getKey();

		Log.w(TAG, "key: " + key);

		if (key.equals(DeviceSettings.KEY_USE_SPI_CRC)) {
			boxValue = (((CheckBoxPreference) preference).isChecked() ? "0"
					: "1");
			Utils.writeValue(FILE_SPI_CRC, boxValue);
		}

		if (key.equals(DeviceSettings.KEY_SWITCH_STORAGE)) {
			boolean b = ((CheckBoxPreference) preference).isChecked();
			String cmd = "SwapStorages.sh " + (b?"1":"0");

			try {
			    Process proc = Runtime.getRuntime().exec(new String[]{"su","-c",cmd});
			    proc.waitFor();
			} catch (IOException e) {
			    e.printStackTrace();
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		}

		if (key.equals(DeviceSettings.KEY_USE_SWEEP2WAKE)) {
			boxValue = (((CheckBoxPreference) preference).isChecked() ? "on"
					: "off");
			Utils.writeValue(FILE_SWEEP2WAKE, boxValue);

		}

		return true;
	}

	public static void restore(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		String crcvalue = sharedPrefs.getBoolean(DeviceSettings.KEY_USE_SPI_CRC,
				false) ? "0" : "1";
		Utils.writeValue(FILE_SPI_CRC, crcvalue);
	
		int sstor = SystemProperties.getInt("persist.sys.vold.switchexternal", 0) ;
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putBoolean(DeviceSettings.KEY_SWITCH_STORAGE,sstor==1?true:false);
		editor.commit();

		String s2wvalue = sharedPrefs.getBoolean(
				DeviceSettings.KEY_USE_SWEEP2WAKE, false) ? "on" : "off";
		Utils.writeValue(FILE_SWEEP2WAKE, s2wvalue);

	}

}
