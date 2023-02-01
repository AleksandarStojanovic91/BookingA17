package steps.kp;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.kp.KPCategoryPage;
import pages.kp.KPHomePage;
import steps.BaseSteps;

import java.io.IOException;
import java.util.Map;

public class KPSteps extends BaseSteps {

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

    @Given("I am on KP home page")
    public void iAmOnKPHomePage() throws Exception {
        openKPApp(env);
        new KPHomePage(driver).acceptCookies();
    }

    @When("I click on category {string}")
    public void iClickOnCategory(String category) {
        new KPHomePage(driver).clickCategory(category);
    }

    @Then("I should see results for category {string}")
    public void iShouldSeeResultsForCategory(String category) {
        KPCategoryPage kpCategoryPage = new KPCategoryPage(driver);

        kpCategoryPage.verifyCategoryTab(category);
        kpCategoryPage.verifyCategoryResults();
    }

    @And("I should see the url for the {string}")
    public void iShouldSeeTheUrlForThe(String category) {
        new KPCategoryPage(driver).verifyURL(category);
    }

}