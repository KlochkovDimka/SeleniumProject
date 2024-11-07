package page.course;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//button[@class='btn btn-xs btn-default btn-push-add']")
    private WebElement buttonPlusModule;

    @FindBy(xpath = "//input[@class='form-control ']")
    private WebElement inputNameInterview;

    @FindBy(xpath = "//input[@class='form-control ' and @placeholder='Модуль']")
    private WebElement inputModuleId;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;
    @FindBy(xpath = "//input[@class='search form-control search_table_item']")
    WebElement search;
    @FindBy(xpath = "//tbody//span")
    private WebElement nameCourse;

    public CoursePage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameCourse(String name) throws InterruptedException {
        search.sendKeys(name);
        Thread.sleep(1000);
        return nameCourse.getText();
    }

    public void createNewCourse(String nameCourse, String id) {
        buttonAdd.click();
        inputNameInterview.sendKeys(nameCourse);
        buttonPlusModule.click();
        inputModuleId.sendKeys(id);
        buttonCreate.click();
    }
}
