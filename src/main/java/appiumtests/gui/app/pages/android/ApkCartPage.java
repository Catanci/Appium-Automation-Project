package appiumtests.gui.app.pages.android;

import appiumtests.gui.app.pages.common.ApkCartPageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApkCartPage extends ApkCartPageBase {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView[@text]")
    private WebElement itemCounter;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='red circle']")
    private WebElement redOption;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove Item']")
    private WebElement remove;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No Items']")
    private WebElement emptyCartTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Go Shopping button']")
    private WebElement goShoppingButton;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkCartPage.class);

    public ApkCartPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getCounterAmount() {
        try {
            Thread.sleep(1000);
            logger.info("Attempting to retrieve item count");
            String counter = itemCounter.getText();
            logger.info("Product name retrieved: " + counter);
            return counter;
        }
        catch (Exception e) {
            logger.error("Failed to get the count of items: "+ e);
            return "Failed to get the count of items";
        }
    }

    public boolean isColorCorrect() {
        try {
            wait.until(ExpectedConditions.visibilityOf(redOption));
            return redOption.isDisplayed();
        } catch (Exception e) {
            logger.error("Banner  is not visible on Home Page ", e);
            return false;
        }
    }

    public boolean isCartEmpty() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(emptyCartTitle));
            return emptyCartTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Cart is not empty when should be", e);
            return false;
        }
    }

    public void tapRemoveButton() {
        remove.click();
    }

    public void tapGoBack() {
        goShoppingButton.click();
    }



}
