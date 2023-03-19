package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {
    private final WebDriver driver;
    private By firstNameLocator = By.id("first-name");
    private By lastNameLocator = By.id("last-name");
    private By codeLocator = By.id("postal-code");
    private By continueButtonLocator = By.id("continue");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public ConfirmPage typeContacts(String firstName, String lastName, String code){
        typeFirstName(firstName);
        typeLastName(lastName);
        typeCode(code);
        return submitContacts();
    }

    public ContactPage typeFirstName(String firstName){
        driver.findElement(firstNameLocator).sendKeys(firstName);
        return this;
    }

    public ContactPage typeLastName(String lastName){
        driver.findElement(lastNameLocator).sendKeys(lastName);
        return this;
    }

    public ContactPage typeCode(String code){
        driver.findElement(codeLocator).sendKeys(code);
        return this;
    }

    public ConfirmPage submitContacts(){
        driver.findElement(continueButtonLocator).click();
        return new ConfirmPage(driver);
    }
}
