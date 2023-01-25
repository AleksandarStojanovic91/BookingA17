package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookingStaysPage extends BasePage {

    public BookingStaysPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='ss']")
    WebElement destinationInput;

    @FindBy(xpath = "//*[text()='Check-in Date' or text()='Check-in']")
    WebElement dateInput;

    @FindBy(css = "[data-bui-ref='calendar-next']")
    WebElement nextArrow;

    @FindBy(xpath = "//*[@data-adults-count or @data-testid='occupancy-config']")
    WebElement guests;

    @FindBy(xpath = "//*[text()='Check-in Date' or text()='Check-in' or @data-testid='flexible-dates-container']")
    List<WebElement> calendar;

    @FindBy(xpath = "//*[text()='Adults']/../..//button")
    List<WebElement> addOrSubAdult;

    @FindBy(xpath = "(//*[text()='Rooms']/../..//button)[2]")
    WebElement addRoom;

    @FindBy(xpath = "(//*[text()='Children']/../..//button)[2]")
    WebElement addChildren;

    @FindBy(xpath = "//select[@name='age' or contains(@aria-label,'Child')]")
    List<WebElement> childrenAge;

    @FindBy(xpath = "//button/span[contains(text(),'Search')]/..")
    WebElement search;

    String destinationRecommendation1 = "//span[@class='search_hl_name' and text()='$$']";
    String destinationRecommendation2 = "//div[@data-testid='destination-container']//div[text()='$$']";
    String date = "//*[@data-date='$$']"; // date format used - 2023-01-25

    public void setDestination(String destination) throws Exception {
        enterDestination(destination);
        selectRecommendedDestination(destination);
    }

    public void enterDestination(String destination) throws Exception {
        typeText(destinationInput, destination, "Destination Input");
        Thread.sleep(1000);
    }

    public void selectRecommendedDestination(String destination) throws Exception {
        if (elementExistsByXpath(updateXpath(destinationRecommendation1, destination))) {
            click(driver.findElement(By.xpath(updateXpath(destinationRecommendation1, destination))), "Destination Recommendation 1 - " + destination);
        } else {
            click(driver.findElement(By.xpath(updateXpath(destinationRecommendation2, destination))), "Destination Recommendation 2 - " + destination);
        }
    }

    public void setCheckInDateAndCheckOutDate(String checkIn, String checkOut) throws Exception {
        openCalendar();

        String checkInXpath = updateXpath(date, checkIn);
        String checkOutXpath = updateXpath(date, checkOut);

        while (true) {
            if (elementExistsByXpath(checkInXpath)) {
                driver.findElement(By.xpath(checkInXpath)).click();
                break;
            } else {
                clickCalendarNext();
            }
        }

        while (true) {
            if (elementExistsByXpath(checkOutXpath)) {
                driver.findElement(By.xpath(checkOutXpath)).click();
                break;
            } else {
                clickCalendarNext();
            }
        }

    }

    public void openCalendar() throws Exception {
        if (calendar.size() == 1){
            if(!calendar.get(0).isDisplayed()){
                click(dateInput, "Check In - Check Out Calendar");
                Thread.sleep(1000);
            }
        } else {
            if(!calendar.get(1).isDisplayed()){
                click(dateInput, "Check In - Check Out Calendar");
                Thread.sleep(1000);
            }
        }
    }

    public void clickCalendarNext() throws Exception {
        click(nextArrow, "Calendar Next");
    }

    public void addGuests(String adultsNum, String childrenNum, String childAge, String rooms) throws Exception {
        openGuestsMenu();
        addAdults(adultsNum);
        addChildren(childrenNum, childAge);
        addRooms(rooms);
    }

    public void openGuestsMenu() throws Exception {
        click(guests, "Guests Menu");
    }

    public void addAdults(String adultsNum) throws Exception {
        if(adultsNum.equalsIgnoreCase("1")){
            click(addOrSubAdult.get(0), "Click - Adults");
        } else if (adultsNum.equalsIgnoreCase("2")){
            //Do nothing
        } {
            for(int i = 2; i<Integer.parseInt(adultsNum); i++){
                click(addOrSubAdult.get(1), "Click + Adults");
            }
        }
    }

    public void addChildren(String childrenNum, String childAge) throws Exception {
        for(int i = 0; i<Integer.parseInt(childrenNum); i++){
            click(addChildren, "Click + Children");
            selectByValue(childrenAge.get(i),childAge, "Select Child Age");
        }
    }

    public void addRooms(String rooms) throws Exception {
        for(int i = 1; i<Integer.parseInt(rooms); i++){
                click(addRoom, "Click + Rooms");
            }
    }

    public void search() throws Exception {
        click(search, "Search");
    }

}
