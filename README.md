CyanogenMod 11.0
=============================
Device Tree for Samsung Galaxy Ace 2 NFC Variant 
(GT-I8160P)

How to build:
=============

- Make a workspace

  $ mkdir -p ~/cyanogenmod
  $ cd ~/cyanogenmod
  
- Do repo init & sync

  repo init -u https://github.com/TeamCanjica/android.git -b cm-11.0
  
  repo sync -j32

- Setup vendor
  
  ./vendor/cm/get-prebuilts
  
  . build/envsetup.sh

- Pull all not merged fixes from gerrit:

  				cd build
  				git fetch https://github.com/TeamCanjica/android_build cm-11.0
  				git cherry-pick dbe7e5b4fff354cd9a9ef2e6605fa7db7eef9727
  				cd ..
				cd art
				git fetch https://github.com/cernekee/android_art monitor-stack-v1
				git cherry-pick fc2ac71d0d9e147c607bff9371fe2ef25d8470af
				git fetch https://github.com/JustArchi/android_art android-4.4
				git cherry-pick 8354d2dc9d260ca67dbdf32e123bd4da62b8a68d
				cd ..
				cd external/clang
				git fetch https://github.com/zwliew/android_external_clang cm-11.0
				git cherry-pick bb0a1a5f007dc6e6f111c3a726977c4cce256bc5
				git cherry-pick 085466671e3c0483466de009bbc81fd31505f6e6
				cd ..
				cd fuse
				git fetch https://github.com/SlimSaber/android_external_fuse kk4.4
				git cherry-pick f3736cb1104f72ee1f1322a4eea79e960bee0cd6
				cd ..
				cd exfat
				git fetch https://github.com/SlimSaber/android_external_exfat kk4.4
				git cherry-pick 0cbb04e3fd9a254dbddf440355949383a9a00976
				cd ../..
				cd frameworks/av
				git fetch https://github.com/TeamCanjica/android_frameworks_av cm-11.0
				git cherry-pick 5c4dd9cc832f47017df8930d77e2d175744af3eb
				cd ..
				cd native
				git fetch https://github.com/TeamCanjica/android_frameworks_native cm-11.0
				git cherry-pick 213b1afc3177f483598e23b9d09738d29c8129cb
				git fetch http://review.cyanogenmod.org/CyanogenMod/android_frameworks_native refs/changes/11/59311/1
				git cherry-pick FETCH_HEAD
				cd ..
				cd base
				git fetch https://github.com/TeamCanjica/android_frameworks_base cm-11.0
				git cherry-pick de30387b3c32c2a9cf653590c8454bd002bf0dd1
				cd ../..
				cd system/core
				git fetch https://github.com/TeamCanjica/android_system_core cm-11.0
				git cherry-pick 02f79390ff2d6a0e173d1dd8bfae02844d4c33a6
				git cherry-pick 910ccc43a23b042df3df12ed1bbbe32954749e59
				cd ..
				cd vold
				git fetch http://review.cyanogenmod.org/CyanogenMod/android_system_vold refs/changes/15/56515/3
				git cherry-pick FETCH_HEAD
				cd ../..
				cd packages/services/Telephony
				git fetch https://github.com/TeamCanjica/android_packages_services_Telephony cm-11.0
				git cherry-pick fdf281fdabe5e7517eb96f2faf159bbcc74ae4a6
				cd ../../..
		
- Build CM11.0
  
  brunch codinap


- Thanks : CyanogenMod, dh-harald, Sakura Droid, jereksel, TeamCanjica, ekim.tecul