ifeq ($(TARGET_DEVICE),codinap)
    LOCAL_PATH := $(call my-dir)
    include $(call first-makefiles-under,$(LOCAL_PATH))
endif
