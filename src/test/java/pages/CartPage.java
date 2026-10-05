package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {
	
	 private WebDriver driver;

	    Actions actions;

	    
	    @FindBy(xpath = "//td[@class='product']//a") private WebElement productName;

	   
	    @FindBy(xpath = "//td/span[@class='product-unit-price']") private WebElement priceValue;

	
	    @FindBy(xpath = "//td/input[@type='text']") private WebElement inputQuantity;
	    
	    @FindBy(xpath = "//td/span[@class='product-subtotal']") private WebElement totalPrice;
	    
	    @FindBy(name = "updatecart") private WebElement updateCartBtn;
	    
	    @FindBy(xpath="//input[@type='checkbox' and @name='termsofservice']") private WebElement termsAndConditions;

	    @FindBy(xpath = "//button[contains(@class,'checkout-button')]") private WebElement checkoutBtn;
	    


	    public CartPage(WebDriver driver) {

	        this.driver = driver;

	        this.actions = new Actions(driver);

	        PageFactory.initElements(driver, this);
	    }
	    
	    
	    public void assertPriceValue(String expectedPrice) {

	        String actualPrice = priceValue.getText();

	        Assert.assertEquals(actualPrice, expectedPrice,"Product price is not matching");
	    }


	    public void assertQuantity(String expectedQuantity) {

	        String actualQuantity = inputQuantity.getAttribute("value");

	        Assert.assertEquals(actualQuantity, expectedQuantity, "Product quantity is not matching");
	    }
	    
	    public void assertTotalPrice(String expectedTotalPrice) {

	        String actualTotalPrice = totalPrice.getText();
	        

	        Assert.assertEquals(actualTotalPrice,expectedTotalPrice,"Total price is not matching");
	    }
	    
	    public void changeQuantity(String quantity) {

	        inputQuantity.clear();

	        inputQuantity.sendKeys(quantity);
	    }


	    public void clickUpdateCart() {

	        updateCartBtn.click();
	    }


	    public void clickCheckout() {
	    	termsAndConditions.click();

	        checkoutBtn.click();
	    }
	    

}
