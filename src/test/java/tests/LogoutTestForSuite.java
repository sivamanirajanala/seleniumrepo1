package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LogoutTestForSuite extends BaseTest {

    @Test
    public void verifyLogout() {
    	openurl("https://demowebshop.tricentis.com/");

        LoginPage loginPage = new LoginPage(driver);
        

        loginPage.clickLogin();

        loginPage.enterEmail(properties.getProperty("email"));

        loginPage.enterPassword(properties.getProperty("password2"));

        loginPage.clickLoginButton();

        Assert.assertTrue(
                loginPage.isLogoutDisplayed(),
                "Login failed"
        );

        loginPage.logout();

        Assert.assertTrue(
                driver.getPageSource().contains("Log in"),
                "Logout was not successful"
        );
    }
}