package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By linkFormAuthentication = By.linkText("Form Authentication");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickFormAuthentication() {
        BasePage.fluentWait(linkFormAuthentication, driver);
        driver.findElement(linkFormAuthentication).click();
        return new LoginPage(driver);
    }
}
