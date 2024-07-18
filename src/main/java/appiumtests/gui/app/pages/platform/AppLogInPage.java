package appiumtests.gui.app.pages.platform;

import appiumtests.gui.app.pages.common.ApkLogInPageBase;
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

public class AppLogInPage extends ApkLogInPageBase {

    @iOSXCUITFindBy(accessibility = "Username input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Username input field']")
    private WebElement usernameField;

    @iOSXCUITFindBy(accessibility = "Password input field")
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Password input field']")
    private WebElement passwordField;

    @iOSXCUITFindBy(accessibility = "Login button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Login button']")
    private WebElement loginButton;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(AppLogInPage.class);

    public AppLogInPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void typeUsername(String email) {
        logger.info("Entering the email: {}", email);
        wait.until(ExpectedConditions.elementToBeClickable(usernameField)).click();
        for (char c : email.toCharArray()) {
            usernameField.sendKeys(Character.toString(c));
        }}

    public void typePassword(String password) {
        logger.info("Entering the password: {}", password);
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public void tapLogInButton() {
        loginButton.click();
    }
}
