package appiumtests.gui.web.components;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class HeaderBase {

    protected AndroidDriver driver;

    public HeaderBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void tapSignIn();

    public abstract void tapGiftCards();

    public abstract void tapMyEbay();

    public abstract void tapCart();
}