package webtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import placeholderPackageName.common.constants.Direction;
import placeholderPackageName.gui.pages.android.HomePage;
import placeholderPackageName.gui.pages.android.ProductPage;
import placeholderPackageName.gui.pages.android.SearchResultPage;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test(description = "Check if user can search for a product")
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
        searchResultPage.tapResultByIndex(1);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(),
                "After opening the result page, user has been redirected to the different page");
        softAssert.assertAll();
    }

    @Test(description = "Check if carousel is updated with recently viewed items")
    public void testCarousel() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        softAssert.assertFalse(homePage.isBannerVisible(),
                "Banner is visible upon entering the page");

        String searchTerm = "laptop";
        homePage.search(searchTerm);
        homePage.tapSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue(searchResultPage.isPageOpened(),
                "After opening 'Search Result' page, user has not been redirected to the 'Search Result' page");
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");
        searchResultPage.tapResultByIndex(1);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(),
                "After opening the product page, user has been redirected to a different page");
        pause(2);
        String firstProduct = productPage.getProductName();
        productPage.tapBackButton();
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");

        searchResultPage.tapResultByIndex(2);
        softAssert.assertTrue(productPage.isPageOpened(),
                "After opening the product page, user has been redirected to the different page");
        pause(2);
        String secondProduct = productPage.getProductName();

        productPage.tapBackButton();
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");

        swipeScreen(Direction.UP);
        searchResultPage.tapResultByIndex(2);
        softAssert.assertTrue(productPage.isPageOpened(),
                "After opening the result page, user has been redirected to the different page");
        pause(2);
        String thirdProduct = productPage.getProductName();

        productPage.tapHomeButton();
        softAssert.assertTrue(homePage.isBannerVisible(),
                "After clicking home button user has been redirected  to different page");

        softAssert.assertTrue(homePage.getAllCarouselItemNames().size() >= 2,
                "There are less items shown in the carousel than what user has seen");
        List<String> carouselItems = homePage.getAllCarouselItemNames();
        softAssert.assertTrue(carouselItems.contains(thirdProduct),
                "Third viewed product '" + thirdProduct + "' not found in carousel items: " + carouselItems);
        softAssert.assertTrue(carouselItems.contains(secondProduct),
                "Second viewed product '" + secondProduct + "' not found in carousel items: " + carouselItems);
        homePage.swipeCarouselRight();
        List<String> carouselItemsAfterSwipeRight = homePage.getAllCarouselItemNames();
        softAssert.assertTrue(carouselItems.contains(firstProduct),
                "First viewed product '" + firstProduct + "' not found in carousel items: " + carouselItemsAfterSwipeRight);
    }

    @Test(description = "Check if user can apply filters to his results")
    public void testApplyingFilters() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        softAssert.assertFalse(homePage.isBannerVisible(),
                "Banner is visible upon entering the page");

        String searchTerm = "laptop";
        homePage.search(searchTerm);
        homePage.tapSearchButton();


    }
}


//Test using advanced search options such as filters (e.g., price range, condition) and verify that search results are filtered accordingly.

//Add to Cart: Add a product to the cart and verify that it appears in the cart summary.