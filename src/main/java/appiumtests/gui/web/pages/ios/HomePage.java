package appiumtests.gui.web.pages.ios;

import appiumtests.gui.web.pages.common.HomePageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends HomePageBase {

    @FindBy(xpath = "//android.widget.EditText[@resource-id='gh-ac']")
    private WebElement searchBar;
    
    @FindBy(xpath = "//android.view.View[@content-desc='eBay Home']")
    private WebElement homeButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']/android.view.View/android.view.View/android.view.View[1]")
    private List<WebElement> carouselItems;

    @FindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][1]")
    private WebElement swipeLeft;

    @FindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][2]")
    private WebElement swipeRight;

    @FindBy(xpath = "//android.widget.TextView[@text='Your Recently Viewed Items']")
    private WebElement recentlyViewedItemsBanner;

    @FindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']//android.widget.TextView[@text][1]")
    private List<WebElement> carouselItemName;

    @FindBy(xpath = "//android.view.View[@content-desc='Your shopping cart']")
    private WebElement emptyCart;

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
            wait.until(ExpectedConditions.visibilityOf(emptyCart));
            return emptyCart.isDisplayed();
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
            wait.until(ExpectedConditions.visibilityOf(emptyCart));
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
        logger.info("Attempting to search for the product: " + search);
        searchBar.click();
        searchBar.sendKeys(search);
    }

    public void tapSearchButton() {
        searchButton.click();
    }
}