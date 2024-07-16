package appiumtests.gui.web.pages.ios;

import appiumtests.gui.web.pages.common.CartPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class CartPage extends CartPageBase {

    @FindBy(xpath = "")
    private WebElement cartItemName;

    @FindBy(xpath = "")
    private WebElement cartItemPrice;

    @FindBy(xpath = "")
    private WebElement remove;

    @FindBy(xpath = "")
    private WebElement homeButton;

    @FindBy(xpath = "")
    private WebElement shoppingCartTitle;


    private final Logger logger = LogManager.getLogger(CartPage.class);

    private final WebDriverWait wait;

    public CartPage(AppiumDriver driver) {
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
