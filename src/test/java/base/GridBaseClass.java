package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class GridBaseClass {

    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {

        properties = new Properties();

        FileInputStream file =
                new FileInputStream("src/test/resources/config.properties");

        properties.load(file);
        file.close();

        System.out.println("Execution Environment: "
                + properties.getProperty("execution_env"));

        System.out.println("OS: " + os);
        System.out.println("Browser: " + browser);


        
        //remote execution
        if (properties.getProperty("execution_env")
                .equalsIgnoreCase("remote")) {

            URL gridUrl = new URL("http://localhost:4444");

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();

                driver = new RemoteWebDriver(gridUrl, options);

            } else if (browser.equalsIgnoreCase("edge")) {

                EdgeOptions options = new EdgeOptions();

                driver = new RemoteWebDriver(gridUrl, options);

            } else {

                throw new IllegalArgumentException(
                        "Invalid browser: " + browser);
            }
        }


       //local execution
        else if (properties.getProperty("execution_env")
                .equalsIgnoreCase("local")) {

            if (browser.equalsIgnoreCase("chrome")) {

                driver = new org.openqa.selenium.chrome.ChromeDriver();

            } else if (browser.equalsIgnoreCase("edge")) {

                driver = new org.openqa.selenium.edge.EdgeDriver();

            } else {

                throw new IllegalArgumentException(
                        "Invalid browser: " + browser);
            }
        }

        else {

            throw new IllegalArgumentException(
                    "Invalid execution environment");
        }

        
        //common settings

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(properties.getProperty("url"));
    }

    public void openurl(String url) {

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}