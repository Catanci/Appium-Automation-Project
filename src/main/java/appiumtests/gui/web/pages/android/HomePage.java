package appiumtests.gui.web.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import appiumtests.gui.web.pages.common.HomePageBase;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends HomePageBase {

//    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='gh-ac']")
    @FindBy(id = "kw")
    private WebElement searchBar;

//    @AndroidFindBy(xpath = "//android.view.View[@content-desc='eBay Home']")
    @FindBy(id = "gh-logo")
    private WebElement homeButton;

//    @FindBy(xpath = "//android.widget.Button[@resource-id='gh-btn']")
    @FindBy(id = "gh-btn")
    private WebElement searchButton;

//    @FindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']/android.view.View/android.view.View/android.view.View[1]")
    @FindBy(id = "s0-1-0-50-1-2-4-17[0[0]]-0[3]-7-@match-media-0-@ebay-carousel-container")
    private List<WebElement> carouselItems;

//    @FindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][1]")
    @FindBy(xpath = "//button[@class='carousel__control carousel__control--prev']")
    private WebElement swipeLeft;

//    @FindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][2]")
    @FindBy(id = "s0-1-0-50-1-2-4-17[0[0]]-0[3]-7-@match-media-0-@ebay-carousel-next")
    private WebElement swipeRight;

    @FindBy(xpath = "//h2[@class='vl-card-header__headline']")
    private WebElement recentlyViewedItemsBanner;

//    @FindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']//android.widget.TextView[@text][1]")
    @FindBy(xpath = "//h3[contains(@class,'vlp-merch-item-title vlp-merch-item-title-dweb')]")
    private List<WebElement> carouselItemName;

//    @FindBy(xpath = "//android.view.View[@content-desc='Your shopping cart']")
    @FindBy(id = "icon--profile")
    private WebElement profileIcon;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(HomePage.class);

    String WEB_URL = "https://www.ebay.com";

    public HomePage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAllCarouselItemNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(carouselItemName));
        return carouselItemName.stream()
                .map(element -> {
                    try {
                        return element.getText();
                    } catch (Exception e) {
                        logger.error("Failed to get text from carousel item: ", e);
                        return "Failed to get text from carousel item";
                    }})
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isBannerVisible() {
    try {
        wait.until(ExpectedConditions.visibilityOf(recentlyViewedItemsBanner));
        return recentlyViewedItemsBanner.isDisplayed();
    } catch (Exception e) {
        logger.error("Banner  is not visible on Home Page ", e);
        return false;
    }
}

    public boolean isCartEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileIcon));
            return profileIcon.isDisplayed();
        } catch (Exception e) {
            logger.error("Cart is not empty but should be ", e);
            return false;
        }
    }

    public boolean isLogoVisible() {
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(homeButton));
            return homeButton.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }
    @Override
    public void open()  {
        logger.info("Opening the landing Page");
        driver.get(WEB_URL);
        try {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(profileIcon));
            logger.info("Landing Page has opened successfully");
        } catch (Exception e) {
            logger.error("Failed to open the landing page: ", e);
        }
    }

    @Override
    public void tapHomeButton() {
        homeButton.click();
    }

    public void swipeCarouselLeft() {
        swipeLeft.click();
    }

    public void swipeCarouselRight() {
        swipeRight.click();
    }

    @Override
    public void search(String search) {
        logger.info("Attempting to search for the product: {}", search);
        searchBar.click();
        searchBar.sendKeys(search);
//        searchBar.sendKeys(Keys.ENTER);
    }

    public void tapSearchButton() {
        searchButton.click();
    }
}