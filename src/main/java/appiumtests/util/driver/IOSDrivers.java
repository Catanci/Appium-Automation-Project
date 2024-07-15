package appiumtests.util.driver;

import appiumtests.constants.TestType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static appiumtests.constants.DriverConstants.*;

@Getter
public class IOSDrivers implements MobileDriverService {
    private IOSDriver iosDriver;

    private final Logger logger = LogManager.getLogger(IOSDrivers.class);

    @Override
    public void startUpDriver(TestType testType) {
        XCUITestOptions options = new XCUITestOptions();

        options.setDeviceName(IOS_DEVICE_NAME);
        options.setPlatformName(IOS_PLATFORM_NAME);
        options.setPlatformVersion(IOS_PLATFORM_VERSION);
        options.setAutomationName(IOS_AUTOMATION_NAME);
        options.setNoReset(true);
        options.setCapability("autoAcceptAlerts", true);

        if (testType == TestType.WEB) {
            logger.info("Setting up WEB test...");
            options.setBundleId(IOS_BUNDLE_ID);
        } else if (testType == TestType.APP) {
            logger.info("Setting up APP test...");
            Path appPath = Paths.get(IOS_APP_PATH).toAbsolutePath();
            options.setApp(appPath.toString());
            options.setNoReset(false);
            options.setBundleId(IOS_BUNDLE_ID);
        }

        try {
            logger.debug("Attempting to create IOSDriver...");
            iosDriver = new IOSDriver(new URL(APPIUM_URL), options);
            logger.info("IOSDriver created successfully");
        } catch (Exception e) {
            logger.error("Error creating IOSDriver: " + e.getMessage());
            e.printStackTrace();
        }

        iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

    @Override
    public void tearDownDriver() {
        if (iosDriver != null) {
            iosDriver.quit();
        }
    }
    public AppiumDriver getDriver() {
        return iosDriver;
    }
}
