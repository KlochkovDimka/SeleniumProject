package MainMenu.admin;

import baseActions.BaseSeleniumPage;
import page.course.CoursePage;
import page.exam.ExamPage;
import page.interviews.InterviewPage;
import page.module.ModulePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.questions.QuestionPage;
import page.quiz.QuizPage;
import page.recording.RecordingPage;
import page.users.UserPage;

public class MainMenuAdmin extends BaseSeleniumPage {

    @FindBy(xpath = "//a[contains(text(), 'Курсы')]")
    private WebElement course;
    @FindBy(xpath = "//a[contains(text(), 'Экзамены')]")
    private WebElement exam;
    @FindBy(xpath = "//a[contains(text(), 'Интервью')]")
    private WebElement interviews;
    @FindBy(xpath = "//a[contains(text(), 'Модули')]")
    private WebElement module;
    @FindBy(xpath = "//a[contains(text(), 'Вопросы')]")
    private WebElement question;
    @FindBy(xpath = "//a[contains(text(), 'Квизы')]")
    private WebElement quiz;
    @FindBy(xpath = "//a[contains(text(), 'Пользователи')]")
    private WebElement users;
    @FindBy(xpath = "//div[@class='menuProfile']")
    private WebElement nameProfile;


    public MainMenuAdmin() {
        PageFactory.initElements(driver, this);
    }

    public CoursePage courseMenu() throws InterruptedException {
        Thread.sleep(1000);
        course.click();
        return new CoursePage();
    }

    public ExamPage examMenu() throws InterruptedException {
        Thread.sleep(1000);
        exam.click();
        return new ExamPage();
    }

    public InterviewPage interviewMenu() throws InterruptedException {
        Thread.sleep(1000);
        interviews.click();
        return new InterviewPage();
    }

    public ModulePage moduleMenu() throws InterruptedException {
        Thread.sleep(1000);
        module.click();
        return new ModulePage();
    }

    public QuestionPage questionMenu() throws InterruptedException {
        Thread.sleep(1000);
        question.click();
        return new QuestionPage();
    }

    public QuizPage quizMenu() throws InterruptedException {
        Thread.sleep(1000);
        quiz.click();
        return new QuizPage();
    }

    public RecordingPage recording() throws InterruptedException {
        Thread.sleep(1000);
        return new RecordingPage();
    }

    public UserPage userMenu() throws InterruptedException {
        Thread.sleep(1000);
        users.click();
        return new UserPage();
    }

    public String getNameUserProfile() {
        return nameProfile.getText();
    }
}
