package appiumtests.gui.web.components;

import appiumtests.gui.web.pages.android.CartPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class HeaderBase {

    protected AppiumDriver driver;

    public HeaderBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void tapSignIn();

    public abstract void tapGiftCards();

    public abstract void tapMyEbay();

    public abstract void tapCart();
}