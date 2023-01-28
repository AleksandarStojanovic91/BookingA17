package pages.qase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class ProjectsPage extends BasePage {

    public ProjectsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#createButton")
    WebElement createButton;

    @FindBy(css = "#project-name")
    WebElement projectName;

    @FindBy(css = "#project-code")
    WebElement projectCode;

    @FindBy(css = "#description-area")
    WebElement descriptionArea;

    @FindBy(css = "[value='private']")
    WebElement privateRadio;

    @FindBy(css = "[value='public']")
    WebElement publicRadio;

    @FindBy(css = "[value='all']")
    WebElement all;

    @FindBy(css = "[value='group']")
    WebElement group;

    @FindBy(css = "[value='none']")
    WebElement none;

    @FindBy(css = ".MfLTYs > .tscvgR ")
    WebElement createProject;

    String project = ".project-name[href='/project/$$']";

    public void createProject(String projectNameText, String projectCodeText, String projectDescText, String projectAccess, String memberAccess) throws Exception {

        click(createButton,"Create a new project");

        typeText(projectName, projectNameText, "Project name");
        typeText(projectCode, projectCodeText, "Project code");
        typeText(descriptionArea, projectDescText, "project description");

        if(projectAccess.equalsIgnoreCase("Private")){
            if (memberAccess.equalsIgnoreCase("Group")){
                click(group, "Member access group");
            } else if(memberAccess.equalsIgnoreCase("None")){
                click(none, "Member access none");
            }
        } else {
            click(publicRadio, "Project access public");
        }

        click(createProject, "Create project");
    }

    public void selectProject(String projectName) throws Exception {
        click(driver.findElement(By.cssSelector(project.replace("$$",projectName))), "Project: "+projectName);
    }
}
