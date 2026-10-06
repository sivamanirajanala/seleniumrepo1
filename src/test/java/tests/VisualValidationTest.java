
package tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;

public class VisualValidationTest extends BaseTest{

	String referencePath = "src/test/resources/reference/logo.png";

	String actualPath = "screenshots/current-logo.png";

	String mismatchPath = "screenshots/mismatches/logo-mismatch.png";

	

	@Test
	public void validateLogoVisual() throws Exception {
		
		openurl("https://demowebshop.tricentis.com/");

		// Locate logo
		By logoLocator = By.cssSelector("div.header-logo img");

		// Capture current logo
		File source = driver.findElement(logoLocator).getScreenshotAs(OutputType.FILE);

		File actualFile = new File(actualPath);

		actualFile.getParentFile().mkdirs();

		FileHandler.copy(source, actualFile);

		System.out.println("Current screenshot captured.");

		// If reference image does not exist,
		// create the reference image.
		File referenceFile = new File(referencePath);

		if (!referenceFile.exists()) {

			referenceFile.getParentFile().mkdirs();

			FileHandler.copy(source, referenceFile);

			System.out.println("Reference image created: " + referencePath);

			return;
		}

		// Compare images
		double mismatchPercentage = compareImages(referenceFile, actualFile);

		System.out.println("Visual mismatch: " + String.format("%.2f", mismatchPercentage) + "%");

		// Allow maximum 1% difference
		double allowedDifference = 1.0;

		if (mismatchPercentage > allowedDifference) {

			File mismatchFile = new File(mismatchPath);

			mismatchFile.getParentFile().mkdirs();

			FileHandler.copy(actualFile, mismatchFile);

			System.out.println("Visual validation FAILED.");

			System.out.println("Mismatch screenshot saved at: " + mismatchPath);

			Assert.fail("Logo visual mismatch = " + String.format("%.2f", mismatchPercentage) + "%");
		}

		System.out.println("Visual validation PASSED.");

		Assert.assertTrue(mismatchPercentage <= allowedDifference);
	}

	public double compareImages(File referenceFile, File actualFile) throws IOException {

		BufferedImage reference = ImageIO.read(referenceFile);

		BufferedImage actual = ImageIO.read(actualFile);

		// Check image dimensions
		if (reference.getWidth() != actual.getWidth() || reference.getHeight() != actual.getHeight()) {

			System.out.println("Image dimensions are different.");

			return 100.0;
		}

		int width = reference.getWidth();
		int height = reference.getHeight();

		long differentPixels = 0;

		long totalPixels = (long) width * height;

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				int referenceRGB = reference.getRGB(x, y);

				int actualRGB = actual.getRGB(x, y);

				if (referenceRGB != actualRGB) {

					differentPixels++;
				}
			}
		}

		return ((double) differentPixels / totalPixels) * 100;
	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {

			driver.quit();
		}
	}
}
