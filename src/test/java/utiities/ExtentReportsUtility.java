package utiities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;

public class ExtentReportsUtility implements ITestListener {

	public ExtentSparkReporter spartReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timeStamp + ".html";

		spartReporter = new ExtentSparkReporter(reportPath);

		spartReporter.config().setDocumentTitle("Automation Report");
		spartReporter.config().setReportName("UI Testing");
		spartReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		spartReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(spartReporter);
		extent.setSystemInfo("os", "windows");
		extent.setSystemInfo("browser", "chrome");
		extent.setSystemInfo("tester", "mani rajanala");
	}

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getName());
		System.out.println("test case is started" + result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "test is passed" + result.getName());
	}

	public void onTestFailure(ITestResult result) {

		BaseTest test = (BaseTest) result.getInstance();

		try {

			test.takeScreenshot("FAILED_" + result.getName());

			test.logger.info("Failure screenshot captured for: " + result.getName());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.INFO, "skipped the test cases" + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
