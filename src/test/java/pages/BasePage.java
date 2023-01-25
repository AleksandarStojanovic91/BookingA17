package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    int waitTime = 5;
    int maxRetries = 3;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[title='Naslovna']")
    WebElement homePage;

    public void goToHomePage() throws Exception {
        click(homePage,"Home Page");
    }

    public void click(WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, waitTime);
        Actions actions = new Actions(driver);

        int retryCount = 0;
        while (retryCount<maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

                actions.moveToElement(element).build().perform();

                element.click();
                System.out.println(getCurrentTimeDate()+" Clicked: "+log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount+" to click on: "+log);
                if(retryCount==maxRetries){
                    e.printStackTrace();
                    throw new Exception(getCurrentTimeDate()+" Failed to click element: "+log);
                }
            }
        }
    }

    public void click(WebElement element){
        webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

        element.click();
    }

    public void typeText(WebElement element,String text, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, waitTime);
        Actions actions = new Actions(driver);

        int retryCount = 0;
        while (retryCount<maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));
                webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

                actions.moveToElement(element).build().perform();

                element.click();
                System.out.println(getCurrentTimeDate()+" Clicked: "+log);

                element.clear();
                element.sendKeys(text);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount+" to type '"+text+"' into: "+log);
                if(retryCount==maxRetries){
                    e.printStackTrace();
                    throw new Exception(getCurrentTimeDate()+" Failed to type '"+text+"' into: "+log);
                }
            }
        }
    }

    public void selectByValue(WebElement element, String value, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, waitTime);

        int retryCount = 0;
        while (retryCount<maxRetries) {
            try {
                webDriverWait.until(ExpectedConditions.visibilityOf(element));

                Select age = new Select(element);
                age.selectByValue(value);

                System.out.println(getCurrentTimeDate()+" Selected: "+value+" "+log);
                break;
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry: "+retryCount+" to select: "+value+" "+log);
                if(retryCount==maxRetries){
                    e.printStackTrace();
                    throw new Exception(getCurrentTimeDate()+" Failed to select: "+value+" "+log);
                }
            }
        }
    }

    public String getCurrentTimeDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public String updateXpath(String xpath, String value){
        return xpath.replace("$$", value);
    }

    public boolean elementExistsByXpath(String xpath){
        return driver.findElements(By.xpath(xpath)).size()>0;
    }

}
