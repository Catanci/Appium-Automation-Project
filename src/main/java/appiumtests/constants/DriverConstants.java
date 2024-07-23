package appiumtests.constants;

import static appiumtests.util.PropertiesReader.*;

public class DriverConstants {
    // Appium Constants
    public static final String APPIUM_URL = getAppiumConfig("appium_url");
    public static final Long APPIUM_DRIVER_TIMEOUT_IN_SECONDS = Long.parseLong(
            getAppiumConfig("appium_driver_timeout_in_seconds"));

    public static final String ANDROID = "android";
    public static final String ANDROID_DEVICE_NAME = getDeviceConfig("android_device_name");
    public static final String ANDROID_PLATFORM_NAME = getDeviceConfig("android_platform_name");
    public static final String ANDROID_AUTOMATION_NAME = getDeviceConfig("android_automation_name");
    public static final String ANDROID_PLATFORM_VERSION = getDeviceConfig("android_platform_version");
    public static final String ANDROID_APK_PACKAGE = getDeviceConfig("android_apk_package");
    public static final String ANDROID_APK_ACTIVITY = getDeviceConfig("android_apk_activity");
    public static final String ANDROID_APK_PATH = getDeviceConfig("android_apk_path");
    public static final String ANDROID_BROWSER_NAME = getDeviceConfig("android_browser_name");

    public static final String IOS = "ios";
    public static final String IOS_DEVICE_NAME = getDeviceConfig("ios_device_name");
    public static final String IOS_AUTOMATION_NAME = getDeviceConfig("ios_automation_name");
    public static final String IOS_BROWSER_NAME = getDeviceConfig("ios_browser_name");
    public static final String IOS_PLATFORM_NAME = getDeviceConfig("ios_platform_name");
    public static final String IOS_PLATFORM_VERSION = getDeviceConfig("ios_platform_version");
    public static final String IOS_BUNDLE_ID = getDeviceConfig("ios_bundle_id");
}
