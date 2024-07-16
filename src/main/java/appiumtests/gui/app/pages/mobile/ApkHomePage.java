package appiumtests.gui.app.pages.mobile;

import appiumtests.gui.app.pages.common.ApkHomePageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class ApkHomePage extends ApkHomePageBase {

    @iOSXCUITFindBy(id = "tab bar option cart")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Your shopping cart']")
    private WebElement emptyCart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"store item text\" and @label]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='store item text']")
    private List<WebElement> firstRowProductsList;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Products\"]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement homePageTitle;

    @iOSXCUITFindBy(accessibility = "tab bar option menu")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='open menu']/android.widget.ImageView")
    private WebElement burgerMenu;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"store item price\" and @label]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='store item price' and @text]")
    private List<WebElement> price;

    @iOSXCUITFindBy(accessibility = "sort button")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='sort button']/android.widget.ImageView")
    private WebElement sortButton;

    @iOSXCUITFindBy(accessibility = "priceAsc")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='priceAsc']")
    private WebElement priceAsc;

    @iOSXCUITFindBy(accessibility = "priceDesc")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='priceDesc']")
    private WebElement priceDesc;

    private final WebDriverWait wait;

    private final Logger logger = LogManager.getLogger(ApkHomePage.class);

    public ApkHomePage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getPrice(int index) {
        List<WebElement> priceList = getPrice();
        WebElement targetElement = priceList.get(index);
        return targetElement.getText();
    }

    public boolean isPriceLower(String price1, String price2) {
        double value1 = Double.parseDouble(price1.replace("$",""));
        double value2 = Double.parseDouble(price2.replace("$", ""));
        return value1 < value2;
    }

    public void tapResultByIndex(int index) {
        List<WebElement> productsList = getFirstRowProductsList();
        if (index < 0 || index >= productsList.size()) {
            throw new IllegalArgumentException("Invalid index. Must be between 0 and " + (productsList.size() - 1));
        }
        WebElement targetElement = productsList.get(index);
        targetElement.click();
    }

    public boolean isTitleVisible() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(homePageTitle));
            return homePageTitle.isDisplayed();
        } catch (Exception e) {
            logger.error("Title is not visible on Home Page ", e);
            return false;
        }
    }

    public void tapMenu() {
        burgerMenu.click();
    }

    public void tapSortButton() {
        sortButton.click();
    }

    public void tapPriceAscending() {
        priceAsc.click();
    }

    public void tapPriceDescending() {
        priceDesc.click();
    }
}

