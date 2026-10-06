package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageForSuite {

	WebDriver driver;

	@FindBy(id = "small-searchterms")
	WebElement searchBox;

	@FindBy(css = "input[value='Search']")
	WebElement searchButton;

	@FindBy(linkText = "Books")
	WebElement booksLink;

	@FindBy(linkText = "Electronics")
	WebElement electronicsLink;

	public HomePageForSuite(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void searchProduct(String product) {

		searchBox.clear();

		searchBox.sendKeys(product);

		searchButton.click();
	}

	public void clickBooks() {

		booksLink.click();
	}

	public void clickElectronics() {

		electronicsLink.click();
	}
}
