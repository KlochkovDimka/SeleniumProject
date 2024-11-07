package authorization;

import MainMenu.admin.MainMenuAdmin;
import baseActions.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    public AuthorizationPage() {
        String URI = "https://aqa-admin.javacode.ru/login";
        driver.get(URI);
        PageFactory.initElements(driver, this);
    }

    public MainMenuAdmin authorizationTest(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password, Keys.ENTER);
        return new MainMenuAdmin();
    }
}
