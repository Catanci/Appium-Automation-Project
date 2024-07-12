package appiumtests.gui.web.pages.android;

import appiumtests.gui.web.pages.common.CartPageBase;
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
public class CartPage extends CartPageBase {

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"mainContent\"]/android.view.View[2]/android.view.View/android.widget.ListView/android.view.View/android.view.View/android.view.View[@text][2]")
    private WebElement cartItemName;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"mainContent\"]/android.view.View[3]/android.view.View/android.widget.ListView/android.view.View/android.view.View/android.widget.TextView[@text][2]")
    private WebElement cartItemPrice;

    @AndroidFindBy(xpath = "//android.widget.Button[@text][2]")
    private WebElement remove;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='eBay Home']")
    private WebElement homeButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shopping cart']")
    private WebElement shoppingCartTitle;


    private final Logger logger = LogManager.getLogger(CartPage.class);

    private final WebDriverWait wait;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getCartProductName() {
        try {
            Thread.sleep(2000);
            logger.info("Attempting to get product name from cart page");
            wait.until(ExpectedConditions.visibilityOf(cartItemName));
            String name = cartItemName.getText();
            logger.info("Product name from overlay retrieved: " + name);
            return name;
        } catch (Exception e) {
            logger.error("Failed to get product name from cart page: ", e);
            return "Couldn't locate the product's name from cart page";
        }
    }

    public boolean isLogoVisible() {
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(shoppingCartTitle));
            return shoppingCartTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Title  is not visible on 'Shopping Cart' page ", e);
            return false;
        }
    }

    public String getCartProductPrice() {
        try {
            Thread.sleep(2000);
            logger.info("Attempting to get product price from cart page");
            wait.until(ExpectedConditions.visibilityOf(cartItemPrice));
            String name = cartItemPrice.getText();
            logger.info("Product price from overlay retrieved: " + name);
            return name;
        } catch (Exception e) {
            logger.error("Failed to get product price from cart page: ", e);
            return "Couldn't locate the product's price from cart page";
        }
    }

    public void tapHomeButton() {
        homeButton.click();
    }

    public void tapRemove() {
        remove.click();
    }
}
