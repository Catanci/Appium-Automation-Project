package placeholderPackageName.gui.pages.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FiltersPage {

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Buy It Now']")
    private WebElement buyItNowFilter;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Price']")
    private WebElement priceFilter;


    private final Logger logger = LogManager.getLogger(SearchResultPage.class);

    private final WebDriverWait wait;

    public FiltersPage(AndroidDriver driver) {
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);


    }
}
