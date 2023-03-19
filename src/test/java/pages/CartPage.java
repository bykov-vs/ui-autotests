package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private By checkoutButtonLocator = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public ContactPage checkout(){
        driver.findElement(checkoutButtonLocator).click();
        return new ContactPage(driver);
    }
}
