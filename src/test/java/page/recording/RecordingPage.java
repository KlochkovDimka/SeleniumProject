package page.recording;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecordingPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement inputLogin;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//div[contains(text(),'Курсы')]")
    private WebElement course;
    @FindBy(xpath = "//div[@class='courseTitle'][contains(text(), 'КУРС')]")
    private WebElement quest;
    @FindBy(xpath = "//button[@class='btn-primary btn btn btn-sm btn-primary']")
    private WebElement checkRecording;
    @FindBy(xpath = "//div[@class='circle delay4']")
    private WebElement recordingCircle;
    @FindBy(xpath = "//span[contains(text(), 5)]")
    private WebElement ratingFive;
    @FindBy(xpath = "//button[contains(text(), 'Дальше')]")
    private WebElement buttonNext;
    @FindBy(xpath = "//small[contains(text(),'Раскройте вопрос')]")
    private WebElement checkRecord;

    public RecordingPage() {
        PageFactory.initElements(driver, this);
    }

    public void createRecord(String course) throws InterruptedException {
        driver.findElement(
                        By.xpath("//div[@class='courseTitle'][contains(text(), '" + course + "')]"))
                .click();
        checkRecording.click();
    }

    public String checkStartRecording() throws InterruptedException {
        Thread.sleep(1000);
        return checkRecord.getText();
    }
}
