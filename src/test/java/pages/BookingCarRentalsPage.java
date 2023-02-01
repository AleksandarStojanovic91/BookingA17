package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookingCarRentalsPage extends BasePage{
    public BookingCarRentalsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//a[@id='cars' or @data-decider-header='bookinggo'] ")
    WebElement carRentalsTab;
    String destinationLocation1 = "//li[contains(@data-value,'$$')]";
    String destinationLocation2 = "//div[@data-testid='destination-container']//div[text()='$$']";
    String destinationReturnLocation1 = "//li[contains(@data-value,'$$')]";
    String destinationReturnLocation2 = "//div[@data-testid='destination-container']//div[text()='$$']";
    @FindBy(css = "[name='ss_origin']")
    WebElement locationInput;
    @FindBy(css = "[name='ss']")
    WebElement locationReturnInput;
    @FindBy(css = "label[for='return-location-different']")
    WebElement locationRad;
    String driversAge="//label[contains(@class,'driverAgeCheckboxLabel')]";  //label field for click for drivers age
    String driversAgeCh="//label/input[@name='driversAgeCheckbox']";  //checkbox field ,name attribute
    String inputDriversAge="//input[@id='driverAgeInput']"; //input field ,id attribute
    String dateCarDay = "//tbody/tr/td/span[contains(text(),'$$')]"; //set day data  from date
    String dateCarMonthYear = "//tr[@class='c2-month-header']//th[contains(text(),'$$')]/ancestor::table[@class='c2-month-table']"; //find table  from date
    String dateCarMonthYearUntil = "//table/thead/tr[1]//th[contains(text(),'$$')]/ancestor::table"; //table for month for until date
    String checkInCal ="//div[@class='c2-calendar']/div[contains(text(),'Check-in')]/parent::div";//check in calendar
    String checkOutCal ="//div[@class='c2-calendar']/div[contains(text(),'Check-out')]/parent::div";//check out calendar
    @FindBy(xpath = "//select[@name='checkinTime']")
    WebElement selectCheckInHour;
    @FindBy(xpath = "//select[@name='checkinTimeMinutes']")
    WebElement selectCheckInTimeMinutes;
    @FindBy(xpath = "//select[@name='checkoutTime']")
    WebElement selectCheckOutHour;
    @FindBy(xpath = "//select[@name='checkoutTimeMinutes']")
    WebElement selectCheckOutTimeMinutes;
    @FindBy(xpath = "//*[@data-calendar2-type='checkin']//button")
    WebElement dateInputCar; //click to  open calendar from
    @FindBy(xpath = "//*[ @data-calendar2-type='checkout']")
    WebElement dateInputCarUntil; //click to  open calendar until
    @FindBy(xpath = "//div[@class='c2-calendar']/div[contains(text(),'Check-in')]/parent::div//div[contains(@class,'c2-button-further')]/span[@class='c2-button-inner']")
    WebElement nextCarArrow;//next arrow on  calendar
    @FindBy(xpath = "//div[@class='c2-calendar']/div[contains(text(),'Check-out')]/parent::div//div[contains(@class,'c2-button-further')]/span[@class='c2-button-inner']")
    WebElement nextCarArrowUntil;//next arrow on  calendar for until date
    @FindBy(xpath = "(//div[@class='c2-calendar'])") //dropdown from calendar container
    List<WebElement> calendar;
    @FindBy(xpath = "//button/span[contains(text(),'Search')]/..")
    WebElement searchCarRentals;
    public void gotoCarRentals() throws Exception {
        click(carRentalsTab, "click to car rentals tab to go to car rentals page");
    }
    public void selectLocation(String location,String returRadLocation,String returnLocation) throws Exception {
        if(returRadLocation.equalsIgnoreCase("Yes")){
            enterLocation(location);
            selectRecommendedLocation(location);
        }
        else if(returRadLocation.equalsIgnoreCase("No")){
            enterLocation(location);
            selectRecommendedLocation(location);
            locationRad.click();
            enterReturnLocation(returnLocation);
            selectReturnRecommendedLocation(returnLocation);
        }
    }
    public void enterLocation(String location) throws Exception {
        typeText(locationInput, location, "Location Input");
        Thread.sleep(1000);
    }
    public void enterReturnLocation(String returnLocation) throws Exception {
        typeText(locationReturnInput, returnLocation, "Location Return Input");
        Thread.sleep(1000);
    }
    public void selectRecommendedLocation(String location) throws Exception {
        if (elementExistsByXpath(updateXpath(destinationLocation1, location))) {
            click(driver.findElement(By.xpath(updateXpath(destinationLocation1, location))), "Location Recommendation 1 - " + location);
        } else {
            click(driver.findElement(By.xpath(updateXpath(destinationLocation2, location))), "Location Recommendation 2 - " + location);
        }
    }
    public void selectReturnRecommendedLocation(String returnLocation) throws Exception {
        if (elementExistsByXpath(updateXpath(destinationReturnLocation1, returnLocation))) {
            click(driver.findElement(By.xpath(updateXpath(destinationReturnLocation1, returnLocation))), "Return Location Recommendation 1 - " + returnLocation);
        } else {
            click(driver.findElement(By.xpath(updateXpath(destinationReturnLocation2, returnLocation))), "Return Location Recommendation 2 - " + returnLocation);
        }
    }
    public void setDriversAge(String driversAgePar) throws InterruptedException {

       if ((driver.findElement(By.xpath(driversAgeCh)).isSelected())) {
           driver.findElement(By.xpath(driversAge)).click();
           driver.findElement(By.xpath(inputDriversAge)).sendKeys(driversAgePar);
        }
    }
    public String datePart(String date,String part) {
        String[] fromSplitted=date.split(",");//split date with comma to get parts of date and time,equalsIgnoreCase
        String dateFrom=fromSplitted[0]; //date part
        String[] dateSplitted=dateFrom.split(" ");
        String hourminutes=fromSplitted[1].trim();
        String[] hourminutesSplitted=hourminutes.split(":");

        String datePart="";
        switch(part.toLowerCase()) {
            case "day": datePart= dateSplitted[1];
            break;
            case "monthyear": datePart= dateSplitted[0]+" "+dateSplitted[2];
            break;
            case "month": datePart= dateSplitted[0];
            break;
            case "hour": datePart= hourminutesSplitted[0];
            break;
            case "minutes": datePart= hourminutesSplitted[1];
                break;
        }
        return datePart;
    }
    public void setFromDateToAndUntilDate(String from, String until) throws Exception {
        openCalendarCarFrom();
        //fom date
        String tab=updateXpath(dateCarMonthYear,datePart(from,"monthyear"));
        String tabUntil=updateXpath(dateCarMonthYearUntil,datePart(until,"monthyear"));
        String checkInXpath = updateXpath(dateCarDay, datePart(from,"day"));
        String checkOutXpath = updateXpath(dateCarDay, datePart(until,"day"));
        //open calendar and set dates
        while (true) {
            if (elementExistsByXpath(checkInCal+tab+checkInXpath)) {
                if((driver.findElement(By.xpath(checkInCal+tab+checkInXpath))).isDisplayed()) {
                    driver.findElement(By.xpath(checkInCal + tab + checkInXpath)).click();
                    //select time from date
                    selectTime("checkin", datePart(from, "hour"), datePart(from, "minutes"));
                    break;
                }
                else {
                    clickCalendarCarNext(nextCarArrow,"Next from");
                }
            } else {
                clickCalendarCarNext(nextCarArrow,"Next from");
            }
        }
        while (true) {
            if (elementExistsByXpath(checkOutCal+tabUntil+checkOutXpath)) {
                if((driver.findElement(By.xpath(checkOutCal+tabUntil+checkOutXpath))).isDisplayed()){
                    selectTime("checkout",datePart(until,"hour"),datePart(until,"minutes"));
                    driver.findElement(By.xpath(checkOutCal+tabUntil+checkOutXpath)).click();
                    break;
                }
                else
                {
                    clickCalendarCarNext(nextCarArrowUntil,"Next until");
                }

            } else {
                clickCalendarCarNext(nextCarArrowUntil,"Next until");
            }
        }
    }
    public void selectTime(String calendar,String hour,String minutes) throws Exception {
        switch(calendar.toLowerCase()) {
            case "checkin":
                selectByValue(selectCheckInHour, hour,"select hour from");
                selectByValue(selectCheckInTimeMinutes, minutes,"select minutes from");
                break;
            case "checkout":
                selectByValue(selectCheckOutHour, hour,"select hour until");
                selectByValue(selectCheckOutTimeMinutes,minutes, "select minutes until");
                break;
        }
    }
    public void openCalendarCarFrom() throws Exception {
       if (calendar.size() == 1){
            if(!calendar.get(0).isDisplayed()){
                click(dateInputCar, "Open From Car Calendar");
                Thread.sleep(1000);
            }
        } else {
            if(!calendar.get(1).isDisplayed()){
                click(dateInputCar, "Open From Car Calendar");
                Thread.sleep(1000);
            }
        }
    }
    public void clickCalendarCarNext(WebElement el, String log) throws Exception {
        click(el, log);
    }
    public void search() throws Exception {
        click(searchCarRentals, "Search car rentals");
    }
}
