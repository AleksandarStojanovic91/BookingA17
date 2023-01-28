package tests;

import excel.ExcelReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.qase.LoginPage;
import pages.qase.ProjectsPage;
import pages.qase.RepositoryPage;
import pages.qase.TestCreatePage;
import steps.BaseSteps;

import java.util.Map;

public class QaseTest extends BaseSteps {

    @BeforeMethod
    @Parameters({"browser","env", ""})
    public void setup(String browser, String env) throws Exception {
        init(browser);
        openApp(env);
    }

    @AfterMethod
    public void tearDown(){
        quit();
    }

    @Test(enabled = false)
    @Parameters({"email","password"})
    public void createProject(String email, String password) throws Exception {
        new LoginPage(driver).login(email,password);
        new ProjectsPage(driver).createProject(
                "Test project",
                "TP",
                "Test project description",
                "Private",
                "All"
        );
    }

    @Test(enabled = false)
    @Parameters({"email","password"})
    public void createTestSuite(String email, String password) throws Exception {
        new LoginPage(driver).login(email,password);
        new ProjectsPage(driver).selectProject("TP");
        new RepositoryPage(driver).createSuite(
                "TestSuite1",
                "Suite description",
                "Test precondition"
        );
    }

    @Test
    @Parameters({"email","password","id"})
    public void createTestCase(String email, String password, String id) throws Exception {
        Map<String, String> data = new ExcelReader().getRowData("QaseTestData","TestCases",Integer.parseInt(id));

        new LoginPage(driver).login(email,password);
        new ProjectsPage(driver).selectProject(data.get("Project"));
        new RepositoryPage(driver).addTestCase(data.get("Suite"));
        new TestCreatePage(driver).createTestCase(data);
    }



}
