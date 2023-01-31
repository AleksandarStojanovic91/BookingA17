package pages.qase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#inputEmail")
    WebElement email;

    @FindBy(css = "#inputPassword")
    WebElement password;

    @FindBy(css = "#btnLogin")
    WebElement loginBtn;

    public void enterEmail(String emailText) throws Exception {
        typeText(email, emailText, "Email");
    }

    public void enterPassword(String passwordText) throws Exception {
        typeText(password, passwordText, "Password");
    }

    public void clickLogin() throws Exception {
        click(loginBtn, "Login");
    }

    public void login(String emailText, String passwordText) throws Exception {
        enterEmail(emailText);
        enterPassword(passwordText);
        clickLogin();
    }

}
