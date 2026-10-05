package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;

public class MainTestCase extends BaseTest {
	
	@Test()
	    public void endtoEndTestCase() {
		
		openurl("https://demowebshop.tricentis.com/");
		logger.info("searching the product...");
		String productName="laptop";
		
		HomePage home=new HomePage(driver);
		
		home.searchTheProduct(productName);
		
		
		logger.info("adding the product to the cart...");
		ProductPage productspage=new ProductPage(driver);
		
		String priceValue=productspage.getPriceInProductPage();
		
		productspage.addingAction();
		
		
		
		String assertproductMessage="The product has been added to your shopping cart";
		
		productspage.assertMessage(assertproductMessage);
		
		logger.info("go to the cart section...");
		productspage.hoverOnShopCart();
		productspage.gotoCart();
		
		CartPage cartpage=new CartPage(driver);
		
		
		logger.info("in the cartpage...");
		cartpage.assertPriceValue(priceValue);
		
		logger.info("asserting the product details...");
		cartpage.assertQuantity("1");
		cartpage.assertTotalPrice(priceValue);
		
		
		logger.info("changing the quantity of the product to 2 dynamically...");
		cartpage.changeQuantity("2");
		
		cartpage.clickUpdateCart();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		double priceNumber = Double.parseDouble(priceValue);

		double total = priceNumber * 2;

		String result = String.format("%.2f", total);
		
		logger.info("price is doubled for the qauntity...");
		System.out.println(result);
		
		cartpage.assertTotalPrice(result);

		logger.info("checkout to the cart...");
		cartpage.clickCheckout();
		
	}
}
