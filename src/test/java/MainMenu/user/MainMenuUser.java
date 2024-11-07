package MainMenu.user;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.recording.RecordingPage;

public class MainMenuUser extends BaseSeleniumPage {
    @FindBy(xpath = "//div[contains(text(),'Курсы')]")
    private WebElement buttonCourse;

    public MainMenuUser() {
        PageFactory.initElements(driver, this);
    }

    public RecordingPage createRecording() throws InterruptedException {
        Thread.sleep(1000);
        buttonCourse.click();
        return new RecordingPage();
    }
}
