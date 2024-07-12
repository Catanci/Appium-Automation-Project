package appiumtests.gui.web.pages.android;

import appiumtests.gui.web.pages.common.ProductPageBase;
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
public class ProductPage extends ProductPageBase {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back to search results']")
    private WebElement backToResultsText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"eBay Home\"]")
    private WebElement logoHomeButton;

    @AndroidFindBy(xpath = "(//android.view.View[@resource-id='RightSummaryPanel']//android.view.View[@resource-id='mainContent']/android.view.View/android.widget.TextView[@text])[1]")
    private WebElement productName;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='mainContent']/android.widget.TextView[@text][1]")
    private WebElement productPrice;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Buy It Now']")
    private WebElement buyItNowButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='mainContent']/android.widget.TextView[@text][3]")
    private WebElement conditionText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Add to cart']")
    private WebElement viewCartButton;

    //Cart Overlay
    @AndroidFindBy(xpath = "//android.app.Dialog[@text='1 item added to cart']/android.view.View/android.view.View[2]/android.widget.TextView[@text][2]")
    private WebElement cartOverlayProductName;

    @AndroidFindBy(xpath = "//android.app.Dialog[@text='1 item added to cart']/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[@text]")
    private WebElement cartOverlayProductPrice;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Close the show me how overlay']")
    private WebElement closeButton;

    private final Logger logger = LogManager.getLogger(ProductPage.class);
    private final WebDriverWait wait;

    public ProductPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getProductName() {
        try {
            Thread.sleep(2000);
            logger.info("Attempting to get product name");
            wait.until(ExpectedConditions.visibilityOf(productName));
            String name = productName.getText();
            logger.info("Product name retrieved: " + name);
            return name;
        } catch (Exception e) {
            logger.error("Failed to get product name: ", e);
            return "Couldn't locate the product's name";
        }
    }

    public String getProductPrice() {
        try {
            Thread.sleep(2000);
            logger.info("Attempting to get product price");
            wait.until(ExpectedConditions.visibilityOf(productPrice));
            String price = productPrice.getText();
            logger.info("Product price retrieved: " + price);
            return price;
        } catch (Exception e) {
            logger.error("Failed to get product price: ", e);
            return "Couldn't locate the product's price";
        }
    }

    public String getProductCondition() {
        try {
            Thread.sleep(2000);
            logger.info("Attempting to get condition of the product");
            wait.until(ExpectedConditions.visibilityOf(conditionText));
            String condition = conditionText.getText();
            logger.info("Product condition retrieved: " + condition);
            return condition;
        } catch (Exception e) {
            logger.error("Failed to get condition: ", e);
            return "Failed to get condition";
        }
    }

    public void tapBackButton() {
        backToResultsText.click();
    }

    public void tapHomeButton() {
        logoHomeButton.click();
    }

    public boolean isPageOpened() {
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(backToResultsText));
            return backToResultsText.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public boolean isBuyItNowButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(buyItNowButton));
            return buyItNowButton.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public void tapAddToCart() {
        viewCartButton.click();
    }

    public void tapCloseButton() {
        closeButton.click();
    }

    public String getCartOverlayProductName() {
        try {
            logger.info("Attempting to get product name from cart overlay");
            wait.until(ExpectedConditions.visibilityOf(cartOverlayProductName));
            String name = cartOverlayProductName.getText();
            logger.info("Product name from overlay retrieved: " + name);
            return name;
        } catch (Exception e) {
            logger.error("Failed to get product name from overlay: ", e);
            return "Couldn't locate the product's name";
        }
    }

    public String getCartOverlayProductPrice() {
        try {
            logger.info("Attempting to get product price from cart overlay");
            wait.until(ExpectedConditions.visibilityOf(cartOverlayProductPrice));
            String price = cartOverlayProductPrice.getText();
            logger.info("Product price from overlay retrieved: " + price);
            return price;
        } catch (Exception e) {
            logger.error("Failed to get product price from overlay: ", e);
            return "Couldn't locate the product's price";
        }
    }
}
