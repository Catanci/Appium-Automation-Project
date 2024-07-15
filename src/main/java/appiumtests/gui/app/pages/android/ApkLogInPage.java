package appiumtests.gui.app.pages.android;

import appiumtests.gui.app.pages.common.ApkLogInPageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApkLogInPage extends ApkLogInPageBase {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Username input field']")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Password input field']")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Login button']")
    private WebElement loginButton;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkLogInPage.class);

    public ApkLogInPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void typeUsername(String email) {
        logger.info("Entering the email: " + email);
        usernameField.click();
        usernameField.sendKeys(email);
    }

    public void typePassword(String password) {
        logger.info("Entering the email: " + password);
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public void tapLogInButton() {
        loginButton.click();
    }
}
