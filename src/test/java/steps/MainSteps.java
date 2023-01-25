package steps;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.testng.Reporter;

import java.io.IOException;
import java.util.Map;

public class MainSteps extends BaseSteps {

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");

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


}