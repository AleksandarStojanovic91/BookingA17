package pages.qase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;

public class RepositoryPage extends BasePage {

    public RepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#create-suite-button")
    WebElement createSuiteButton;

    @FindBy(css = "#title")
    WebElement title;

    @FindBy(xpath = "//*[@for='description']/../..//p")
    WebElement description;

    @FindBy(xpath = "//*[@for='preconditions']/../..//p")
    WebElement preconditions;

    @FindBy(css = ".MfLTYs > .tscvgR ")
    WebElement create;

    @FindBy(css = ".AreGx_[href='/case/TP/create/1']")
    WebElement createCase;

    String addTestCase = "//span[text()='$$']/../..//i[contains(@class,'fa-plus')]";
    String suiteName = "//span[text()='$$']";

    public void createSuite(String titleText, String desc, String precondition) throws Exception {
        click(createSuiteButton, "Create suite button");
        typeText(title, titleText, "Title");
        typeText(description, desc, "Description");
        typeText(preconditions, precondition, "Precondition");
        click(create, "Create");
    }

    public void addTestCase(String suiteNameText) throws Exception {
        hover(driver.findElement(By.xpath(suiteName.replace("$$",suiteNameText))));
        click(driver.findElement(By.xpath(addTestCase.replace("$$",suiteNameText))), "Add test");
        click(createCase, "Create case");
    }

}