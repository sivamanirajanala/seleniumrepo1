package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductPage {

	WebDriver driver;
	Actions actions;
	@FindBy(xpath = "//input[@value='Add to cart']")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//p[contains(@class,'content') and contains(text(),'The product has been added')]")
	private WebElement productAddMessage;

	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartBtn;

	@FindBy(xpath = "//input[@value='Go to cart']")
	private WebElement gotoCartBtn;

	@FindBy(xpath = "(//span[@class='price actual-price'])[1]")
	private WebElement priceInProductPage;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);

		PageFactory.initElements(driver, this);
	}

	public void addingAction() {
		addToCartBtn.click();
	}

	public void hoverOnShopCart() {
		actions.moveToElement(shoppingCartBtn).perform();
	}

	public void assertMessage(String expectedMessage) {

		String actualMessage = productAddMessage.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Product added message is not matching");
	}

	public void assertPriceInProductPage(String expectedPrice) {
		String actualPrice = priceInProductPage.getText();

		Assert.assertEquals(actualPrice, expectedPrice, "price is not same");

	}

	public void gotoCart() {
		gotoCartBtn.click();
	}

	public String getPriceInProductPage() {
		return priceInProductPage.getText();
	}

}
