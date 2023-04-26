package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginSecureAreaFirefoxTest extends BaseTests {

    @Test
    public void testLoginSecureAreaFirefox() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        SecureAreaPage secureAreaPage = loginPage.loginSecureArea("tomsmith", "SuperSecretPassword!");
        String expectedMessage = secureAreaPage.getAlertText();
        Assert.assertTrue(expectedMessage.contains("You loggeds into a secure area!"), "Expected message: " + expectedMessage);
    }
}
