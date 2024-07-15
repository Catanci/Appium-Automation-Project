package appiumtests.gui.app.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkCartPageBase {
    protected AndroidDriver driver;

    public ApkCartPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract String getCounterAmount();

    public abstract boolean isColorCorrect();

    public abstract boolean isCartEmpty();

    public abstract void tapRemoveButton();

    public abstract void tapGoBack();

    public abstract void tapProceedToCheckout();

}
