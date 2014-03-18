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

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;

import com.cyanogenmod.settings.device.R;

public class DeviceSettings extends FragmentActivity {

	public static final String SHARED_PREFERENCES_BASENAME = "com.cyanogenmod.settings.device";
	public static final String ACTION_UPDATE_PREFERENCES = "com.cyanogenmod.settings.device.UPDATE";
	public static final String KEY_HSPA = "hspa";
	public static final String KEY_USE_ACCELEROMETER_CALIBRATION = "use_accelerometer_calibration";
	public static final String KEY_CALIBRATE_ACCELEROMETER = "calibrate_accelerometer";
	public static final String KEY_USB_OTG_POWER = "usb_otg_power";
	public static final String KEY_DEEPEST_SLEEP_STATE = "deepest_sleep_state";
	public static final String KEY_AC_CURRENCY = "ac_currency";
	public static final String KEY_USB_CURRENCY = "usb_currency";
	public static final String KEY_FSYNC_MODE = "fsync_mode";
	public static final String KEY_MALI_L2MR = "mali_l2_mr";
	public static final String KEY_MALI_PAM = "mali_pam";
	public static final String KEY_USE_SWEEP2WAKE = "use_sweep2wake";
	public static final String KEY_USE_SPI_CRC = "use_spi_crc";
	public static final String KEY_SWITCH_STORAGE = "switch_storage";
	public static final String KEY_ENABLE_ANAGAIN3 = "enable_anagain3";
	public static final String KEY_ENABLE_HSLDIGGAIN = "enable_hsldiggain";
	public static final String KEY_ENABLE_HSRDIGGAIN = "enable_hsrdiggain";
	public static final String KEY_ENABLE_HSLOWPOW = "enable_hslowpow";
	public static final String KEY_ENABLE_HSDACLOWPOW = "enable_hsdaclowpow";
	public static final String KEY_ENABLE_HSHPEN = "enable_hshpen";
	public static final String KEY_ENABLE_CLASSDHPG = "enable_classdhpg";
	public static final String KEY_ENABLE_CLASSDWG = "enable_classdwg";
	public static final String KEY_ENABLE_ADDIGGAIN2 = "enable_addiggain2";
	public static final String KEY_ENABLE_EARDIGGAIN = "enable_eardiggain";

	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE,
				ActionBar.DISPLAY_SHOW_TITLE);
		bar.setTitle(R.string.app_name);
		bar.setDisplayHomeAsUpEnabled(true);

		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(
				bar.newTab().setText(R.string.category_general_title),
				GeneralFragmentActivity.class, null);
		mTabsAdapter.addTab(bar.newTab().setText(R.string.category_usb_title),
				USBFragmentActivity.class, null);
		mTabsAdapter.addTab(
				bar.newTab().setText(R.string.category_advanced_title),
				AdvancedFragmentActivity.class, null);
		mTabsAdapter.addTab(
				bar.newTab().setText(R.string.category_audio_title),
				AudioFragmentActivity.class, null);

		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

	public static class TabsAdapter extends FragmentPagerAdapter implements
			ActionBar.TabListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args) {
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(Activity activity, ViewPager pager) {
			super(activity.getFragmentManager());
			mContext = activity;
			mActionBar = activity.getActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		public void onPageScrollStateChanged(int state) {
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i) == tag) {
					mViewPager.setCurrentItem(i);
				}
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			DeviceSettings.this.onBackPressed();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
