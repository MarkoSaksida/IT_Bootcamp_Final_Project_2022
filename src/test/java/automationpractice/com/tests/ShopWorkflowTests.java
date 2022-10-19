package automationpractice.com.tests;

import automationpractice.com.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShopWorkflowTests {


    private WebDriver webDriver;
    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private TShirtsPage tShirtsPage;
    private ProductPage productPage;
    private Cart cart;
    private SearchResultsPage searchResultsPage;


    @BeforeMethod
    public void configure() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        homePage = new HomePage(webDriver);
        accountCreateOrLogInPage = new AccountCreateOrLogInPage(webDriver);
        tShirtsPage = new TShirtsPage(webDriver);
        productPage = new ProductPage(webDriver);
        cart = new Cart(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void quitWebDriver() {
        webDriver.quit();
    }

    @Test
    public void searchForClothingItem_expectedReturnSearchResults() {
        homePage.setSearchQueryField("t-shirt");
        homePage.clickSearchQueryBtn();
        Assert.assertEquals(webDriver.getTitle(), "Search - My Store");
        Assert.assertEquals(searchResultsPage.getSearchReturnedItem(), "Faded Short Sleeve T-shirts");

    }

    @Test
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

    @Test
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
    @DataProvider(name = "paymentMethod")
    public Object[][] paymentMethod() {
        return new Object[][] {
                { "bank wire"},
                { "check"},
        };
    }
    @Test(dataProvider = "paymentMethod")
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
    }

}
