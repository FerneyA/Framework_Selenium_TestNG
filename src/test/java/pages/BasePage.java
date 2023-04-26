package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebElement findElement(By locator, WebDriver driver) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator, WebDriver driver) {
        return driver.findElements(locator);
    }

    public String getText(WebElement webElement, WebDriver driver) {
        return webElement.getText();
    }

    public String getText(By locator, WebDriver driver) {
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator, WebDriver driver) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(inputText);
    }

    public Boolean isDisplayed(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void typeWithTab(String inputText, By locator, WebDriver driver) {
        WebElement webElement = driver.findElement(locator);
        webElement.clear();
        int lengthValue = webElement.getAttribute("value").toCharArray().length;
        for (int i = 0; i < lengthValue; i++) {
            webElement.sendKeys(Keys.BACK_SPACE);
        }
        webElement.sendKeys(inputText);
    }

    public void click(By locator, WebDriver driver) {
        driver.findElement(locator).click();
    }

    public static WebElement fluentWait(final By locator, WebDriver driver) {
        // Waiting 50 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait.until(x -> x.findElement(locator));
    }

    public void webDriverWait(By locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement explicitWaitVisibilityOfElement(By locator, WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement explicitWaitElementToBeClickable(By locator, WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Boolean explicitWaitTextToBePresentInElement(By locator, String text, WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void selectOptionDown(By locator, String searchText, WebDriver driver) {
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

    public void actionsClick(By locator, WebDriver driver) {
        WebElement webElement = findElement(locator, driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().build().perform();
    }
}