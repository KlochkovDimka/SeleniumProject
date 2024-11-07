import authorization.AuthorizationPage;
import authorization.AuthorizationUserPage;
import baseActions.BaseSeleniumTest;
import page.course.CoursePage;
import page.exam.ExamPage;
import page.interviews.InterviewPage;
import page.module.ModulePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.questions.QuestionPage;
import page.quiz.QuizPage;
import page.recording.RecordingPage;
import page.users.UserPage;

import static org.junit.Assert.assertEquals;

public class TestClass extends BaseSeleniumTest {
    private final String adminLogin = "klochkov_dmitriy";
    private final String adminPassword = "\\lWX${6vY_VQ[Is";
    private final String userLogin = getData()[41][3].toString();
    private final String userPassword = getData()[41][4].toString();
    private final String courseName = "Мой курс";
    private final String nameInterview = "Мое интервью";
    private final String nameQuestion = "Мой вопрос";
    private final String nameQuiz = "Мой квиз";
    private final String nameModule = "Мой модуль";
    private final String nameExam = "Мой Экзамен";
    private String idQuestion;
    private String idModule;

    @Test
    public void check() {
        AuthorizationPage authorizationPage = new AuthorizationPage();

        assertEquals(authorizationPage
                .authorizationTest(adminLogin, adminPassword).getNameUserProfile(), "Дмитрий К");
    }

    @Test
    public void interview() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();

        InterviewPage interviewPage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .interviewMenu();

        interviewPage.createdNewInterview(nameInterview);

