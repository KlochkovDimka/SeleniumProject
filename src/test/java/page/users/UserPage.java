package page.users;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;
    @FindBy(xpath = "//div[@_key='2']//input")
    private WebElement inputName;
    @FindBy(xpath = "//div[@_key='3']//input")
    private WebElement surname;
    @FindBy(xpath = "//div[@_key='4']//input")
    private WebElement inputEmail;
    @FindBy(xpath = "//div[@_key='5']//input")
    private WebElement inputLogin;
    @FindBy(xpath = "//div[@_key='6']//input")
    private WebElement inputPassword;
    @FindBy(xpath = "//div[@_key='7']//input")
    private WebElement inputRoles;
    @FindBy(xpath = "//div[@_key='8']//input")
    private WebElement inputIsCV;
    @FindBy(xpath = "//div[@_key='9']//input")
    private WebElement inputCalendar;
    @FindBy(xpath = "//div[@_key='10']//button[contains(text(), '---')]")
    private WebElement buttonStatusNull;
    @FindBy(xpath = "//div[@_key='10']//button[contains(text(), 'На поиске')]")
    private WebElement buttonStatusOnSearch;
    @FindBy(xpath = "//div[@_key='10']//button[contains(text(), 'Пауза поиска')]")
    private WebElement buttonStatusPauseSearch;
    @FindBy(xpath = "//div[@_key='10']//button[contains(text(), 'На проекте')]")
    private WebElement buttonStatusOnProject;
    @FindBy(xpath = "//div[@_key='18']//button")
    private WebElement buttonUserCreate;
    @FindBy(xpath = "//td[@class='text-center nothing-found false']")
    private WebElement usersNotFound;

    @FindBy(xpath = "//i[@class='fa fa-times pull-right']")
    private WebElement exit;

    @FindBy(xpath = "//tbody//span")
    private WebElement userProfile;
    @FindBy(xpath = "//button[contains(text(), '+ Запись')]")
    private WebElement buttonAddRecord;
    @FindBy(xpath = "//input[@autocomplete='new-password']")
    private WebElement addNameCourse;
    @FindBy(xpath = "//button[contains(text(), 'Сохранить')]")
    private WebElement saveUpdateUser;

    public UserPage() {
        PageFactory.initElements(driver, this);
    }

    public void createdNewUser(String name,
                               String surName,
                               String email,
                               String login,
                               String password,
                               String roles,
                               String isCV,
                               String calendar,
                               String status) {
        buttonAdd.click();
        inputName.sendKeys(name);
        surname.sendKeys(surName);
        inputPassword.sendKeys(password);
        inputEmail.sendKeys(email);
        inputLogin.sendKeys(login);
        inputRoles.sendKeys(roles);
        if (Boolean.parseBoolean(isCV)) {
            inputIsCV.click();                 //карьерный трек
        }
        inputCalendar.sendKeys(calendar);
        switch (status) {
            case "active_search" -> buttonStatusOnSearch.click();
            case "on_project" -> buttonStatusOnProject.click();
            case "pause_search" -> buttonStatusPauseSearch.click();
            default -> buttonStatusNull.click();
        }
        buttonUserCreate.click();
    }

    public void openCourse(String nameCourse) {
        userProfile.click();
        buttonAddRecord.click();
        addNameCourse.sendKeys(nameCourse, Keys.ENTER);
        saveUpdateUser.click();
    }
}
