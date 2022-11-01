package automationpractice.com.tests;

import automationpractice.com.base.BaseTest;
import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class ShopFlowTests extends BaseTest {

    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private TShirtsPage tShirtsPage;
    private ProductPage productPage;
    private Cart cart;
    private SearchResultsPage searchResultsPage;

    private AccountPage accountPage;


    @BeforeMethod
    public void configure() {
        homePage = new HomePage();
        accountCreateOrLogInPage = new AccountCreateOrLogInPage();
        tShirtsPage = new TShirtsPage();
        productPage = new ProductPage();
        cart = new Cart();
        searchResultsPage = new SearchResultsPage();
        accountPage = new AccountPage();
    }

    @AfterMethod
    public void pageCleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @Test (priority = 10)
    public void searchForClothingItem_expectedReturnSearchResults() {
        homePage.setSearchQueryField("t-shirt");
        homePage.clickSearchQueryBtn();
        Assert.assertEquals(webDriver.getTitle(), "Search - My Store");
        Assert.assertEquals(searchResultsPage.getSearchReturnedItem(), "Faded Short Sleeve T-shirts");

    }

    @Test (priority = 20)
    public void addOrangeAndBlueTShirtToShoppingCartAndContinueShopping_expectedShoppingContinuesItemsAddedToShoppingCart() {
        homePage.clickTShirtsBtn();
        Assert.assertEquals(webDriver.getTitle(), "T-shirts - My Store", "error");
        tShirtsPage.clickOnFadedShortSleeveTShirt();
        productPage.setQuantityField("3");
        productPage.setSizeDropdown(2);
        productPage.clickOrangeOrBlueColorButton("orange");
        productPage.clickAddFadedTShirtToCart();
        productPage.clickContinueShopping();
        productPage.setQuantityField("2");
        productPage.setSizeDropdown(3);
        productPage.clickOrangeOrBlueColorButton("blue");
        productPage.clickAddFadedTShirtToCart();
        productPage.clickContinueShopping();
        Assert.assertEquals(webDriver.getTitle(), "Faded Short Sleeve T-shirts - My Store");
        productPage.clickOnCartButton();
        Assert.assertEquals(webDriver.getTitle(), "Order - My Store");
        Assert.assertEquals(cart.getSrcAttributeOrangeTShirt(), "http://automationpractice.com/img/p/1/1-small_default.jpg");
        Assert.assertEquals(cart.getSrcAttributeBlueTShirt(), "http://automationpractice.com/img/p/3/3-small_default.jpg");

    }

    @Test (priority = 30)
    public void addOrangeTShirtToShoppingCartAndRemoveIt_expectedShoppingCartEmpty() {
        homePage.clickTShirtsBtn();
        Assert.assertEquals(webDriver.getTitle(), "T-shirts - My Store");
        tShirtsPage.clickOnFadedShortSleeveTShirt();
        productPage.setQuantityField("2");
        productPage.setSizeDropdown(1);
        productPage.clickOrangeOrBlueColorButton("orange");
        productPage.clickAddFadedTShirtToCart();
        productPage.clickProceedToCheckoutBtn();
        Assert.assertEquals(webDriver.getTitle(), "Order - My Store");
        Assert.assertEquals(cart.getSrcAttributeOrangeTShirt(), "http://automationpractice.com/img/p/1/1-small_default.jpg");
        cart.clickRemoveAllFromCart();
        Assert.assertEquals(cart.getShoppingCartIsEmptyAlert(), "Your shopping cart is empty.");
    }

    @Test(priority = 40, dataProvider = "paymentMethod", dataProviderClass = DataProviders.class)
    public void shopForOrangeTShirt_expectedSuccessfulCheckout(String paymentMethod) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("tester@tester.rs");
        accountCreateOrLogInPage.setSignInPasswordField("test123456");
        accountCreateOrLogInPage.clickSignInButton();
        homePage.clickYourLogoHomeButton();
        homePage.clickTShirtsBtn();
        Assert.assertEquals(webDriver.getTitle(), "T-shirts - My Store");
        tShirtsPage.clickOnFadedShortSleeveTShirt();
        productPage.setQuantityField("2");
        productPage.setSizeDropdown(1);
        productPage.clickOrangeOrBlueColorButton("orange");
        productPage.clickAddFadedTShirtToCart();
        productPage.clickProceedToCheckoutBtn();
        Assert.assertEquals(webDriver.getTitle(), "Order - My Store");
        Assert.assertEquals(cart.getSrcAttributeOrangeTShirt(), "http://automationpractice.com/img/p/1/1-small_default.jpg");
        cart.changeQuantityOfItem("1");
        cart.clickProceedToCheckout();
        cart.clickProceedToCheckoutBtn2();
        cart.checkAgreeToTermsCheckbox();
        cart.clickProceedToCheckoutBtn3();
        cart.clickPayBy(paymentMethod);
        cart.clickIConfirmMyOrder();
        Assert.assertEquals(webDriver.getTitle(), "Order confirmation - My Store");
        accountPage.clickSignOutButton();
    }

}
