package placeholderPackageName.gui.pages.android;

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
public class FiltersPage {

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Buy It Now']")
    private WebElement buyItNowFilter;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Show More']")
    private WebElement showMoreButton;

    @AndroidFindBy(xpath = "//android.widget.ToggleButton[@resource-id='switch__LH_ItemCondition']")
    private WebElement eBayRefurbishedFilter;

    @AndroidFindBy(xpath = "//android.app.Dialog[@text='Filter']/android.view.View/android.view.View[3]/android.widget.Button")
    private WebElement showResultsButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='filter__title filter__title--hub']")
    private WebElement title;

    private final Logger logger = LogManager.getLogger(SearchResultPage.class);

    private final WebDriverWait wait;

    public FiltersPage(AndroidDriver driver) {
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapBuyItNowFilter() {
        buyItNowFilter.click();
    }

    public void tapShowMore() {
        showMoreButton.click();
    }

    public void tapEbayRefurbished() {
        eBayRefurbishedFilter.click();
    }

    public void tapShowResults() {
        showResultsButton.click();
    }

    public boolean isPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(title));
            return title.isDisplayed();
        } catch (Exception e) {
            logger.error("Filter page is not opened: ", e);
            return false;
        }
    }
}
