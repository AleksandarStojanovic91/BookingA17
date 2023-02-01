package steps;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    DriverManager driverManager;
    public WebDriver driver;

    String path = "src/results/screenshots/";

    public void init(String type) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(type);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void quit() {
        driverManager.quitDriver();
    }

    public void openBKApp(String env) throws Exception {
        env = env.toUpperCase();
        switch (env) {
            case "PROD": {
                driver.get("https://www.booking.com/");
            }
            break;
            default:
                throw new Exception("Environment: " + env + " not supported!");
        }
    }

    public void openKPApp(String env) throws Exception {
        env = env.toUpperCase();
        switch (env) {
            case "PROD": {
                driver.get("https://kupujemprodajem.com/");
            }
            break;
            default:
                throw new Exception("Environment: " + env + " not supported!");
        }
    }

    public void takeScreenshot(String fileName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(path + fileName + ".png"));
    }

    public void reporterScreenshot(String fileName, String desc) throws IOException {
        takeScreenshot(fileName);
        Path filePath = Paths.get(path + fileName + ".png");
        InputStream is = Files.newInputStream(filePath);
        Allure.addAttachment(desc, is);
    }

}
