package placeholderPackageName.util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static placeholderPackageName.common.constants.DriverConstants.*;

public class IOSMobileDriver implements MobileDriverService {
    private AppiumDriver iosDriver;

    @Override
    public void startUpDriver() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", IOS_DEVICE_NAME);
        capabilities.setCapability("automationName", IOS_AUTOMATION_NAME);
        capabilities.setCapability(PLATFORM_NAME, IOS);
        capabilities.setCapability("platformVersion", IOS_PLATFORM_VERSION);
        capabilities.setCapability("appium:shouldTerminateApp", IOS_SHOULD_TERMINATE_APP);
        capabilities.setCapability("fullReset", IOS_FULL_RESET);

        try {
            iosDriver = new IOSDriver(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

    @Override
    public void tearDownDriver() {{
            iosDriver.quit();
        }
    }

    @Override
    public AppiumDriver getDriver() {
        return iosDriver;
    }
}
