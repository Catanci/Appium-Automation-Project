package appiumtests.gui.app.pages.android;

import appiumtests.gui.app.pages.common.ApkProductPageBase;
import appiumtests.gui.web.pages.common.ProductPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class ApkProductPage extends ApkProductPageBase {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add To Cart']")
    private WebElement addToCart;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='red circle']")
    private WebElement redOption;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter plus button']/android.widget.ImageView")
    private WebElement plusButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Highlights']")
    private WebElement highlightsBanner;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView[@text]")
    private WebElement itemCounter;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView")
    private WebElement cartItemCount;

    private final Logger logger = LogManager.getLogger(ApkProductPage.class);

    private final WebDriverWait wait;

    public ApkProductPage(AppiumDriver driver) {
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
            logger.info("Product name retrieved from product page: " + counter);
            return counter;
        }
        catch (Exception e) {
            logger.error("Failed to get the count of items from product page: "+ e);
            return "Failed to get the count of items from product page";
        }
    }

    public String getCartIconItemCount() {
        try {
            Thread.sleep(1000);
            logger.info("Attempting to retrieve item from the cart icon");
            String counter =  cartItemCount.getText();
            logger.info("Item count retrieved from cart icon: " + counter);
            return counter;
        }
        catch (Exception e) {
            logger.error("Failed to get the count of items from cart icon: "+ e);
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
