package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Level;
import base.BaseTest;

public class BrowserConsoleLogsCapture extends BaseTest {

	@Test
	public void captureBrowseLogs() {

		logger.info("opening the url");

		openurl("https://the-internet.herokuapp.com/javascript_alerts");

		logger.info("finding alert element");

		WebElement jsAlertElement = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));

		jsAlertElement.click();

		logger.info("clicked on the alert button");

		driver.switchTo().alert().accept();

		logger.info("accepted the alert");

		logger.info("finding the confirm alert button");

		WebElement jsConfirmElement = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));

		jsConfirmElement.click();

		logger.info("clicked on the confirm button");

		driver.switchTo().alert().accept();

		logger.info("accepted the confirm alert");

		logger.info("now finding the browser logs");

		
		LogEntries logsEntries = driver.manage().logs().get(LogType.BROWSER);

		logger.info("total browser logs: " + logsEntries.getAll().size());

		boolean criticalLogs = false;

		int criticalErrorCount = 0;
		int warningCount = 0;
		int ignoredLogCount = 0;

	
		for (LogEntry entry : logsEntries) {

			String level = entry.getLevel().toString();
			String message = entry.getMessage();

			logger.info(level + " : " + message);

			
			
			if (message.contains("optimizely.com")) {

				ignoredLogCount++;

				logger.info("IGNORED - Third-party Optimizely log: " + message);

				continue;
			}

			
			if (message.contains("favicon.ico") && message.contains("404")) {

				ignoredLogCount++;

				logger.info("IGNORED - Favicon 404: " + message);

				continue;
			}

		
			if (entry.getLevel().equals(Level.SEVERE)) {

				criticalLogs = true;
				criticalErrorCount++;

				logger.error("CRITICAL BROWSER ERROR: " + message);
				System.out.println(message);
			}

			
			//Browser WARNING.
			else if (entry.getLevel().equals(Level.WARNING)) {

				warningCount++;

				logger.warn("BROWSER WARNING: " + message);
			}
		}

		logger.info("Browser console log validation result");
		logger.info("Critical errors: " + criticalErrorCount);
		logger.info("Warnings: " + warningCount);
		logger.info("Ignored logs: " + ignoredLogCount);

		logger.info("Browser console validation completed successfully");
		
		System.out.println("critical logs are found"+criticalLogs);
	}

}
