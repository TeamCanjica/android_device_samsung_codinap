# Specify phone tech before including full_phone
$(call inherit-product, vendor/cm/config/gsm.mk)

# Release name
PRODUCT_RELEASE_NAME := GT-I8160P

# Boot animation
TARGET_SCREEN_HEIGHT := 800
TARGET_SCREEN_WIDTH := 480

# Inherit some common CM stuff.
$(call inherit-product, vendor/cm/config/common_full_phone.mk)

# Inherit device configuration
$(call inherit-product, device/samsung/codina/full_codinap.mk)

# Device identifier. This must come after all inclusions
PRODUCT_NAME := cm_codinap
PRODUCT_DEVICE := codinap
PRODUCT_BRAND := samsung
PRODUCT_MODEL := GT-I8160P
PRODUCT_MANUFACTURER := samsung

# Set build fingerprint / ID / Product Name ect.
PRODUCT_BUILD_PROP_OVERRIDES += PRODUCT_NAME=GT-I8160P TARGET_DEVICE=GT-I8160P
