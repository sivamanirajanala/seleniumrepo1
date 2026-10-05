package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='small-searchterms']") private WebElement searchBox;
	
	
	@FindBy(xpath="(//input[@type=\"submit\"])[1]") private WebElement searchButton;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	public void searchTheProduct(String product) {
		searchBox.sendKeys(product);;
		searchButton.click();
	}
	
}
