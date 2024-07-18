package appiumtests.util.driver;

import appiumtests.constants.TestType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;


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
//            options.setCapability("browserName", "Chrome");
//            options.setCapability("autoDownloadChromedriver", true);
//            options.setCapability("autoWebviewTimeout", 10000);
//            options.setCapability("nativeWebScreenshot", true);
//            options.setCapability("ensureWebviewsHavePages", true);
        } else if (testType == TestType.APP) {
            logger.info("Setting up APP test...");
            Path appPath = Paths.get(ANDROID_APP_APK_PATH).toAbsolutePath();
            options.setApp(String.valueOf(appPath));
            options.setNoReset(false);
            options.setAppPackage(ANDROID_APP_APK_PACKAGE);
            options.setAppActivity(ANDROID_APP_APK_ACTIVITY);
            logger.debug("App Package: " + ANDROID_APP_APK_PACKAGE);
            logger.debug("App Activity: " + ANDROID_APP_APK_ACTIVITY);
        }

        try {
            logger.debug("Attempting to create AndroidDriver for web testing...");
            androidDriver = new AndroidDriver(new URL(APPIUM_URL), options);
            logger.info("AndroidDriver created successfully for web testing");

//            switchToWebViewContext();
        } catch (Exception e) {
            logger.error("Error creating AndroidDriver for web testing: " + e.getMessage(), e);
        }

        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

//    private void switchToWebViewContext() {
//        try {
//            // Wait for contexts to be available
//            new WebDriverWait(androidDriver, Duration.ofSeconds(30))
//                    .until(driver -> ((AndroidDriver) driver).getContextHandles().size() > 1);
//
//            Set<String> contextNames = androidDriver.getContextHandles();
//            for (String contextName : contextNames) {
//                if (contextName.contains("CHROMIUM")) {
//                    androidDriver.context(contextName);
//                    logger.info("Switched to " + contextName + " context");
//                    return;
//                }
//            }
//            logger.warn("No WEBVIEW context found");
//        } catch (Exception e) {
//            logger.error("Failed to switch to WEBVIEW context", e);
//        }
//    }

    @Override
    public void tearDownDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
    public AppiumDriver getDriver() {
        return androidDriver;
    }
}
