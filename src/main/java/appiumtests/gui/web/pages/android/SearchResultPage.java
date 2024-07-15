package appiumtests.gui.web.pages.android;

import appiumtests.gui.web.pages.common.SearchResultPageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
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
public class SearchResultPage extends SearchResultPageBase {

    @FindBy(xpath = "//android.view.View[@content-desc]/android.view.View[1]")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//android.view.View[@resource-id=\"mainContent\"]/android.view.View[1]/android.view.View/android.widget.TextView[@text]")
    private WebElement searchResultCount;

    @FindBy(xpath = "//android.view.View[@resource-id='mainContent']/android.view.View[1]/android.view.View")
    private WebElement bottomResultsCount;

    @FindBy(xpath = "//android.widget.Button[@text='Filter']")
    private WebElement filter;

    private final Logger logger = LogManager.getLogger(SearchResultPage.class);
    private final WebDriverWait wait;

    public SearchResultPage(AndroidDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getVisibleResults() {
        logger.info("Number of elements found: " + searchResultsList.size());
        return searchResultsList.stream()
                .peek(element -> logger.info("Found elements... " + element.getSize()))
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void tapFirstResult() {
     List<WebElement> firstResult = getSearchResultsList();
     WebElement targetElement = firstResult.stream()
             .findFirst()
             .orElseThrow(() -> new RuntimeException("First result isn't found"));
        targetElement.click();
    }

    public void tapResultByIndex(int index) {
        List<WebElement> searchResult = getSearchResultsList();
        if (index < 0 || index >= searchResult.size()) {
            throw new IllegalArgumentException("Invalid index. Must be between 0 and " + (searchResult.size() - 1));
        }
        WebElement targetElement = searchResult.get(index);
        targetElement.click();
    }

    public boolean isPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(searchResultCount));
            return searchResultCount.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public boolean isBottomResultCountVisible() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(bottomResultsCount));
            return bottomResultsCount.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }

    public void tapFilters() {
        filter.click();
    }
}
