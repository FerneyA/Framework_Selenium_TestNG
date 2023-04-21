package com.demo.tests;

import com.demo.pages.HomePage;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    @Parameters( {"URL", "BrowserType"} )
    public void setUp(String url, String browserType) throws MalformedURLException {
        if (browserType.equalsIgnoreCase("Chrome"))
        {
            //String projectPath = System.getProperty("user.dir");
            //System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("incognito");
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            driver.get(url);
            //driver.get("https://the-internet.herokuapp.com/");
            homePage = new HomePage(driver);
        }
        else if (browserType.equalsIgnoreCase("Firefox"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-- no headless");
            options.addArguments("-private");
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            driver.get(url);
            //driver.get("https://the-internet.herokuapp.com/");
            homePage = new HomePage(driver);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
