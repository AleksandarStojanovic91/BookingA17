package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BookingStaysPage {
    WebDriver driver;

    public BookingStaysPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


}
