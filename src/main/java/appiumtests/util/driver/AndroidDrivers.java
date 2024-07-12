package appiumtests.util.driver;

import appiumtests.constants.TestType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static appiumtests.constants.DriverConstants.*;

@Getter
public class AndroidDrivers implements MobileDriverService {
    private AndroidDriver androidDriver;

    @Override
    public void startUpDriver(TestType testType) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(ANDROID_DEVICE_NAME);
        options.setPlatformName(ANDROID_PLATFORM_NAME);
        options.setPlatformVersion(ANDROID_PLATFORM_VERSION);
        options.setAutomationName(ANDROID_AUTOMATION_NAME);
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);
        options.setCapability("autoAcceptAlerts", true);

        if (testType == TestType.WEB) {
            options.setAppPackage(ANDROID_APP_PACKAGE);
            options.setAppActivity(ANDROID_APP_ACTIVITY);
        } else if (testType == TestType.APK) {
            options.setAppPackage(ANDROID_APP_APK_PACKAGE);
            options.setAppActivity(ANDROID_APP_APK_ACTIVITY);
        }

        try {
            androidDriver = new AndroidDriver(new URL(APPIUM_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

    @Override
    public void tearDownDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
    public AndroidDriver getDriver() {
        return androidDriver;
    }
}
