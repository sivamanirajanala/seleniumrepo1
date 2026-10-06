package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(className = "ico-login")
    WebElement loginLink; 

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(css = "input[value='Log in']")
    WebElement loginButton;

    @FindBy(className = "ico-logout")
    WebElement logoutLink;

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {

        loginLink.click();
    }

    public void enterEmail(String emailAddress) {

        email.sendKeys(emailAddress);
    }

    public void enterPassword(String passwordValue) {

        password.sendKeys(passwordValue);
    }

    public void clickLoginButton() {

        loginButton.click();
    }

    public boolean isLogoutDisplayed() {

        return logoutLink.isDisplayed();
    }

    public void logout() {

        logoutLink.click();
    }
}

