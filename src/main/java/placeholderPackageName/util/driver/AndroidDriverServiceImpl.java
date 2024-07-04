package placeholderPackageName.util.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static placeholderPackageName.common.constants.DriverConstants.*;

public class AndroidDriverServiceImpl implements MobileDriverService {
    private AndroidDriver androidDriver;

    @Override
    public void startUpDriver() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", ANDROID_DEVICE_NAME);
        capabilities.setCapability("automationName", ANDROID_AUTOMATION_NAME);
        capabilities.setCapability(PLATFORM_NAME, ANDROID);
        capabilities.setCapability("platformVersion", ANDROID_PLATFORM_VERSION);
        capabilities.setCapability(BROWSER_NAME, "Chrome"); // Specify browser for web automation
        capabilities.setCapability("fullReset", ANDROID_FULL_RESET);

        try {
            androidDriver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
    }

    @Override
    public void tearDownDriver() {
        androidDriver.quit();
    }

    @Override
    public AppiumDriver getDriver() {
        return androidDriver;
    }
}
