package com.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demo.pages.HomePage;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    static ExtentSparkReporter spark;
    ExtentReports extent;

    public WebDriver getDriver() {
        return this.driver;
    }

    @BeforeTest
    public void setUp() {
        spark = new ExtentSparkReporter("target/report/spark.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeTest
    @Parameters( {"URL", "BrowserType"} )
    public void setUpTest(String url, String browserType) throws MalformedURLException {
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

    @AfterTest
    public void tearDownTest(){
        driver.close();
    }

    @AfterTest
    public void tearDown(){
        extent.flush();
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
