package page.exam;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonCreated;
    @FindBy(xpath = "//div[@_key='0'][@class='col-sm-3']//input")
    private WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='ID для перемещения']")
    private WebElement inputIdQuestion;
    @FindBy(xpath = "//button[contains(text(), 'Переместить по Id')]")
    private WebElement buttonMoveQuestionById;
    @FindBy(xpath = "//button[contains(text(), 'Create')]")
    private WebElement saveExam;
    @FindBy(xpath = "//tbody//span")
    private WebElement getNameExam;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement search;


    public ExamPage() {
        PageFactory.initElements(driver, this);
    }

    public void createdExam(String name, String idQuestion) {
        buttonCreated.click();
        inputName.sendKeys(name);
        inputIdQuestion.sendKeys(idQuestion);
        buttonMoveQuestionById.click();
        saveExam.click();
    }

    public String getNameExam(String nameExam) throws InterruptedException {
        search.sendKeys(nameExam);
        Thread.sleep(1000);
        return getNameExam.getText();
    }
}
