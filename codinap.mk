# Include common makefile
$(call inherit-product, device/samsung/u8500-common/common.mk)

#For better compatibility with ROMs (like Slim, PAC)
$(call inherit-product, vendor/samsung/u8500-common/codina/codinap-vendor-blobs.mk)

ifneq ($(TARGET_SCREEN_HEIGHT),800)
# Call cm.mk because somehow it's not being called!
$(call inherit-product, device/samsung/codinap/cm.mk)
endif

LOCAL_PATH := device/samsung/codinap
CODINA_PATH := device/samsung/codina

DEVICE_PACKAGE_OVERLAYS += $(CODINA_PATH)/overlay

# Ace 2 settings
PRODUCT_PACKAGES += \
    GalaxyAce2Settings

# Init files
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/rootdir/init.samsungcodina.rc:root/init.samsungcodina.rc \
    $(CODINA_PATH)/rootdir/fstab.samsungcodina:root/fstab.samsungcodina \
    $(CODINA_PATH)/rootdir/init.recovery.samsungcodina.rc:root/init.recovery.samsungcodina.rc \
    $(CODINA_PATH)/rootdir/init.samsungcodina.usb.rc:root/init.samsungcodina.usb.rc \
    $(CODINA_PATH)/rootdir/ueventd.samsungcodina.rc:root/ueventd.samsungcodina.rc
    
# STE
PRODUCT_COPY_FILES += \
    $(CODINA_PATH)/configs/ste_modem.sh:system/etc/ste_modem.sh

# Audio
PRODUCT_COPY_FILES += \
    $(CODINA_PATH)/configs/audio_policy.conf:system/etc/audio_policy.conf \
    $(CODINA_PATH)/configs/adm.sqlite-u8500:system/etc/adm.sqlite-u8500
    
# Dbus
PRODUCT_COPY_FILES += \
    $(CODINA_PATH)/configs/dbus.conf:system/etc/dbus.conf

# Gps
PRODUCT_COPY_FILES += \
    $(CODINA_PATH)/configs/gps.conf:system/etc/gps.conf
	
# NFC
PRODUCT_PACKAGES += \
    com.android.nfc_extras \
    libnfc \
    libnfc_jni \
    Nfc \
    Tag
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/NFC/nfcee_access.xml:system/etc/nfcee_access.xml \
    packages/apps/Nfc/migrate_nfc.txt:system/etc/updatecmds/migrate_nfc.txt \
    frameworks/native/data/etc/android.hardware.nfc.xml:system/etc/permissions/android.hardware.nfc.xml \
    frameworks/native/data/etc/com.nxp.mifare.xml:system/etc/permissions/com.nxp.mifare.xml \
    frameworks/native/data/etc/com.android.nfc_extras.xml:system/etc/permissions/com.android.nfc_extras.xml

# Storage switch script
PRODUCT_COPY_FILES += \
    $(CODINA_PATH)/configs/SwapStorages.sh:system/xbin/SwapStorages.sh
PRODUCT_PROPERTY_OVERRIDES += \
     persist.sys.vold.switchablepair=sdcard0,sdcard1 \
     persist.sys.vold.switchexternal=0
