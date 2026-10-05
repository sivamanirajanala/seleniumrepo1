package utiities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisualUtils {

	// taking screenshot of complete page
	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destination = new File("./screenshots/actual/" + fileName + ".png");

		destination.getParentFile().mkdirs();

		java.nio.file.Files.copy(source.toPath(), destination.toPath(),
				java.nio.file.StandardCopyOption.REPLACE_EXISTING);

		return destination.getAbsolutePath();
	}

	// Take screenshot of a particular web element
	public static String takeElementScreenshot(WebElement element, String fileName) throws IOException {

		File source = element.getScreenshotAs(OutputType.FILE);

		File destination = new File("./screenshots/actual/" + fileName + ".png");

		destination.getParentFile().mkdirs();

		java.nio.file.Files.copy(source.toPath(), destination.toPath(),
				java.nio.file.StandardCopyOption.REPLACE_EXISTING);

		return destination.getAbsolutePath();
	}

	// Compare two images
	public static boolean compareImages(String expectedPath, String actualPath) throws IOException {

		BufferedImage expected = ImageIO.read(new File(expectedPath));

		BufferedImage actual = ImageIO.read(new File(actualPath));

		// Check image dimensions
		if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {

			return false;
		}

		// Compare every pixel
		for (int x = 0; x < expected.getWidth(); x++) {

			for (int y = 0; y < expected.getHeight(); y++) {

				if (expected.getRGB(x, y) != actual.getRGB(x, y)) {

					return false;
				}
			}
		}

		return true;
	}

	// Create mismatch image
	public static void createMismatchImage(String expectedPath, String actualPath, String mismatchPath)
			throws IOException {

		BufferedImage expected = ImageIO.read(new File(expectedPath));

		BufferedImage actual = ImageIO.read(new File(actualPath));

		int width = Math.min(expected.getWidth(), actual.getWidth());

		int height = Math.min(expected.getHeight(), actual.getHeight());

		BufferedImage mismatch = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				int expectedRGB = expected.getRGB(x, y);

				int actualRGB = actual.getRGB(x, y);

				if (expectedRGB != actualRGB) {

					// Mark difference
					mismatch.setRGB(x, y, 0xFFFF0000);

				} else {

					mismatch.setRGB(x, y, actualRGB);
				}
			}
		}

		File output = new File(mismatchPath);

		output.getParentFile().mkdirs();

		ImageIO.write(mismatch, "png", output);
	}
}
