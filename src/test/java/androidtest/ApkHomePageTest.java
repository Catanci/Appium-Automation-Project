package androidtest;

import appiumtests.constants.Direction;
import appiumtests.gui.app.pages.android.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApkHomePageTest extends ApkBaseTest {
    private static final String USER_EMAIL = "bob@example.com";
    private static final String USER_PASSWORD = "10203040";

    @Test(description = "Verify user can add products to his cart")
    public void verifyUserCanAddProductToCart() {
        SoftAssert softAssert = new SoftAssert();
        ApkHomePage apkHomePage = new ApkHomePage(driver);
        apkHomePage.tapResultByIndex(0);

        ApkProductPage apkProductPage = new ApkProductPage(driver);
        Assert.assertTrue(apkProductPage.isBannerVisible(),
                "User has been redirected to a different page");
        apkProductPage.tapRedOption();
        apkProductPage.tapPlusButton();
        String expectedAmount = "2";
        String counterAmount = apkProductPage.getCounterAmount();
        softAssert.assertEquals(counterAmount, expectedAmount,
                "Amount of items about to be added to cart is wrong");

        apkProductPage.tapAddToCart();
        String cartCounterAmount = apkProductPage.getCartIconItemCount();
        softAssert.assertEquals(cartCounterAmount, expectedAmount,
                "Amount of items added to cart is wrong");


        apkProductPage.tapCartIcon();
        ApkCartPage apkCartPage = new ApkCartPage(driver);
        String cartAmount = apkCartPage.getCounterAmount();
        softAssert.assertEquals(cartAmount, expectedAmount,
                "Amount of items in the cart is wrong");
        softAssert.assertTrue(apkCartPage.isColorCorrect(),
                "Color for added product is not correct");
        apkCartPage.tapRemoveButton();
        softAssert.assertTrue(apkCartPage.isCartEmpty(),
                "The cart is not empty after removing items from the cart");

        apkCartPage.tapGoBack();
        softAssert.assertTrue(apkHomePage.isTitleVisible(),
                "User has been redirected to different page than 'Home Page'");

        softAssert.assertAll();
    }

    @Test(description = "Verify user can buy products going through whole flow")
    public void verifyUserCanPlaceAnOrder() {
        SoftAssert softAssert = new SoftAssert();
        ApkHomePage apkHomePage = new ApkHomePage(driver);
        apkHomePage.tapResultByIndex(0);

        ApkProductPage apkProductPage = new ApkProductPage(driver);
        Assert.assertTrue(apkProductPage.isBannerVisible(),
                "User has been redirected to a different page");
        apkProductPage.tapRedOption();
        String expectedAmount = "1";
        String counterAmount = apkProductPage.getCounterAmount();
        softAssert.assertEquals(counterAmount, expectedAmount,
                "Amount of items about to be added to cart is wrong");

        apkProductPage.tapAddToCart();
        String cartCounterAmount = apkProductPage.getCartIconItemCount();
        softAssert.assertEquals(cartCounterAmount, expectedAmount,
                "Amount of items added to cart is wrong");

        apkProductPage.tapCartIcon();
        ApkCartPage apkCartPage = new ApkCartPage(driver);
        String cartAmount = apkCartPage.getCounterAmount();
        softAssert.assertEquals(cartAmount, expectedAmount,
                "Amount of items in the cart is wrong");
        softAssert.assertTrue(apkCartPage.isColorCorrect(),
                "Color for added product is not correct");
        apkCartPage.tapProceedToCheckout();

        ApkLogInPage apkLogInPage = new ApkLogInPage(driver);
        apkLogInPage.typeUsername(USER_EMAIL);
        apkLogInPage.typePassword(USER_PASSWORD);
        apkLogInPage.tapLogInButton();

        ApkCheckoutPage apkCheckoutPage = new ApkCheckoutPage(driver);
        Assert.assertTrue(apkCheckoutPage.isShippingPageOpened(),
                "User has been redirected to different page than shipping page");

        String firstName = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(6).toLowerCase());
        String lastName = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(6).toLowerCase());
        String fullName = firstName + " " + lastName;
        String address = createRandomAddress();
        String city = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(7).toLowerCase());
        String state = RandomStringUtils.randomAlphabetic(2).toUpperCase();
        String zipCode = RandomStringUtils.randomNumeric(5);
        String country = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(7).toLowerCase());
        String security = RandomStringUtils.randomNumeric(3);
        String credit = createRandomCardNumber();
        String exp = "0325";

        apkCheckoutPage.fillFullName(fullName);
        apkCheckoutPage.fillAddress(address);
        apkCheckoutPage.fillCity(city);
        apkCheckoutPage.fillState(state);
        apkCheckoutPage.fillZipCode(zipCode);
        apkCheckoutPage.fillCountry(country);

        apkCheckoutPage.tapToPaymentButton();
        Assert.assertTrue(apkCheckoutPage.isPaymentPageOpened(),
                "User has been redirected to different page than shipping page");

        apkCheckoutPage.fillFullName(fullName);
        apkCheckoutPage.fillCardNumber(credit);
        apkCheckoutPage.fillExpirationDate(exp);
        apkCheckoutPage.fillSecurityCode(security);

        pause(2);
        apkCheckoutPage.tapReviewButton();
        Assert.assertTrue(apkCheckoutPage.isReviewOrderPageOpened(),
                "User has been redirected to different page than review order page");
        softAssert.assertEquals(address, apkCheckoutPage.isAddressValid(),
                "Address for shipment doesn't match");

        softAssert.assertAll();
    }

    @Test(description = "Verify user can sort items by price")
    public void verifySortingByPrice() {
        SoftAssert softAssert = new SoftAssert();
        ApkHomePage apkHomePage = new ApkHomePage(driver);
        swipeScreen(Direction.UP);

        apkHomePage.tapSortButton();
        apkHomePage.tapPriceAscending();

        pause(2);
        String ascFirstItem = apkHomePage.getPrice(0);
        String ascMiddleItem = apkHomePage.getPrice(3);
        String ascLastItem = apkHomePage.getPrice(5);

        softAssert.assertTrue(apkHomePage.isPriceLower(ascFirstItem, ascMiddleItem),
                "First item price(" + ascFirstItem + ") should be less than second item price (" + ascMiddleItem + ")");
        softAssert.assertTrue(apkHomePage.isPriceLower(ascMiddleItem, ascLastItem),
                "First item price (" + ascMiddleItem + ") should be less than last item price (" + ascLastItem + ")");

        apkHomePage.tapSortButton();
        apkHomePage.tapPriceDescending();
        pause(2);

        String descFirstItem = apkHomePage.getPrice(0);
        String descMiddleItem = apkHomePage.getPrice(3);
        String descLastItem = apkHomePage.getPrice(5);

        softAssert.assertTrue(apkHomePage.isPriceLower(descMiddleItem, descFirstItem),
                "First item price(" + descMiddleItem + ") should be less than second item price (" + descFirstItem + ")");
        softAssert.assertTrue(apkHomePage.isPriceLower(descLastItem, descMiddleItem),
                "First item price (" + descLastItem + ") should be less than last item price (" + descMiddleItem + ")");

        softAssert.assertAll();
    }
}

