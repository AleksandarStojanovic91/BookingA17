package steps;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.BookingCarRentalsPage;
import pages.booking.BookingStaysPage;

import java.io.IOException;
import java.util.Map;

public class MainSteps extends BaseSteps {

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

    Map<String, String> data;

    @Before
    public void setup() throws Exception {
        init(browser);
    }

    @After
    public void tearDown(){
        if(quit.equalsIgnoreCase("Yes")) {
            quit();
        }
    }

    @Given("I load test data from {string} {string} {string}")
    public void iLoadTestDataFrom(String file, String sheet, String row) throws IOException {
        data = new ExcelReader().getRowData(file,sheet,Integer.parseInt(row));
    }

    @Given("I load test data from {string} {string} for {string}")
    public void iLoadTestDataFromFor(String file, String sheet, String tc_id) throws IOException {
        data = new ExcelReader().getRowDataByID(file,sheet,tc_id);
    }

    @Given("I navigate to booking stays page")
    public void iNavigateToBookingStaysPage() throws Exception {
        openApp(env);
    }

    @When("I set destination to {string}")
    public void iSetDestinationTo(String destination) throws Exception {
        new BookingStaysPage(driver).setDestination(destination);
    }

    @And("I set check in date to {string} and check out date to {string}")
    public void iSetCheckInDateToAndCheckOutDateTo(String checkIn, String checkOut) throws Exception {
        new BookingStaysPage(driver).setCheckInDateAndCheckOutDate(checkIn, checkOut);
    }

    @And("I add {string} adults and {string} children with age {string} and {string} rooms")
    public void iAddAdultsAndChildrenWithAgeAndRooms(String adultsNum, String childrenNum, String childAge, String rooms) throws Exception {
        new BookingStaysPage(driver).addGuests(adultsNum, childrenNum, childAge, rooms);
    }

    @And("I click search button")
    public void iClickSearchButton() throws Exception {
        new BookingStaysPage(driver).search();
    }

    //car rentals page testing
    @Given("I navigate to booking car rentals page")
    public void iNavigateToBookingCarRentalsPage() throws Exception {
        openApp(env);
        new BookingCarRentalsPage(driver).gotoCarRentals();
    }

    @When("I set pickup location to {string} and same return location {string} and return location is {string}")
    public void iSetPickupLocationToAndSameReturnLocationAndReturnLocationIs(String location, String returRadLocation,String returnLocation) throws Exception {
        new BookingCarRentalsPage(driver).selectLocation(location,returRadLocation,returnLocation);
    }
    @And("I set drivers age to {string}")
    public void iSetDriversAgeTo(String driversAge) throws InterruptedException {
        new BookingCarRentalsPage(driver).setDriversAge(driversAge);
    }

    @And("I set from date to {string} and until date to {string}")
    public void iSetFromDateToAndUntilDateTo(String from, String until) throws Exception {
        new BookingCarRentalsPage(driver).setFromDateToAndUntilDate(from, until);

    }
    @And("I click search button on car rentals")
    public void iClickSearchButtonOnCarRentals() throws Exception {
        new BookingCarRentalsPage(driver).search();
    }
    //car rentals page testing end

}