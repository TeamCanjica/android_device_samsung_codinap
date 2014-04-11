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

package com.teamcanjica.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.teamcanjica.settings.device.fragments.AdvancedFragmentActivity;
import com.teamcanjica.settings.device.fragments.AudioFragmentActivity;
import com.teamcanjica.settings.device.fragments.GPUFragmentActivity;
import com.teamcanjica.settings.device.fragments.IOFragmentActivity;
import com.teamcanjica.settings.device.fragments.NetworkFragmentActivity;
import com.teamcanjica.settings.device.fragments.ScreenFragmentActivity;
import com.teamcanjica.settings.device.fragments.USBFragmentActivity;

public class Startup extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent bootintent) {
		USBFragmentActivity.restore(context);
		AudioFragmentActivity.restore(context);
		ScreenFragmentActivity.restore(context);
		NetworkFragmentActivity.restore(context);
		AdvancedFragmentActivity.restore(context);
		GPUFragmentActivity.restore(context);
		MasterListPreference.restore(context);
		MasterSeekBarDialogPreference.restore(context);
		IOFragmentActivity.restore(context);
	}
}
