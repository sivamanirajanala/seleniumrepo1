package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class ProductPageForSuite {

    WebDriver driver;

    @FindBy(css = ".product-item")
    List<WebElement> productItems;

    @FindBy(css = ".page-title h1")
    WebElement pageTitle;

    public ProductPageForSuite(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean areProductsDisplayed() {

        return productItems.size() > 0;
    }

    public String getPageTitle() {

        return pageTitle.getText();
    }
}
