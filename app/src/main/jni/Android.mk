LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := hellojni
LOCAL_SRC_FILES := HelloJni.cpp

include $(BUILD_SHARED_LIBRARY)