        assertEquals(interviewPage.getNameInterview(nameInterview), nameInterview);
    }

    @Test
    public void questing() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();

        QuestionPage questionPage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .questionMenu();

        questionPage.createNewQuestion(nameQuestion);
        idQuestion = questionPage.getIdQuestion(nameQuestion);

        assertEquals(questionPage.getNameQuestion(nameQuestion), nameQuestion);
    }

    @Test
    public void quiz() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        QuizPage quizPage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .quizMenu();

        quizPage.createNewQuiz(nameQuiz);

        assertEquals(quizPage.getNameQuiz(nameQuiz), nameQuiz);
    }

    @Test
    public void module() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        ModulePage module = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .moduleMenu();

        module.createNewModule(nameModule, idQuestion);
        idModule = module.getIdModule(nameModule);

        assertEquals(module.getNameModule(nameModule), nameModule);
    }

    @Test
    public void course() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        CoursePage coursePage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .courseMenu();

        coursePage.createNewCourse(courseName, idModule);

        assertEquals(coursePage.getNameCourse(courseName), courseName);
    }

    @Test(dataProvider = "userTest")
    public void newUser(String name,
                        String surName,
                        String email,
                        String login,
                        String password,
                        String roles,
                        String isCV,
                        String calendar,
                        String status) throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();

        UserPage userPage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .userMenu();

        userPage.createdNewUser(name,
                surName,
                email,
                login,
                password,
                roles,
                isCV,
                calendar,
                status);

        userPage.openCourse(courseName);
    }

    @Test
    public void createdRecord() throws InterruptedException {
        AuthorizationUserPage authorizationUserPage = new AuthorizationUserPage();
        RecordingPage recordingPage = authorizationUserPage
                .authorizationTest(userLogin, userPassword)
                .createRecording();
        recordingPage.createRecord(courseName);

        assertEquals(recordingPage.checkStartRecording(), "Раскройте вопрос");
    }

    @Test(dataProvider = "interviewData")
    public void updateInterview(String name,
                                String data,
                                String type,
                                String score,
                                String link) throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.authorizationTest(adminLogin, adminPassword)
                .interviewMenu().updateInterview(nameInterview, name, data, type, score, link);

    }

    @Test
    public void createdExam() throws InterruptedException {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        ExamPage examPage = authorizationPage
                .authorizationTest(adminLogin, adminPassword)
                .examMenu();

        examPage.createdExam(nameExam, idQuestion);

        assertEquals(examPage.getNameExam(nameExam), nameExam);
    }

    @DataProvider(name = "userTest")
    private Object[][] getData() {
        return new Object[][]{
                //1-----------------------------------------------------------------------------------------------------
                {"user1.@>", "", "user1", "", "пароль", "", "false", "11/06/2024", "active_search"},
                //2-----------------------------------------------------------------------------------------------------
                {"user2.@>", "surName", "", "user2", "", "admin", "true", "11/06/2222", "on_project"},
                //3-----------------------------------------------------------------------------------------------------
                {"user3.@>", "surName.<@>", "user3@email.com", "<@->", "password123", "", "", "11/06/2024", ""},
                //4-----------------------------------------------------------------------------------------------------
                {"user4.@>", "", "user4.com", "", "пароль", "user", "true", "11/06/2022", "active_search"},
                //5-----------------------------------------------------------------------------------------------------
                {"user5.@>", "фамилия", "пользователь5", "user5", "<@-.", "", "false", "", "pause_search"},
                //6-----------------------------------------------------------------------------------------------------
                {"user6.@>", "фамилия", "пользователь6", "user6", "<@-.", "", "", "08/25/2024", "on_project"},
                //7-----------------------------------------------------------------------------------------------------
                {"", "", "пользователь7", "логин7", "password123", "user", "false", "08/25/2024", ""},
                //8-----------------------------------------------------------------------------------------------------
                {"", "surName", "user8@email.com", "", "<@-.", "user", "true", "11/06/2024", "pause_search"},
                //9-----------------------------------------------------------------------------------------------------
                {"", "surName.<@>", "user9.com", "user9", "", "admin", "false", "08/25/2024", "on_project"},
                //10-----------------------------------------------------------------------------------------------------
                {"", "", "пользователь10", "логин10", "password123", "", "", "11/06/2222", ""},
                //11-----------------------------------------------------------------------------------------------------
                {"", "фамилия", "", "<@->", "пароль", "user", "true", "11/06/2024", "active_search"},
                //12-----------------------------------------------------------------------------------------------------
                {"", "surName", "user12@email.com", "", "<@-.", "admin", "", "11/06/2022", "pause_search"},
                //13-----------------------------------------------------------------------------------------------------
                {"", "surName.<@>", "user13.com", "user13", "", "", "true", "", "on_project"},
                //14-----------------------------------------------------------------------------------------------------
                {"Имя", "фамилия", "user14@email.com", "user14", "password123", "user", "", "", ""},
                //15-----------------------------------------------------------------------------------------------------
                {"Имя", "surName", "user15.com", "логин15", "пароль", "admin", "true", "08/25/2024", "active_search"},
                //16-----------------------------------------------------------------------------------------------------
                {"Имя", "surName", "пользователь16", "<@->", "<@-.", "", "false", "11/06/2222", "pause_search"},
                //17-----------------------------------------------------------------------------------------------------
                {"Имя", "", "", "", "", "user", "", "11/06/2024", "on_project"},
                //18-----------------------------------------------------------------------------------------------------
                {"Имя", "фамилия", "user18@email.com", "user18", "password123", "", "true", "08/25/2024", ""},
                //19-----------------------------------------------------------------------------------------------------
                {"Имя", "surName", "user19.com", "логин19", "пароль", "user", "false", "11/06/2222", "active_search"},
                //20-----------------------------------------------------------------------------------------------------
                {"Имя", "surName.<@>", "пользователь20", "<@->", "<@-.", "admin", "", "11/06/2024", "pause_search"},
                //21-----------------------------------------------------------------------------------------------------
                {"User", "", "", "", "", "", "true", "11/06/2022", "on_project"},
                //22-----------------------------------------------------------------------------------------------------
                {"User", "surName", "пользователь22", "", "password123", "user", "", "11/06/2022", ""},
                //23-----------------------------------------------------------------------------------------------------
                {"User", "surName.<@>", "", "user23", "пароль", "admin", "true", "", "active_search"},
                //24-----------------------------------------------------------------------------------------------------
                {"User", "", "user24@email.com", "логин24", "<@-.", "", "", "08/25/2024", "pause_search"},
                //25-----------------------------------------------------------------------------------------------------
                {"User", "фамилия", "user25.com", "<@->", "", "user", "true", "11/06/2222", "on_project"},
                //26-----------------------------------------------------------------------------------------------------
                {"User", "surName", "пользователь26", "", "password123", "admin", "false", "11/06/2024", ""},
                //27-----------------------------------------------------------------------------------------------------
                {"User", "surName.<@>", "", "user27", "пароль", "", "", "08/25/2024", "active_search"},
                //28-----------------------------------------------------------------------------------------------------
                {"User", "", "user28@email.com", "логин28", "<@-.", "user", "true", "11/06/2222", "pause_search"},
                //29-----------------------------------------------------------------------------------------------------
                {"User", "фамилия", "user29.com", "<@->", "", "", "false", "11/06/2024", "on_project"},
                //30-----------------------------------------------------------------------------------------------------
                {"user1.@>", "surName.<@>", "user30@email.com", "<@->", "password123", "", "true", "11/06/2024", ""},
                //31-----------------------------------------------------------------------------------------------------
                {"user1.@>", "", "user31.com", "", "пароль", "user", "false", "11/06/2022", "active_search"},
                //32-----------------------------------------------------------------------------------------------------
                {"user1.@>", "фамилия", "пользователь32", "vanyaIvanov", "<@-.", "", "", "", "pause_search"},
                //33-----------------------------------------------------------------------------------------------------
                {"user1.@>", "surName", "", "логин33", "", "user", "true", "08/25/2024", "on_project"},
                //34-----------------------------------------------------------------------------------------------------
                {"user1.@>", "surName.<@>", "user34@email.com", "<@->", "password123", "admin", "", "11/06/2222", ""},
                //35-----------------------------------------------------------------------------------------------------
                {"user1.@>", "", "user35.com", "", "пароль", "", "true", "11/06/2024", "active_search"},
                //36-----------------------------------------------------------------------------------------------------
                {"user1.@>", "фамилия", "пользователь36", "user36", "<@-.", "user", "false", "08/25/2024", "pause_search"},
                //37-----------------------------------------------------------------------------------------------------
                {"user1.@>", "surName", "", "логин37", "", "admin", "", "11/06/2222", "on_project"},
                //38-----------------------------------------------------------------------------------------------------
                {"", "", "пользователь38", "логин38", "password123", "", "false", "11/06/2222", ""},
                //40-----------------------------------------------------------------------------------------------------
                {"", "фамилия", "", "<@->", "пароль", "user", "", "11/06/2024", "active_search"},
                //39-----------------------------------------------------------------------------------------------------
                {"", "surName", "user40@email.com", "", "<@-.", "admin", "true", "11/06/2022", "pause_search"},
                //42-----------------------------------------------------------------------------------------------------
                {"", "surName.<@>", "user41.com", "user41", "", "", "false", "", "on_project"},         //41
                {"", "", "пользователь42", "логин42", "password123", "user", "", "08/25/2024", ""},
                //43-----------------------------------------------------------------------------------------------------
                {"", "фамилия", "", "<@->", "пароль", "", "true", "11/06/2222", "active_search"},
                //44-----------------------------------------------------------------------------------------------------
                {"", "surName", "user44@email.com", "", "<@-.", "user", "", "11/06/2024", "pause_search"},
                //45-----------------------------------------------------------------------------------------------------
                {"", "surName.<@>", "user45.com", "user45", "", "admin", "true", "08/25/2024", "on_project"},
        };
    }

    @DataProvider(name = "interviewData")
    private Object[][] getInterviewData() {
        return new Object[][]{
                //1----------------------------------------------------------------------------------------
                {"Quest1", "11.07.2024", "", "", ""},
                //2----------------------------------------------------------------------------------------
                {"Quest2", "", "hr", "scope2", "link2"},
                //3----------------------------------------------------------------------------------------
                {"Quest3", "11.07.2055", "tech", "оценка3", "ссылка3"},
                //4----------------------------------------------------------------------------------------
                {"Quest4", "11.07.2000", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //5----------------------------------------------------------------------------------------
                {"Quest5", "11.07.2024", "hr", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //6----------------------------------------------------------------------------------------
                {"Quest6", "", "tech", "scope6<|>", "link6<|>"},
                //7----------------------------------------------------------------------------------------
                {"Вопрос7", "", "tech", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", ""},
                //8----------------------------------------------------------------------------------------
                {"Вопрос8", "11.07.2055", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "link2"},
                //9----------------------------------------------------------------------------------------
                {"Вопрос9", "11.07.2000", "hr", "scope6<|>", "ссылка3"},
                //10----------------------------------------------------------------------------------------
                {"Вопрос10", "11.07.2024", "tech", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //11----------------------------------------------------------------------------------------
                {"Вопрос11", "", "", "scope11", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //12----------------------------------------------------------------------------------------
                {"Вопрос12", "11.07.2024", "hr", "оценка12", "link6<|>"},
                //13----------------------------------------------------------------------------------------
                {"Quest13@?>/", "11.07.2055", "hr", "", ""},
                //14----------------------------------------------------------------------------------------
                {"Quest14@?>/", "11.07.2000", "tech", "scope14", "link2"},
                //15----------------------------------------------------------------------------------------
                {"Quest15@?>/", "11.07.2024", "", "оценка15", "ссылка3"},
                //16----------------------------------------------------------------------------------------
                {"Quest16@?>/", "", "hr", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //17----------------------------------------------------------------------------------------
                {"Quest17@?>/", "11.07.2024", "tech", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //18-------------------------------------------------------------------------------------
                {"Quest18@?>/", "", "", "scope6<|>", "link6<|>"},
                //19----------------------------------------------------------------------------------------
                {"", "11.07.2000", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", ""},
                //20----------------------------------------------------------------------------------------
                {"", "11.07.2024", "hr", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "link2"},
                //21----------------------------------------------------------------------------------------
                {"", "", "tech", "scope6<|>", "ссылка3"},
                //22----------------------------------------------------------------------------------------
                {"", "11.07.2024", "", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //23----------------------------------------------------------------------------------------
                {"", "", "hr", "scope23", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //24----------------------------------------------------------------------------------------
                {"", "11.07.2055", "tech", "оценка24", "link6<|>"},
                //25----------------------------------------------------------------------------------------
                {"null", "11.07.2024", "tech", "", ""},
                //26----------------------------------------------------------------------------------------
                {"null", "", "", "scope26", "link2"},
                //27----------------------------------------------------------------------------------------
                {"null", "11.07.2024", "hr", "оценка27", "ссылка3"},
                //28----------------------------------------------------------------------------------------
                {"null", "", "tech", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //29----------------------------------------------------------------------------------------
                {"null", "11.07.2055", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //30----------------------------------------------------------------------------------------
                {"null", "11.07.2000", "hr", "scope30<|>", "link6<|>"},
                //31----------------------------------------------------------------------------------------
                {"Quest31", "", "hr", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i", ""},
                //32----------------------------------------------------------------------------------------
                {"Quest32", "11.07.2024", "tech", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in", "link2"},
                //33----------------------------------------------------------------------------------------
                {"Quest33", "", "", "scope33<|>", "ссылка3"},
                //34----------------------------------------------------------------------------------------
                {"Quest34", "11.07.2055", "hr", "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i"},
                //35----------------------------------------------------------------------------------------
                {"Quest35", "11.07.2000", "tech", "scope35", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"},
                //36----------------------------------------------------------------------------------------
                {"Quest36", "11.07.2024", "", "оценка36", "link6<|>"},
        };
    }
}
