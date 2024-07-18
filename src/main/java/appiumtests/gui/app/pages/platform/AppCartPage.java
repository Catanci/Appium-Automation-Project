package appiumtests.gui.app.pages.platform;

import appiumtests.gui.app.pages.common.ApkCartPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppCartPage extends ApkCartPageBase {
    @iOSXCUITFindBy(accessibility = "counter amount")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView[@text]")
    private WebElement itemCounter;

    @iOSXCUITFindBy(accessibility = "red circle")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='red circle']")
    private WebElement redOption;

    @iOSXCUITFindBy(accessibility = "remove item")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove Item']")
    private WebElement remove;

    @iOSXCUITFindBy(accessibility = "No Items")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No Items']")
    private WebElement emptyCartTitle;

    @iOSXCUITFindBy(accessibility = "Go Shopping button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Go Shopping button']")
    private WebElement goShoppingButton;

    @iOSXCUITFindBy(accessibility = "Proceed To Checkout button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Proceed To Checkout button']")
    private WebElement proceedToCheckoutButton;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(AppCartPage.class);

    public AppCartPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getCounterAmount() {
        try {
            Thread.sleep(1000);
            logger.info("Attempting to retrieve item count");
            String counter = itemCounter.getText();
            logger.info("Product count retrieved: " + counter);
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

    public void tapProceedToCheckout() {
        proceedToCheckoutButton.click();
    }
}
