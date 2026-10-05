package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.*;
import utiities.VisualUtils;

public class VisualValidationTest extends BaseTest {

    @Test
    public void validateJQueryUIVisuals() throws IOException {

        // Open website
        openurl("https://jqueryui.com/");

        // Find the logo
        WebElement logo = driver.findElement(
                By.cssSelector("a[href='https://jqueryui.com/']")
        );

        // Take actual screenshot of logo
        String actualPath = VisualUtils.takeElementScreenshot(
                logo,
                "jquery_logo_actual"
        );

        // Reference image
        String expectedPath =
                "./src/test/resources/reference/jquery_header.png";

        // Compare actual vs reference
        boolean imagesMatch = VisualUtils.compareImages(
                expectedPath,
                actualPath
        );

        // Create mismatch screenshot if comparison fails
        if (!imagesMatch) {

            VisualUtils.createMismatchImage(
                    expectedPath,
                    actualPath,
                    "./screenshots/mismatch/jquery_logo_mismatch.png"
            );

            System.out.println("Visual validation FAILED");
            System.out.println(
                    "Mismatch screenshot: ./screenshots/mismatch/jquery_logo_mismatch.png"
            );
        }

        Assert.assertTrue(
                imagesMatch,
                "Visual validation failed. Check the mismatch screenshot."
        );

        System.out.println("Visual validation PASSED");
    }
}