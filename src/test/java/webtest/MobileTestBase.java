package webtest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import placeholderPackageName.util.driver.AndroidDriverServiceImpl;
import placeholderPackageName.util.driver.MobileDriverHolder;
import io.appium.java_client.AppiumDriver;

public class MobileTestBase {

    @BeforeMethod
    public void setupDriver() {
        AndroidDriverServiceImpl androidDriverService = new AndroidDriverServiceImpl();
        androidDriverService.startUpDriver();
        MobileDriverHolder.setDriver(androidDriverService.getDriver());
    }

    @AfterMethod
    public void tearDownDriver() {
        AppiumDriver driver = MobileDriverHolder.getDriver();
        if (driver != null) {
            driver.quit();
        }
        MobileDriverHolder.setDriver(null);
    }



}
