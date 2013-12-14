CyanogenMod 10.2
=============================
Device Tree for Samsung Galaxy Ace 2 NFC Variant
(GT-I8160P)

How to build:
=============

- Make a workspace

  $ mkdir -p ~/cyanogenmod/system
  $ cd ~/cyanogenmod/system
  
- Do repo init & sync

  repo init https://github.com/TeamCanjica/android.git -b cm-10.2
  
  repo sync -j32

- Setup vendor
  
  ./vendor/cm/get-prebuilts
  
  . build/envsetup.sh

- Pull all not merged fixes from gerrit:
  
        cd frameworks/av
        git fetch http://review.cyanogenmod.org/CyanogenMod/android_frameworks_av refs/changes/32/52032/3
        git cherry-pick FETCH_HEAD
        cd ..
        cd native
        git fetch http://review.cyanogenmod.org/CyanogenMod/android_frameworks_native refs/changes/33/52033/5
        git cherry-pick FETCH_HEAD
        cd ../..
        cd system/core
        git fetch http://review.cyanogenmod.org/CyanogenMod/android_system_core refs/changes/34/52034/2
        git cherry-pick FETCH_HEAD
        cd ../..

- Build CM10.2
  
  brunch codinap


- Thanks : CyanogenMod, dh-harald, Sakura Droid, jereksel, diego-ch, frapeti, OliverG96, ekim.tecul, percy-g2, munjeni

