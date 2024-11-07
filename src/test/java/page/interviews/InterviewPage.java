package page.interviews;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InterviewPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;
    @FindBy(xpath = "//input[@class='form-control ']")
    private WebElement inputNameInterview;
    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;
    @FindBy(xpath = "//div[@class='col-xs-12']//i")
    private WebElement exit;
    @FindBy(xpath = "//input[@type='search']")
    private WebElement search;
    @FindBy(xpath = "//tbody//td")
    private WebElement searchNameInterviews;
    @FindBy(xpath = "//i[@class='fa fa-times pull-right']")
    private WebElement exits;
    @FindBy(xpath = "//tbody[@class='error']//span")
    private WebElement interview;
    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement updateName;
    @FindBy(xpath = "//div[@_key='1']//input")
    private WebElement updateData;
    @FindBy(xpath = "//div[@_key='2']//option[@value='0']")
    private WebElement typeNull;
    @FindBy(xpath = "//div[@_key='2']//option[@value='HR']")
    private WebElement typeHR;
    @FindBy(xpath = "//div[@_key='2']//option[@value='tech']")
    private WebElement typeTech;
    @FindBy(xpath = "//textarea[@class='form-control']")
    private WebElement inputScore;
    @FindBy(xpath = "//input[@placeholder='Видео ссылка']")
    private WebElement inputLink;
    @FindBy(xpath = "//button[contains(text(), 'Сохранить')]")
    private WebElement save;


    public InterviewPage() {
        PageFactory.initElements(driver, this);
    }

    public void createdNewInterview(String name) {
        buttonAdd.click();
        inputNameInterview.sendKeys(name);
        exits.click();
        buttonCreate.click();
    }

    public String getNameInterview(String name) throws InterruptedException {
        search.sendKeys(name);
        Thread.sleep(1000);
        return searchNameInterviews.getText();
    }

    public void updateInterview(String oldNameInterview,
                                String name,
                                String data,
                                String type,
                                String score,
                                String link) throws InterruptedException {
        search.sendKeys(oldNameInterview);
        Thread.sleep(1000);
        interview.click();
        updateName.sendKeys(name);
        updateData.sendKeys(data);
        switch (type.toLowerCase()) {
            case "0" -> typeNull.click();
            case "hr" -> typeHR.click();
            case "tech" -> typeTech.click();
        }
        inputScore.sendKeys(score);
        inputLink.sendKeys(link);
        save.click();
    }
}
