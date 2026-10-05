package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {

    private WebDriver driver;


    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement country;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement city;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement address;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement zipCode;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumber;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;


    @FindBy(xpath = "//div[contains(@class,'order-summary-content')]")
    private WebElement orderSummary;


    public CheckoutPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }


    public void enterBillingDetails() {

        country.click();

        country.sendKeys("India");

        city.sendKeys("Hyderabad");

        address.sendKeys("Test Address");

        zipCode.sendKeys("500001");

        phoneNumber.sendKeys("9999999999");
    }


    public void clickContinue() {

        continueButton.click();
    }


    public void verifyOrderSummary(String expectedProductName) {

        String summary = orderSummary.getText();

        Assert.assertTrue(
                summary.contains(expectedProductName),
                "Product is not present in order summary"
        );
    }
}