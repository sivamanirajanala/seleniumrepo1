package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
 
    WebDriver driver;

    @FindBy(className = "ico-register")
    WebElement registerLink;

    @FindBy(xpath = "//input[@id='small-searchterms']")
    WebElement searchBox;

    @FindBy(id = "gender-male")
    WebElement maleRadio;

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPassword;

    @FindBy(id = "register-button")
    WebElement registerButton;

    public RegisterPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void clickRegister() {

        registerLink.click();
    }

    public void selectMale() {

        maleRadio.click();
    }

    public void enterFirstName(String value) {

        firstName.sendKeys(value);
    }

    public void enterLastName(String value) {

        lastName.sendKeys(value);
    }

    public void enterEmail(String value) {

        email.sendKeys(value);
    }

    public void enterPassword(String value) {

        password.sendKeys(value);
    }

    public void enterConfirmPassword(String value) {

        confirmPassword.sendKeys(value);
    }

    public void clickRegisterButton() {

        registerButton.click();
    }
}
