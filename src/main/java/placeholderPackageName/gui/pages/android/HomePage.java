package placeholderPackageName.gui.pages.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.network.Header;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import placeholderPackageName.gui.pages.common.HomePageBase;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends HomePageBase {

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='gh-ac']")
    private WebElement searchBar;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='eBay Home']")
    private WebElement homeButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='gh-btn']")
    private WebElement searchButton;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']/android.view.View/android.view.View/android.view.View[1]")
    private List<WebElement> carouselItems;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][1]")
    private WebElement swipeLeft;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id]/android.widget.Button[@text][2]")
    private WebElement swipeRight;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your Recently Viewed Items']")
    private WebElement recentlyViewedItemsBanner;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list']//android.widget.TextView[@text][1]")
    private List<WebElement> carouselItemName;

//    @AndroidFindBy(xpath = "//android.view.View[@resource-id='gh-minicart-hover']")
//    private WebElement defaultCartButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Your shopping cart']")
    private WebElement emptyCart;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(SearchResultPage.class);

    String WEB_URL = "https://www.ebay.com";

    public HomePage(AndroidDriver driver) {
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
            wait.until(ExpectedConditions.visibilityOf(recentlyViewedItemsBanner));
            return recentlyViewedItemsBanner.isDisplayed();
        } catch (Exception e) {
            logger.error("Cart is not empty but should be ", e);
            return false;
        }
    }

    public void tapCartIcon() {
        emptyCart.click();
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
    public void open()
    {
        logger.info("Opening the landing Page");
        driver.get(WEB_URL);
        logger.info("Landing Page has opened");
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