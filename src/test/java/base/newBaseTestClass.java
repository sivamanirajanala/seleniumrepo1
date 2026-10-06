package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class newBaseTestClass {

    protected WebDriver driver;
    protected Properties properties;

    // Stores PASS/FAIL results
    protected Map<String, String> testResults;

    // Stores current browser
    protected String currentBrowser;


    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws IOException {

        testResults = new LinkedHashMap<>();

        currentBrowser = browser;

        properties = new Properties();

        FileInputStream file = new FileInputStream("src/test/resources/config.properties");

        properties.load(file);
        file.close();

        System.out.println("Browser: " + browser);


        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else {

            throw new RuntimeException(
                    "Invalid browser: " + browser
            );
        }


        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.get(properties.getProperty("url"));

        
        testResults.put("Open application", "PASS");
    }


    // Method to record individual results
    protected void recordResult(String testName, boolean passed) {

        if (passed) {
            testResults.put(testName, "PASS");
        } else {
            testResults.put(testName, "FAIL");
        }
    }


    
    protected void printResults() {

        boolean overallPass = true;

        for (String result : testResults.values()) {

            if (result.equals("FAIL")) {
                overallPass = false;
                break;
            }
        }

        testResults.put(
                "Overall",
                overallPass ? "PASS" : "FAIL"
        );


        System.out.println();
        System.out.println("========================================");
        System.out.println(
                "browser test result: "
                + currentBrowser.toUpperCase()
        );
        System.out.println("========================================");


        for (Map.Entry<String, String> entry :
                testResults.entrySet()) {

            System.out.printf(
                    "%-17s : %s%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }


        System.out.println("========================================");
        System.out.println();
    }


    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}