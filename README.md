CyanogenMod 11.0
=============================
Device Tree for Samsung Galaxy Ace 2 NFC Variant (GT-I8160P)

How to build:
=============

- Make a workspace

  $ mkdir -p ~/cyanogenmod/system
  $ cd ~/cyanogenmod/system
  
- Do repo init & sync

  repo init https://github.com/TeamCanjica/android.git -b cm-11.0
  
  repo sync -j32

- Setup vendor
  
  ./vendor/cm/get-prebuilts
  
  . build/envsetup.sh

- Pull all not merged fixes from gerrit:

				cd art
				git fetch https://github.com/cernekee/android_art monitor-stack-v1
				git cherry-pick fc2ac71d0d9e147c607bff9371fe2ef25d8470af
				cd ..
				cd frameworks/av
				git fetch https://github.com/TeamCanjica/android_frameworks_av cm-11.0
				git cherry-pick 803bb5dd145630c0239a61bd4d58c3728f2dba57
				cd ..
				cd native
				git fetch https://github.com/TeamCanjica/android_frameworks_native cm-11.0
				git cherry-pick d9dd39ca7935c16a34ce8b3e8c00dd6bae680d49
				cd ..
				cd base
				git fetch https://github.com/TeamCanjica/android_frameworks_base cm-11.0
				git cherry-pick 3826055d49ec70ab3d0e130a8e444fd334806fa5
				cd ../..
				cd system/core
				git fetch https://github.com/TeamCanjica/android_system_core cm-11.0
				git cherry-pick 7bd81ee140c09066ede2ffab47da1a1c4e54e021
				git cherry-pick b6cb91b1f70c969bb0f818a24111c0ca055be590
				cd ..
				cd vold
				git fetch http://review.cyanogenmod.org/CyanogenMod/android_system_vold refs/changes/15/56515/2
				git cherry-pick FETCH_HEAD
				cd ../..
				cd hardware/libhardware_legacy
				git fetch https://github.com/TeamCanjica/android_hardware_libhardware_legacy cm-11.0
				git cherry-pick 9c2250d32a1eda9afe3b5cefe3306104148aa532
				cd ../..
				cd build
				git fetch https://github.com/TeamCanjica/android_build cm-10.2
				git cherry-pick 8f031162d08dadd1595d8c1e42d23134bbdb93d3
				cd ..
		
- Build CM11.0
  
  brunch codinap


- Thanks : CyanogenMod, dh-harald, Sakura Droid, jereksel, diego-ch, frapeti, OliverG96, ekim.tecul