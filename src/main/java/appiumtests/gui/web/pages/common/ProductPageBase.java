package appiumtests.gui.web.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ProductPageBase {

    protected AndroidDriver driver;

    public ProductPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

        public abstract boolean isPageOpened();
}
