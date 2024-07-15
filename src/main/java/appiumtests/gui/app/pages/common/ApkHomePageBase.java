package appiumtests.gui.app.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkHomePageBase {

    protected AndroidDriver driver;

    public ApkHomePageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract String getPrice(int index);

    public abstract boolean isPriceLower(String price1, String price2);

    public abstract void tapResultByIndex(int index);

    public abstract boolean isTitleVisible();

    public abstract void tapSortButton();

    public abstract void tapPriceAscending();

    public abstract void tapPriceDescending();
}

