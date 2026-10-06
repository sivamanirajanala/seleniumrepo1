package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RegisterPage;

public class RegistrationTestForSuite extends BaseTest {

    @Test
    public void verifyUserRegistration() {
    	
    	logger.info("opening the reegistration url");
    	openurl("https://demowebshop.tricentis.com/");

        RegisterPage registerPage = new RegisterPage(driver);

        
        registerPage.clickRegister();
        
        logger.info("enteringn the registration form details");

        registerPage.selectMale();

        registerPage.enterFirstName("Mani");

        registerPage.enterLastName("Test");

        registerPage.enterEmail(
                "mani" + System.currentTimeMillis() + "@test.com"
        );
        
        logger.info("password and confirming the password");

        registerPage.enterPassword("Test@123");

        registerPage.enterConfirmPassword("Test@123");

        registerPage.clickRegisterButton();

        logger.info("asserting the registration success message");
        Assert.assertTrue(
                driver.getPageSource().contains("Your registration completed"),
                "Registration was not successful"
        );
    }
}