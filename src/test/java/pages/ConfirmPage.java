package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPage {
    private final WebDriver driver;
    private By finishButtonLocator = By.id("finish");

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    public CompletePage finish(){
        driver.findElement(finishButtonLocator).click();
        return new CompletePage(driver);
    }
}
