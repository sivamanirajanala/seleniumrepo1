package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePageForSuite;
import pages.ProductPageForSuite;

public class SearchTestForSuite extends BaseTest {

    @Test
    public void verifyProductSearch() {
    	 openurl("https://demowebshop.tricentis.com/");

        HomePageForSuite homePage = new HomePageForSuite(driver);
       

        ProductPageForSuite productPage = new ProductPageForSuite(driver);

        homePage.searchProduct("laptop");

        Assert.assertTrue(
                productPage.areProductsDisplayed(),
                "No products found"
        );
    }
}