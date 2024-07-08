package placeholderPackageName.util.driver;

import io.appium.java_client.android.AndroidDriver;

public interface MobileDriverService {
    void startUpDriver();
    void tearDownDriver();
    AndroidDriver getDriver();
}