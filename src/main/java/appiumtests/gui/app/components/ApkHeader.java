package appiumtests.gui.app.components;

import appiumtests.gui.web.components.Header;
import appiumtests.gui.web.pages.android.SearchResultPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class ApkHeader extends ApkHeaderBase {
    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")
    private Header header;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='open menu']")
    private WebElement menu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='sort button']/android.widget.ImageView")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView")
    private WebElement cartIcon;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(SearchResultPage.class);

    public ApkHeader(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public void openMenu() {
        menu.click();
    }

    @Override
    public void tapSortButton() {
        sortButton.click();
    }

    @Override
    public void tapCart() {
        cartIcon.click();
    }

    public boolean isBannerVisible() {
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf((WebElement) header));
            return ((WebElement) header).isDisplayed();
        } catch (Exception e) {
            logger.error("App is not opened: ", e);
            return false;
        }
    }
}
