package appiumtests.gui.app.pages.android;

import appiumtests.gui.app.pages.common.ApkCheckoutPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApkCheckoutPage extends ApkCheckoutPageBase {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter a shipping address']")
    private WebElement shippingAddressPageTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Full Name* input field']")
    private WebElement fullName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Address Line 1* input field']")
    private WebElement addressLine1;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='City* input field']")
    private WebElement cityField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='State/Region input field']")
    private WebElement stateField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Zip Code* input field']")
    private WebElement zipCode;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Country* input field']")
    private WebElement countryField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='To Payment button']")
    private WebElement toPaymentButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter a payment method']")
    private WebElement paymentMethodPageTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Card Number* input field']")
    private WebElement cardNumberField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Expiration Date* input field']")
    private WebElement expirationDateField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Security Code* input field']")
    private WebElement securityCodeField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Review Order button']")
    private WebElement reviewOrderButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Review your order']")
    private WebElement reviewOrderPageTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='checkout delivery address']/android.widget.TextView[3]")
    private WebElement orderReviewAddressStreet;


    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkCheckoutPage.class);

    public ApkCheckoutPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public void fillFullName(String name) {
        fullName.sendKeys(name);
    }

    public String isAddressValid() {
       return orderReviewAddressStreet.getText();
    }

    @Override
    public void fillAddress(String address) {
        logger.info("Entering credentials for shipping info...");
        addressLine1.sendKeys(address);
    }

    @Override
    public void fillCity(String city) {
        cityField.sendKeys(city);
    }

    @Override
    public void fillState(String state) {
        stateField.sendKeys(state);
    }

    @Override
    public void fillZipCode(String zip) {
        zipCode.sendKeys(zip);
    }

    @Override
    public void fillCountry(String country) {
        countryField.sendKeys(country);
    }

    public void tapToPaymentButton() {
        toPaymentButton.click();
        logger.info("Credentials provided. Going to enter credit card info...");
    }

    public void tapReviewButton() {
        reviewOrderButton.click();
        reviewOrderButton.click();
    }

    public boolean isShippingPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(shippingAddressPageTitle));
            return shippingAddressPageTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public boolean isPaymentPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(paymentMethodPageTitle));
            return paymentMethodPageTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public boolean isReviewOrderPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(reviewOrderPageTitle));
            return reviewOrderPageTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public void fillCardNumber(String card) {
        cardNumberField.sendKeys(card);
    }

    public void fillExpirationDate(String exp) {
        expirationDateField.sendKeys(exp);
    }

    public void fillSecurityCode(String code){
        securityCodeField.sendKeys(code);
    }
}
