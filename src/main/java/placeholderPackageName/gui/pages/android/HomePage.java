package placeholderPackageName.gui.pages.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.network.Header;
import org.openqa.selenium.support.PageFactory;
import placeholderPackageName.gui.pages.common.HomePageBase;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomePage extends HomePageBase {

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"gh-ac\"]")
    private WebElement searchBar;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"eBay Home\"]")
    private WebElement homeButton;

    @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id=\"s0-1-0-50-1-2-4-17[0[0]]-0[0]-7-@match-media-0-@ebay-carousel-list\"]")
    private List<WebElement> recentlyViewedItemsCarousel;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"gh-btn\"]")
    private WebElement searchButton;

    String WEB_URL = "https://www.ebay.com";

    public HomePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAllCarouselItems() {
        return getRecentlyViewedItemsCarousel().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Override
    public void open() {
        driver.get(WEB_URL);
    }


    @Override
    public void tapHomeButton() {
        homeButton.click();
    }


    @Override
    public void search(String search) {
        searchBar.click();
        searchBar.sendKeys(search);
    }

    public void tapSearchButton() {
        searchButton.click();
    }



}