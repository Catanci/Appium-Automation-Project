package webtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import placeholderPackageName.common.constants.Direction;
import placeholderPackageName.gui.pages.android.HomePage;
import placeholderPackageName.gui.pages.android.ProductPage;
import placeholderPackageName.gui.pages.android.SearchResultPage;

public class HomePageTest extends BaseTest {

    @Test()
    //check if user can search for a product
    public void testProductSearch() {
        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        String searchTerm = "laptop";
        homePage.search(searchTerm);
        homePage.tapSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue(searchResultPage.isPageOpened(),
                "After opening 'Search Result' page, user has not been redirected to the 'Search Result' page");
        swipeScreen(Direction.UP);
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");
        searchResultPage.tapFirstResult();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(),
                "After opening the result page, user has been redirected to the different page");
        softAssert.assertAll();
    }
}