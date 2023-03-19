package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarketPage {
    private final WebDriver driver;
    private By addToCartButtonLocator = By.id("add-to-cart-sauce-labs-backpack");
    private By cartLinkLocator = By.className("shopping_cart_link");

    public MarketPage(WebDriver driver) {
        this.driver = driver;
    }

    public MarketPage addToCart(){
        driver.findElement(addToCartButtonLocator).click();
        return this;
    }

    public CartPage toCart(){
        driver.findElement(cartLinkLocator).click();
        return new CartPage(driver);
    }
}
