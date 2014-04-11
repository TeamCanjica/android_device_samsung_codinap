package com.teamcanjica.settings.device;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.teamcanjica.settings.device.fragments.AdvancedFragmentActivity;
import com.teamcanjica.settings.device.fragments.AudioFragmentActivity;
import com.teamcanjica.settings.device.fragments.GPUFragmentActivity;
import com.teamcanjica.settings.device.fragments.NetworkFragmentActivity;
import com.teamcanjica.settings.device.fragments.IOFragmentActivity;
import com.teamcanjica.settings.device.fragments.ScreenFragmentActivity;
import com.teamcanjica.settings.device.fragments.USBFragmentActivity;

public class ContainerActivity extends Activity {

	FrameLayout frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.container);
		frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
		
		Fragment fragment = new Fragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();

		switch (getIntent().getExtras().getInt(DeviceSettings.SELECTION)) {
		case 0:
			// Network
			fragment = new NetworkFragmentActivity();
			break;
		case 1:
			// USB
			fragment = new USBFragmentActivity();
			break;
		case 2:
			// Audio
			fragment = new AudioFragmentActivity();
			break;
		case 3:
			// Screen
			fragment = new ScreenFragmentActivity();
			break;
		case 4:
			// GPU
			fragment = new GPUFragmentActivity();
			break;
		case 5:
			// I/O
			fragment = new IOFragmentActivity();
			break;
		case 6:
			// Advanced
			fragment = new AdvancedFragmentActivity();
			break;

		default:
			break;
		}
		
		transaction.replace(R.id.frameLayout, fragment);
		transaction.commit();

		super.onCreate(savedInstanceState);
	}

}
