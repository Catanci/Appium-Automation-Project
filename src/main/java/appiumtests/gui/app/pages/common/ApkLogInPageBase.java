package appiumtests.gui.app.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkLogInPageBase {

    protected AndroidDriver driver;

    public ApkLogInPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void typeUsername(String email);

    public abstract void typePassword(String password);

    public abstract void tapLogInButton();
}
