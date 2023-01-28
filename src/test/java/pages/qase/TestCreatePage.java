package pages.qase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;
import java.util.Map;

public class TestCreatePage extends BasePage {

    public TestCreatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#title")
    WebElement title;

    @FindBy(xpath = "//div[contains(@class,'toastui-editor-contents')]/*[contains(@data-placeholder,'For example')]")
    WebElement description;

    @FindBy(xpath = "//*[text()='Automation status']/..//button")
    WebElement automationStatusDropdown;

    @FindBy(xpath = "//div[text()='Automated']")
    WebElement automatedFiled;

    @FindBy(xpath = "//*[text()=' Add step']/..")
    WebElement addStep;

    @FindBy(css = ".toastui-editor-contents > [data-placeholder='Step Action']")
    List<WebElement> stepAction;

    @FindBy(css = ".toastui-editor-contents > [data-placeholder='Data']")
    List<WebElement> stepData;

    @FindBy(css = ".toastui-editor-contents > [data-placeholder='Expected result']")
    List<WebElement> expectedResult;

    @FindBy(css = "#save-case")
    WebElement saveCase;

    public void createTestCase(Map<String, String> data) throws Exception {
        typeText(title, data.get("Title"), "Title");
        typeText(description, data.get("Description"), "Description");
        click(automationStatusDropdown);
        click(automatedFiled);

        for(int i = 1; i <= Integer.parseInt(data.get("StepNum")); i++) {
            click(addStep);
            typeText(stepAction.get(0), data.get("StepAction_"+i), "Step");
            typeText(stepData.get(0), data.get("StepData_"+i), "StepData");
            typeText(expectedResult.get(0), data.get("ExpectedResults_"+i), "ExpectedResults");
        }
        click(saveCase);
    }
}