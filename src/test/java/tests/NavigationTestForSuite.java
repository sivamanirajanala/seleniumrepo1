package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePageForSuite;

public class NavigationTestForSuite extends BaseTest {

    @Test
    public void verifyNavigation() {
    	
    	openurl("https://demowebshop.tricentis.com/");

        HomePageForSuite homePage = new HomePageForSuite(driver);

        homePage.clickBooks();

        Assert.assertTrue(
                driver.getTitle().contains("Demo Web Shop"),
                "Books page navigation failed"
        );

        driver.navigate().back();

        homePage.clickElectronics();

        Assert.assertTrue(
                driver.getTitle().contains("Demo Web Shop"),
                "Electronics page navigation failed"
        );
    }
}