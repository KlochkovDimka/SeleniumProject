package page.questions;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuestionPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//textarea[@class='w-md-editor-text-input ']")
    private WebElement textarea;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;
    @FindBy(xpath = "//tbody//td")
    private WebElement nameQuestion;
    @FindBy(xpath = "//input[@class='search form-control search_table_item']")
    private WebElement search;
    @FindBy(xpath = "//tbody//td[13]//span")
    private String idQuestion;

    public QuestionPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameQuestion(String name) throws InterruptedException {
        search.sendKeys(name);
        Thread.sleep(1000);
        return nameQuestion.getText();
    }

    public String getIdQuestion(String name) {
        search.sendKeys(name);
        return idQuestion;
    }

    public void createNewQuestion(String value) {
        buttonAdd.click();
        textarea.sendKeys(value);
        buttonCreate.click();
    }
}
