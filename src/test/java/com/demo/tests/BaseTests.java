package com.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTests {

    public static WebDriver driver;
    protected HomePage homePage;
    static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod() {
        htmlReporter = new ExtentSparkReporter("target/reports/evidence/AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Nombre automatizador");
    }

    @BeforeMethod
    @Parameters( {"url", "browserName"} )
    public void setUpDriver(String url, String browserName, Method testMethod) throws MalformedURLException {
        logger = extent.createTest(testMethod.getName());
        if (browserName.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("incognito");
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            driver.get(url);
            homePage = new HomePage(driver);
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-- no headless");
            options.addArguments("-private");
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            driver.get(url);
            homePage = new HomePage(driver);
        }
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath("failed/" + result.getMethod().getMethodName() + ".png").build());
            logger.log(Status.FAIL, m);
        } else if (result.getStatus() == ITestResult.SKIP) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
            logger.log(Status.SKIP, m);
        }
        driver.quit();
    }

    @AfterTest
    public void afterTestMethod() {
        extent.flush();
    }
}
