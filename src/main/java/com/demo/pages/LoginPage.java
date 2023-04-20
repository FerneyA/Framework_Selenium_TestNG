package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By inputUsername = By.id("username");
    private By inputPassword = By.id("password");
    private By buttonLogin = By.cssSelector("#login button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public SecureAreaPage loginSecureArea(String username, String password) {
        driver.findElement(inputUsername).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonLogin).click();
        return new SecureAreaPage(driver);
    }
}
