package mobiletest;

import appiumtests.gui.web.components.Header;
import appiumtests.gui.web.pages.platform.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import appiumtests.constants.Direction;

import java.util.List;

public class WebHomePageTest extends WebBaseTest {

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
        swipeScreen(Direction.UP);
        String firstProduct = productPage.getProductName();
        swipeScreen(Direction.DOWN);
        productPage.tapBackButton();
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");

        searchResultPage.tapResultByIndex(2);
        softAssert.assertTrue(productPage.isPageOpened(),
                "After opening the product page, user has been redirected to the different page");
        pause(2);

        swipeScreen(Direction.UP);
        String secondProduct = productPage.getProductName();
        swipeScreen(Direction.DOWN);
        productPage.tapBackButton();
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");

        swipeScreen(Direction.UP);
        searchResultPage.tapResultByIndex(2);
        softAssert.assertTrue(productPage.isPageOpened(),
                "After opening the result page, user has been redirected to the different page");
        pause(2);

        swipeScreen(Direction.UP);
        String thirdProduct = productPage.getProductName();
        swipeScreen(Direction.DOWN);
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

        softAssert.assertAll();
    }

    @Test(description = "Check if user can apply filters to his results")
    public void testApplyingFiltersToSearchResult() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();

        String searchTerm = "iphone";
        homePage.search(searchTerm);
        homePage.tapSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue(searchResultPage.isPageOpened(),
                "After opening 'Search Result' page user has been redirected to different page");
        softAssert.assertFalse(searchResultPage.getVisibleResults().isEmpty(),
                "Search results should not be empty");
        searchResultPage.tapFilters();

        FiltersPage filtersPage = new FiltersPage(driver);
        Assert.assertTrue(filtersPage.isPageOpened(),
                "After opening 'Filters' page user has been redirected to different page");
        filtersPage.tapBuyItNowFilter();
        filtersPage.tapEbayRefurbished();
        filtersPage.tapShowMore();
        swipeScreen(Direction.UP);
        filtersPage.tapShowResults();

        softAssert.assertTrue(searchResultPage.isSortButtonVisible(),
                "There are not any results");
        searchResultPage.tapResultByIndex(1);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isPageOpened(),
                "After opening the product page, user has been redirected to a different page");
        pause(2);
        swipeScreen(Direction.UP);

        String condition = productPage.getProductCondition();
        String refurbished = "Refurbished";
        Assert.assertTrue(condition.contains(refurbished),
                "Condition of the product is not correct for the filter:" + condition + ", and should be" + refurbished);
        swipeScreen(Direction.UP);
        Assert.assertTrue(productPage.isBuyItNowButtonVisible(),
                "'Buy it now' option is not found for the product");

        softAssert.assertAll();
    }

    @Test(description = "Verify user can add products to his cart")
    public void testAddingItemsToCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isLogoVisible(),
                "Logo is not visible upon entering the page");
        Assert.assertTrue(homePage.isCartEmpty(),
                "Cart is not empty");

        String searchTerm = "game";
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

        swipeScreen(Direction.UP);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        productPage.tapAddToCart();
        pause(2);
        String productNameOverlay = productPage.getCartOverlayProductName();
        String productPriceOverlay = productPage.getCartOverlayProductPrice();
        softAssert.assertEquals(productName,productNameOverlay,
                "Product with different name was added to the cart");
        softAssert.assertEquals(productPrice,productPriceOverlay,
                "Product with different price was added to the cart");
        productPage.tapCloseButton();
        pause(2);

        productPage.tapHomeButton();
        softAssert.assertTrue(homePage.isLogoVisible(),
                "User has been redirected to different page");

        Header header = new Header(driver);
        header.tapCart();

        CartPage cartPage = new CartPage(driver);
        softAssert.assertTrue(cartPage.isLogoVisible(),
                "User has been redirected to different page");
        String cartProductName = cartPage.getCartProductName();
        swipeScreen(Direction.LEFT);
        String cartProductPrice = cartPage.getCartProductPrice();
        String formattedCartProductPrice = "US " + cartProductPrice;
        softAssert.assertEquals(productName,cartProductName,
                "Product with different name was added to the cart");
        softAssert.assertEquals(productPrice,formattedCartProductPrice,
                "Product with different price was added to the cart");
        cartPage.tapRemove();
        pause(2);
        swipeScreen(Direction.RIGHT);

        cartPage.tapHomeButton();
        Assert.assertTrue(homePage.isCartEmpty(),
                "Cart is not empty");

        softAssert.assertAll();
    }
}
