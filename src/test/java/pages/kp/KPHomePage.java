package pages.kp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class KPHomePage extends BasePage {

    public KPHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Prihvatam']")
    WebElement acceptCookies;

    String categoryLink = "//div[contains(@class,'CategoryList')]//a[text()='$$']";

    public void clickCategory(String category){
        click(driver.findElement(By.xpath(categoryLink.replace("$$",category))));
    }

    public void acceptCookies(){
        click(acceptCookies);
    }

}