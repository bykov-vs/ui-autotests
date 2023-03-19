import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ChromeTest {
    WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }


    @Test
    public void successfulOrderTest() {
        String userName = "standard_user";
        String password = "secret_sauce";
        String contact = "test";

        LoginPage loginPage = new LoginPage(driver);
        MarketPage marketPage = loginPage.loginAs(userName, password);
        CartPage cartPage = marketPage.addToCart().toCart();
        ContactPage contactPage = cartPage.checkout();
        ConfirmPage confirmPage = contactPage.typeContacts(contact, contact, contact);
        CompletePage completePage = confirmPage.finish();

        assertEquals("Checkout: Complete!", completePage.getTitle());
        assertEquals("Thank you for your order!", completePage.getSuccessfulText());
    }

    @Test
    public void invalidLoginTest() {
        String userName = "test", password = "test";
        LoginPage loginPage = new LoginPage(driver);
        loginPage = loginPage.invalidLoginAs(userName, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username and password " +
                "do not match any user in this service", errorMessage);
    }

    @After
    public void quit() {
        driver.quit();
    }
}
