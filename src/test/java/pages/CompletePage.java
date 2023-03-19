package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage {
    private final WebDriver driver;
    private By titleLocator = By.className("title");
    private By successfulTextLocator = By.xpath("//h2[text()='Thank you for your order!']");

    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(titleLocator).getText();
    }

    public String getSuccessfulText(){
        return driver.findElement(successfulTextLocator).getText();
    }
}
