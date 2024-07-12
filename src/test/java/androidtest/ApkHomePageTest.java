package androidtest;

import appiumtests.gui.app.pages.android.ApkCartPage;
import appiumtests.gui.app.pages.android.ApkHomePage;
import appiumtests.gui.app.pages.android.ApkProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApkHomePageTest extends ApkBaseTest {

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

        apkCartPage.tapGoBack();
        softAssert.assertTrue(apkHomePage.isTitleVisible(),
                "User has been redirected to different page than 'Home Page'");

        softAssert.assertAll();
    }

    @Test(description = "Verify user can buy products going through whole flow")
    public void verifyUserCanPlaceAnOrder() {
        SoftAssert softAssert = new SoftAssert();
        ApkHomePage apkHomePage = new ApkHomePage(driver);
        apkHomePage.tapMenu();





        softAssert.assertAll();
    }
}


