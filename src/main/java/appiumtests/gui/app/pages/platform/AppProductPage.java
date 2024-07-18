package appiumtests.gui.app.pages.platform;

import appiumtests.gui.app.pages.common.ApkProductPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class AppProductPage extends ApkProductPageBase {

    @iOSXCUITFindBy(accessibility = "Add To Cart button")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add To Cart']")
    private WebElement addToCart;

    @iOSXCUITFindBy(accessibility = "red circle")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='red circle']")
    private WebElement redOption;

    @iOSXCUITFindBy(accessibility = "counter plus button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter plus button']/android.widget.ImageView")
    private WebElement plusButton;

    @iOSXCUITFindBy(accessibility = "Product Highlights")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Highlights']")
    private WebElement highlightsBanner;

    @iOSXCUITFindBy(accessibility = "counter amount")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView[@text]")
    private WebElement itemCounter;

    @iOSXCUITFindBy(accessibility = "tab bar option cart")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView")
    private WebElement cartItemCount;

    private final Logger logger = LogManager.getLogger(AppProductPage.class);

    private final WebDriverWait wait;

    public AppProductPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isBannerVisible() {
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(highlightsBanner));
            return highlightsBanner.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public String getCounterAmount() {
        try {
            Thread.sleep(1000);
            logger.info("Attempting to retrieve item count from product page");
            String counter = itemCounter.getText();
            logger.info("Product name retrieved from product page: {}", counter);
            return counter;
        }
        catch (Exception e) {
            logger.error("Failed to get the count of items from product page: ", e);
            return "Failed to get the count of items from product page";
        }
    }

    public String getCartIconItemCount() {
        try {
            Thread.sleep(1000);
            logger.info("Attempting to retrieve item from the cart icon");
            String counter =  cartItemCount.getText();
            logger.info("Item count retrieved from cart icon: {}", counter);
            return counter;
        }
        catch (Exception e) {
            logger.error("Failed to get the count of items from cart icon: ", e);
            return "Failed to get the count of items from cart icon";
        }
    }

    public void tapRedOption() {
        redOption.click();
    }

    public void tapAddToCart() {
        addToCart.click();
    }

    public void tapPlusButton() {
        plusButton.click();
    }

    public void tapCartIcon() {
        cartItemCount.click();
    }
}
