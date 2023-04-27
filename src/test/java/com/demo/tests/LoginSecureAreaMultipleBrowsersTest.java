package com.demo.tests;

import org.testng.Assert;
import pages.LoginPage;
import pages.SecureAreaPage;
import org.testng.annotations.Test;

public class LoginSecureAreaMultipleBrowsersTest extends BaseTests {

    @Test
    public void testLoginSecureAreaChrome() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        SecureAreaPage secureAreaPage = loginPage.loginSecureArea("tomsmith", "SuperSecretPassword!");
        String expectedMessage = secureAreaPage.getAlertText();
        Assert.assertTrue(expectedMessage.contains("You logged into a secure area!"), "Expected message: " + expectedMessage);
    }

    @Test
    public void testLoginSecureAreaFirefox() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        SecureAreaPage secureAreaPage = loginPage.loginSecureArea("tomsmith", "SuperSecretPassword!");
        String expectedMessage = secureAreaPage.getAlertText();
        Assert.assertTrue(expectedMessage.contains("You loggeds into a secure area!"), "Expected message: " + expectedMessage);
    }
}
