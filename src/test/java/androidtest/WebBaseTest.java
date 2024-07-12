package androidtest;

import appiumtests.constants.TestType;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import appiumtests.constants.Direction;
import appiumtests.util.driver.AndroidDrivers;
import appiumtests.util.driver.MobileDriverService;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

public class WebBaseTest {
    protected MobileDriverService driverService;
    protected AndroidDriver driver;
    private final Logger logger = LogManager.getLogger(WebBaseTest.class);

    @BeforeMethod
    public void setUp() {
        driverService = new AndroidDrivers();
        driverService.startUpDriver(TestType.WEB);
        driver = driverService.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        logger.info("Driver started successfully");
    }

    protected void quitDriver() {
        if (driverService != null) {
            driverService.tearDownDriver();
            logger.info("Driver closed successfully");
        }
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

    public void swipeScreen(Direction direction) {
        Dimension size = driver.manage().window().getSize();

        int startX, startY, endX, endY;
        switch (direction) {
            case UP:
                startX = size.width / 2;
                startY = (int) (size.height * 0.8);
                endY = (int) (size.height * 0.2);
                endX = startX;
                break;
            case DOWN:
                startX = size.width / 2;
                startY = (int) (size.height * 0.2);
                endY = (int) (size.height * 0.8);
                endX = startX;
                break;
            case LEFT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.8);
                endX = (int) (size.width * 0.2);
                endY = startY;
                break;
            case RIGHT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.2);
                endX = (int) (size.width * 0.8);
                endY = startY;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        logger.debug("Executing swipe action");
        driver.perform(List.of(swipe));
//        driver.perform(Arrays.asList(swipe));

    }

    public void pause(Number timeout) {
        logger.debug("Will wait for {} seconds", timeout);

        try {
            Float timeoutFloat = timeout.floatValue() * 1000.0F;
            long timeoutLong = timeoutFloat.longValue();
            Thread.sleep(timeoutLong);
        } catch (InterruptedException var4) {
            Thread.currentThread().interrupt();
        }

        logger.debug("Pause is over. Keep going..");
    }


}
