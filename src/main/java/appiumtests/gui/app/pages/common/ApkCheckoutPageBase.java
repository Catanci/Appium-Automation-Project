package appiumtests.gui.app.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ApkCheckoutPageBase {

    protected AndroidDriver driver;

    public ApkCheckoutPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void fillFullName(String name);

    public abstract void fillAddress(String address);

    public abstract void fillCity(String city);

    public abstract void fillState(String state);

    public abstract void fillZipCode(String zip);

    public abstract void fillCountry(String country);

    public abstract String isAddressValid();

    public abstract void tapToPaymentButton();

    public abstract void tapReviewButton();

    public abstract boolean isShippingPageOpened();

    public abstract boolean isPaymentPageOpened();

    public abstract boolean isReviewOrderPageOpened();

    public abstract void fillCardNumber(String card);

    public abstract void fillExpirationDate(String exp);

    public abstract void fillSecurityCode(String code);

}
