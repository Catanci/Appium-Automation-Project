package appiumtests.gui.app.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkProductPageBase {

    protected AndroidDriver driver;

    public ApkProductPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract boolean isBannerVisible();

    public abstract String getCounterAmount();

    public abstract String getCartIconItemCount();

    public abstract void tapPlusButton();

    public abstract void tapAddToCart();

    public abstract void tapCartIcon();






}
