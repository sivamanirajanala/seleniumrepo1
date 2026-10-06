package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.newBaseTestClass;

public class CrossBrowserTests extends newBaseTestClass {

	@Test
	public void testLogin() {

		String randomUsername = "mani" + System.currentTimeMillis();
		String password = properties.getProperty("password");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		//signup

		System.out.println("Starting Signup...");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username"))).sendKeys(randomUsername);

		driver.findElement(By.id("sign-password")).sendKeys(password);

		driver.findElement(By.xpath("//button[text()='Sign up']")).click();

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		String signupMessage = alert.getText();

		System.out.println("Signup Alert: " + signupMessage);

		Assert.assertTrue(signupMessage.contains("Sign up successful"), "Signup failed: " + signupMessage);

		alert.accept();

		recordResult("Signup", true);

		//login

		System.out.println("Starting Login...");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(randomUsername);

		driver.findElement(By.id("loginpassword")).sendKeys(password);

		driver.findElement(By.xpath("//button[text()='Log in']")).click();

		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));

		Assert.assertTrue(logout.isDisplayed(), "Logout button is not displayed");

		recordResult("Login", true);
		
		
		//navigation

		System.out.println("Starting Navigation...");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Phones']"))).click();

		WebElement phoneProduct = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'prod.html')]")));

		Assert.assertTrue(phoneProduct.isDisplayed(), "Phone products are not displayed");

		recordResult("Navigation", true);

	    //form submission

		System.out.println("Starting Form Submission...");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recipient-email")))
				.sendKeys("test@example.com");
  
		driver.findElement(By.id("recipient-name")).sendKeys("Mani");

		driver.findElement(By.id("message-text")).sendKeys("This is a cross browser testing message.");

		driver.findElement(By.xpath("//button[text()='Send message']")).click();

		alert = wait.until(ExpectedConditions.alertIsPresent());

		String message = alert.getText();

		System.out.println("Contact Alert: " + message);

		Assert.assertTrue(message.contains("Thanks"), "Form submission was not successful");

		alert.accept();

		recordResult("Form submission", true);

	   //final result
		printResults();
	}
}