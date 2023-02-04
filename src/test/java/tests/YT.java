package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import steps.BaseSteps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class YT extends BaseSteps {

    @BeforeMethod
    public void setup() throws Exception {
        init("CHROME");
        openYTApp("YT");
    }

    @AfterMethod
    public void tearDown(){
        quit();
    }

    @Test(enabled = true)
    public void getComments() throws Exception {
        new BasePage(driver).typeText(driver.findElement(By.cssSelector("input#search")),"Mr beast squid game","");
        driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[title='$456,000 Squid Game In Real Life!']")).click();
        Thread.sleep(10000);

        FileWriter fileWriter = new FileWriter("comments.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int found = 0;
        while (true) {
            new BasePage(driver).scroll();
            Thread.sleep(5000);

            List<WebElement> comments = driver.findElements(By.cssSelector("#content-text"));

            for (int i = found; i < comments.size(); i++) {
                bufferedWriter.write(comments.get(i).getText());
                bufferedWriter.newLine();
            }

            found = comments.size();
            if(found > 100){
                break;
            }
        }

        bufferedWriter.close();

    }

}
