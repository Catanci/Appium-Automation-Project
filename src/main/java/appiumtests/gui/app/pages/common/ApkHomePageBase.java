package appiumtests.gui.app.pages.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkHomePageBase {

    protected AppiumDriver driver;

    public ApkHomePageBase(AppiumDriver driver) {
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

