package page.quiz;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//textarea[@class='w-md-editor-text-input ']")
    private WebElement textarea;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;
    @FindBy(xpath = "//input[@class='search form-control search_table_item']")
    private WebElement search;
    @FindBy(xpath = "//tbody//span")
    private WebElement nameQuiz;

    public QuizPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameQuiz(String name) throws InterruptedException {
        search.sendKeys(name);
        Thread.sleep(1000);
        return nameQuiz.getText();
    }

    public void createNewQuiz(String value) {
        buttonAdd.click();
        textarea.sendKeys(value);
        buttonCreate.click();
    }
}
