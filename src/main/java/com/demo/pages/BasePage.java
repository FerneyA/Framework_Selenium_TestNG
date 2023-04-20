package com.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(inputText);
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void typeWithTab(String inputText, By locator) {
        WebElement webElement = driver.findElement(locator);
        webElement.clear();
        int lengthValue = webElement.getAttribute("value").toCharArray().length;
        for (int i = 0; i < lengthValue; i++) {
            webElement.sendKeys(Keys.BACK_SPACE);
        }
        webElement.sendKeys(inputText);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public WebElement fluentWait(final By locator) {
        // Waiting 50 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(locator));
    }

    public void webDriverWait(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement explicitWaitVisibilityOfElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement explicitWaitElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Boolean explicitWaitTextToBePresentInElement(By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void selectOptionDown(By locator, String searchText) {
        WebElement webElement = driver.findElement(locator);
        webElement.click();
        List<WebElement> options = webElement.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            if (option.getText().equals(searchText))
            {
                option.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    public void actionsClick(By locator) {
        WebElement webElement = findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().build().perform();
    }
}