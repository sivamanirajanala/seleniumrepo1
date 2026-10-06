package tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTestForSuite extends BaseTest {

    @Test
    public void verifyValidLogin() {
    	
    	openurl("https://demowebshop.tricentis.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLogin();

        loginPage.enterEmail(properties.getProperty("email"));

        loginPage.enterPassword(properties.getProperty("password2"));

        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.isLogoutDisplayed(),
                "Login was not successful"
        );
    }
}