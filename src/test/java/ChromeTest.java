import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import static io.qameta.allure.Allure.step;

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
        step("Setup driver");
    }


    @Test
    @DisplayName("Order creation test")
    public void successfulOrderTest() {
        String userName = "standard_user";
        String password = "secret_sauce";
        String contact = "test";

        step("Open login page");
        LoginPage loginPage = new LoginPage(driver);
        step("Login");
        MarketPage marketPage = loginPage.loginAs(userName, password);
        step("Add item to cart");
        CartPage cartPage = marketPage.addToCart().toCart();
        step("Checkout cart");
        ContactPage contactPage = cartPage.checkout();
        step("Type contact information");
        ConfirmPage confirmPage = contactPage.typeContacts(contact, contact, contact);
        step("Finish");
        CompletePage completePage = confirmPage.finish();

        assertEquals("Checkout: Complete!", completePage.getTitle());
        assertEquals("Thank you for your order!", completePage.getSuccessfulText());
    }

    @Test
    @DisplayName("Invalid login test")
    public void invalidLoginTest() {
        String userName = "test", password = "test";
        step("Open login page");
        LoginPage loginPage = new LoginPage(driver);
        step("Type invalid username and password");
        loginPage = loginPage.invalidLoginAs(userName, password);

        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username and password " +
                "do not match any user in this service", errorMessage);
        step("Error: " + errorMessage);
    }

    @After
    public void quit() {
        driver.quit();
        step("Quit driver");
    }
}
