package appiumtests.gui.web.pages.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class HomePageBase {

    protected AppiumDriver driver;

    public HomePageBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void tapHomeButton();

    public abstract void search(String search);

    public abstract void open() throws InterruptedException;

    public abstract boolean isBannerVisible();

    public abstract boolean isLogoVisible();

    public abstract boolean isCartEmpty();

    public abstract void tapSearchButton();

    public abstract void swipeCarouselRight();

    public abstract void swipeCarouselLeft();



}