package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;
import utiities.VisualUtils;

public class CreateReferenceImageTest extends BaseTest {

	@Test
	public void createReferenceImage() throws IOException {

		openurl("https://demowebshop.tricentis.com/");

		WebElement logo = driver.findElement(By.cssSelector("a[href='https://jqueryui.com/']"));

		String path = "./src/test/resources/reference/logo.png";

		java.io.File source = logo.getScreenshotAs(org.openqa.selenium.OutputType.FILE);

		java.io.File destination = new java.io.File(path);

		destination.getParentFile().mkdirs();

		java.nio.file.Files.copy(source.toPath(), destination.toPath(),
				java.nio.file.StandardCopyOption.REPLACE_EXISTING);

		System.out.println("Reference image created:");
		System.out.println(destination.getAbsolutePath());
	}
}