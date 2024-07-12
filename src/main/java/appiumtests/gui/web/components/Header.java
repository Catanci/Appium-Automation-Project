package appiumtests.gui.web.components;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class Header extends HeaderBase {
    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='mw-hdr']/android.view.View[2]")
    private Header header;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Open Menu']")
    private WebElement myEbayMenu;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Your shopping cart is empty']")
    private WebElement emptyShoppingCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My eBay']")
    private WebElement myEbay;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Gift Cards']")
    private WebElement giftCards;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='gh-minicart-hover']")
    private WebElement cartIcon;

    public Header(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Override
    public void tapSignIn() {

    }

    @Override
    public void tapGiftCards() {

    }

    @Override
    public void tapMyEbay() {

    }

    @Override
    public void tapCart() {
    cartIcon.click();
    }

    public void openMenu(){
        myEbayMenu.click();
    }
}