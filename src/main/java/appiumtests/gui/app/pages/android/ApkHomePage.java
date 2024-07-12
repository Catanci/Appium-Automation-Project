package appiumtests.gui.app.pages.android;

import appiumtests.gui.app.pages.common.ApkHomePageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class ApkHomePage extends ApkHomePageBase {

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='gh-ac']")
    private WebElement searchBar;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']//android.widget.TextView[@text][1]")
    private List<WebElement> carouselItemName;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Your shopping cart']")
    private WebElement emptyCart;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkHomePage.class);


    public ApkHomePage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}