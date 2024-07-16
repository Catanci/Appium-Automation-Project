package appiumtests.gui.app.pages.mobile;

import appiumtests.gui.app.pages.common.ApkCheckoutPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApkCheckoutPage extends ApkCheckoutPageBase {

    @iOSXCUITFindBy(accessibility = "Enter a shipping address")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter a shipping address']")
    private WebElement shippingAddressPageTitle;

    @iOSXCUITFindBy(accessibility = "Full Name* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Full Name* input field']")
    private WebElement fullName;

    @iOSXCUITFindBy(accessibility = "Address Line 1* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Address Line 1* input field']")
    private WebElement addressLine1;

    @iOSXCUITFindBy(accessibility = "City* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='City* input field']")
    private WebElement cityField;

    @iOSXCUITFindBy(accessibility = "State/Region input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='State/Region input field']")
    private WebElement stateField;

    @iOSXCUITFindBy(accessibility = "Zip Code* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Zip Code* input field']")
    private WebElement zipCode;

    @iOSXCUITFindBy(accessibility = "Country* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Country* input field']")
    private WebElement countryField;

    @iOSXCUITFindBy(accessibility = "To Payment button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='To Payment button']")
    private WebElement toPaymentButton;

    @iOSXCUITFindBy(accessibility = "Enter a payment method")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter a payment method']")
    private WebElement paymentMethodPageTitle;

    @iOSXCUITFindBy(accessibility = "Card Number* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Card Number* input field']")
    private WebElement cardNumberField;

    @iOSXCUITFindBy(accessibility = "Expiration Date* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Expiration Date* input field']")
    private WebElement expirationDateField;

    @iOSXCUITFindBy(accessibility = "Security Code* input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Security Code* input field']")
    private WebElement securityCodeField;

    @iOSXCUITFindBy(accessibility = "Review Order button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Review Order button']")
    private WebElement reviewOrderButton;

    @iOSXCUITFindBy(accessibility = "Review your order")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Review your order']")
    private WebElement reviewOrderPageTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Delivery Address\"]/following::XCUIElementTypeStaticText[1]/following-sibling::XCUIElementTypeStaticText[1]")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='checkout delivery address']/android.widget.TextView[3]")
    private WebElement orderReviewAddressStreet;

    @iOSXCUITFindBy(accessibility = "navigation back button")
    private WebElement backButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='checkout address screen']")
    private WebElement checkoutAddressScreen;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"checkout payment screen\"]/XCUIElementTypeScrollView")
    private WebElement reviewScreen;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkCheckoutPage.class);

    public ApkCheckoutPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapBackButton() {
        backButton.click();
    }

    public void dismissKeyboardCheckoutPage() {
        checkoutAddressScreen.click();
    }

    public void dismissKeyboardReviewPage() {
        reviewScreen.click();
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
        wait.until(ExpectedConditions.elementToBeClickable(addressLine1)).click();
        for (char c : address.toCharArray()) {
            addressLine1.sendKeys(Character.toString(c));
    }}

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
        logger.info("Entering the expiration date: {}", exp);
        wait.until(ExpectedConditions.elementToBeClickable(expirationDateField)).click();
        for (char c : exp.toCharArray()) {
            expirationDateField.sendKeys(Character.toString(c));
        }}

    public void fillSecurityCode(String code){
        logger.info("Entering the code: {}", code);
        wait.until(ExpectedConditions.elementToBeClickable(securityCodeField)).click();
        for (char c : code.toCharArray()) {
            securityCodeField.sendKeys(Character.toString(c));
        }}
}
