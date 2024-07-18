package appiumtests.util.driver;

import appiumtests.constants.TestType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.Duration;
import java.util.Set;

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
        options.setNoReset(false);
        options.setCapability("autoAcceptAlerts", true);

        if (testType == TestType.WEB) {
            logger.info("Setting up WEB test...");
            options.setCapability("browserName", IOS_BROWSER_NAME);
        } else if (testType == TestType.APP) {
            logger.info("Setting up APP test...");
            options.setNoReset(false);
            options.setBundleId(IOS_BUNDLE_ID);
        }

        try {
            logger.debug("Attempting to create IOSDriver...");
            iosDriver = new IOSDriver(new URL(APPIUM_URL), options);
            logger.info("IOSDriver created successfully");
            if (testType == TestType.WEB) {
                switchToNativeContext();
            }
        } catch (Exception e) {
            logger.error("Error creating IOSDriver: {}", e.getMessage());
            e.printStackTrace();
        }

        iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

    private void switchToNativeContext() {
        Set<String> contextHandles = iosDriver.getContextHandles();
        for (String contextHandle : contextHandles) {
            if (contextHandle.startsWith("NATIVE")) {
                iosDriver.context(contextHandle);
                logger.info("Switched to native context: {}", contextHandle);
                break;
            }
        }
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
