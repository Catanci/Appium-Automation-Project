package appiumtests.gui.web.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class FiltersPageBase {

    protected AndroidDriver driver;

    public FiltersPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void tapBuyItNowFilter();

    public abstract void tapShowMore();

    public abstract void tapEbayRefurbished();

    public abstract void tapShowResults();

    public abstract boolean isPageOpened();
}
