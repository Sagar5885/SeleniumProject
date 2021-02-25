package PageObject;

import Utilities.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public static Logger logger;

    public String BaseURL = readConfig.getBaseURL();
    public static WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String br){
        logger = Logger.getLogger("ff4j");
        PropertyConfigurator.configure("src/test/resources/log4j.properties");

        if(br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();
        }else if(br.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", readConfig.getfirefoxPath());
            driver = new FirefoxDriver();
        }
        driver.get(BaseURL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreen(driver, ITestResult.class.getName());
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }
}
