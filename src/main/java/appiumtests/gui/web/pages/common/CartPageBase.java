package appiumtests.gui.web.pages.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class CartPageBase {

    protected AppiumDriver driver;

    public CartPageBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract String getCartProductName();

    public abstract String getCartProductPrice();

    public abstract boolean isLogoVisible();

    public abstract void tapHomeButton();

    public abstract void tapRemove();
}
