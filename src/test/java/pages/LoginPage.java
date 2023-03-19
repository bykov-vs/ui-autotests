package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    private final WebDriver driver;
    private By usernameLocator = By.id("user-name");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.cssSelector(".submit-button");
    private By errorMessageContainerLocator = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public MarketPage loginAs(String username, String password){
        typeUsername(username);
        typePassword(password);
        return submitForm();
    }

    public LoginPage invalidLoginAs(String username, String password){
        typeUsername(username);
        typePassword(password);
        driver.findElement(loginButtonLocator).click();
        return this;
    }

    public MarketPage submitForm(){
        driver.findElement(loginButtonLocator).click();
        return new MarketPage(driver);
    }

    public LoginPage typeUsername(String username){
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password){
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public String getErrorMessage(){
        List<WebElement> elements = driver.findElement(errorMessageContainerLocator)
                .findElements(By.tagName("h3"));
        if (!elements.isEmpty()){
            return elements.get(0).getText();
        }
        return "";
    }
}
