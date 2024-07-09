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
public class ProductPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Back to search results\"]")
    private WebElement backToResultsText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"eBay Home\"]")
    private WebElement logoHomeButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='RightSummaryPanel']//android.view.View[@resource-id='mainContent']//android.widget.TextView")
    private WebElement productName;

    private final Logger logger = LogManager.getLogger(SearchResultPage.class);
    private final WebDriverWait wait;

    public ProductPage(AndroidDriver driver) {
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getProductName() {
        try {
            logger.info("Attempting to get product name");
            wait.until(ExpectedConditions.visibilityOf(productName));
            String name = productName.getText();
            logger.info("Product name retrieved: " + name);
            return name;
        } catch (Exception e) {
            logger.error("Failed to get product name: ", e);
            return "";
        }
    }

    public void tapBackButton() {
        backToResultsText.click();
    }

    public void tapHomeButton() {
        logoHomeButton.click();
    }


    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(backToResultsText));
            return backToResultsText.isDisplayed();
        } catch (Exception e) {
            logger.error("Page is not opened: ", e);
            return false;
        }
    }
}
