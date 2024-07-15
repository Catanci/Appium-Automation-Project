package appiumtests.gui.web.pages.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ProductPageBase {

    protected AppiumDriver driver;

    public ProductPageBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract boolean isPageOpened();

    public abstract boolean isBuyItNowButtonVisible();

    public abstract String getProductPrice();

    public abstract String getProductName();

    public abstract String getProductCondition();

    public abstract String getCartOverlayProductPrice();

    public abstract String getCartOverlayProductName();

    public abstract void tapBackButton();

    public abstract void tapHomeButton();

    public abstract void tapAddToCart();

    public abstract void tapCloseButton();


}
