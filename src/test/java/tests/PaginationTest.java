package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class PaginationTest extends BaseTest {

    @Test
    public void verifyPagination() {

      // Open website
        openurl( "https://datatables.net/examples/core/basic_init/zero_configuration.html");

        logger.info("Website opened successfully.");

        // Wait for table
        WebDriverWait wait = new WebDriverWait( driver,Duration.ofSeconds(10) );

        wait.until(driver -> driver.findElement(By.cssSelector("#example tbody tr")).isDisplayed() );

        logger.info("Table loaded successfully.");

        // Verify first page
        verifyPageData();

        String firstPageData = getFirstRowData();

        logger.info("Page 1 first row: " + firstPageData);

        // Find Next button
        By nextButton = By.cssSelector("#example_wrapper .dt-paging-button.next");

        By previousButton = By.cssSelector( "#example_wrapper .dt-paging-button.previous" );

        // Get page information
        By pageInfo = By.cssSelector("#example_wrapper .dt-info" );

        String info = driver.findElement(pageInfo).getText();

        logger.info("Initial page information: " + info);

        int pageNumber = 1;

        // Navigate through all pages
        while (true) {

            WebElement next = driver.findElement(nextButton);

            // Check whether Next is disabled
            String disabledAttribute = next.getAttribute("aria-disabled");

            if ("true".equals(disabledAttribute)) {

            	logger.info("Next button is disabled.");
            	logger.info("Last page reached: " + pageNumber);

                break;
            }

            // Click Next
            next.click();

            pageNumber++;

            // Wait for table refresh
            wait.until(driver -> driver.findElement(By.cssSelector("#example tbody tr")).isDisplayed() );

            logger.info("Navigated to page: " + pageNumber);

            // Verify data
            verifyPageData();

            String currentPageData = getFirstRowData();

            logger.info(
                "Page " + pageNumber +
                " first row: " + currentPageData
            );

            // Verify page data changed
            Assert.assertNotEquals( currentPageData, firstPageData, "Page data did not change after clicking Next" );

            // Update first row data for next comparison
            firstPageData = currentPageData;
        }

        logger.info("All pages loaded successfully.");

        // Test Previous button
        if (pageNumber > 1) {

        	logger.info("Testing Previous button.");

            WebElement previous = driver.findElement(previousButton);

            Assert.assertFalse( "true".equals(previous.getAttribute("aria-disabled")
                    ), "Previous button should be enabled");

            previous.click();

            pageNumber--;

            logger.info("Successfully navigated back to page: " + pageNumber );

            verifyPageData();

            logger.info("Previous button validation successful.");
        }

        logger.info("Pagination Test Passed");
    }

    
     // Verify that current page contains table data.
 
    private void verifyPageData() {

        List<WebElement> rows = driver.findElements( By.cssSelector("#example tbody tr"));

        Assert.assertFalse( rows.isEmpty(), "No data displayed on current page" );

        for (WebElement row : rows) {

            String rowText = row.getText();

            Assert.assertFalse( rowText.trim().isEmpty(), "Empty row found on current page");

            logger.info("Row data: " + rowText);
        }
    }
    
 
    

   //Get the first row data
    private String getFirstRowData() {

        WebElement firstRow = driver.findElement( By.cssSelector("#example tbody tr:first-child"));

        return firstRow.getText();
    }

}