package placeholderPackageName.util.driver;

import io.appium.java_client.AppiumDriver;

public interface MobileDriverService {
    void startUpDriver();

    void tearDownDriver();

    AppiumDriver getDriver();
}
