package authorization;

import MainMenu.user.MainMenuUser;
import baseActions.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationUserPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;


    public AuthorizationUserPage() {
        String URI = "https://aqa.javacode.ru/main";
        driver.get(URI);
        PageFactory.initElements(driver, this);
    }

    public MainMenuUser authorizationTest(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password, Keys.ENTER);
        return new MainMenuUser();
    }
}
