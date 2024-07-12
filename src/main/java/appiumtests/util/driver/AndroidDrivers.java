package appiumtests.util.driver;

import appiumtests.constants.TestType;
import appiumtests.gui.web.pages.android.SearchResultPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static appiumtests.constants.DriverConstants.*;

@Getter
public class AndroidDrivers implements MobileDriverService {
    private AndroidDriver androidDriver;

    private final Logger logger = LogManager.getLogger(AndroidDriver.class);


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
            logger.info("Setting up WEB test...");
            options.setAppPackage(ANDROID_APP_PACKAGE);
            options.setAppActivity(ANDROID_APP_ACTIVITY);
            logger.debug("App Package: " + ANDROID_APP_APK_PACKAGE);
            logger.debug("App Activity: " + ANDROID_APP_APK_ACTIVITY);
        } else if (testType == TestType.APK) {
            logger.info("Setting up APK test...");
            Path appPath = Paths.get(ANDROID_APP_APK_PATH).toAbsolutePath();
            options.setApp(String.valueOf(appPath));
            options.setNoReset(false);
            options.setAppPackage(ANDROID_APP_APK_PACKAGE);
            options.setAppActivity(ANDROID_APP_APK_ACTIVITY);
            logger.debug("App Package: " + ANDROID_APP_APK_PACKAGE);
            logger.debug("App Activity: " + ANDROID_APP_APK_ACTIVITY);
        }

        try {
            logger.debug("Attempting to create AndroidDriver...");
            androidDriver = new AndroidDriver(new URL(APPIUM_URL), options);
            logger.info("AndroidDriver created successfully");
        } catch (Exception e) {
            logger.debug("Error creating AndroidDriver: " + e.getMessage());
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
