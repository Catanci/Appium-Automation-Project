package webtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import placeholderPackageName.util.driver.AndroidDriverServiceImpl;
import io.appium.java_client.AppiumDriver;

public class AndroidAppTest {

    private AndroidDriverServiceImpl androidDriverService;
    private AppiumDriver driver;

    @BeforeClass
    public void setUp() {
        androidDriverService = new AndroidDriverServiceImpl();
        androidDriverService.startUpDriver();
        driver = androidDriverService.getDriver();
    }

    @Test
    public void testAppFunctionality() {
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        androidDriverService.tearDownDriver();
    }
}
