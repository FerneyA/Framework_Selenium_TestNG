package com.demo.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.demo.pages.LoginPage;
import com.demo.pages.SecureAreaPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTests {

    @Test
    public void testLoginSecureArea() throws IOException {
        new WebDriverWait(super.getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.urlMatches("https://the-internet.herokuapp.com/"));
        extent.createTest("Browser OK")
                .log(Status.PASS, "Browser got up successfully");
        LoginPage loginPage = homePage.clickFormAuthentication();
        SecureAreaPage secureAreaPage = loginPage.loginSecureArea("tomsmith", "SuperSecretPassword!");
        String expectedMessage = secureAreaPage.getAlertText();
        File scrFile = ((TakesScreenshot)super.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/report/evidence/CP001_OK.png"));
        if(expectedMessage.contains("You logged into a secure area!")) {
            extent.createTest("Login successful in Secure Area")
                    .createNode("CP001")
                    .pass(MediaEntityBuilder.createScreenCaptureFromPath("evidence/CP001_OK.png").build());
        } else {
            extent.createTest("Login NO successful in Secure Area")
                    .createNode("CP001")
                    .pass(MediaEntityBuilder.createScreenCaptureFromPath("evidence/CP001_OK.png").build());
        }
    }
}
