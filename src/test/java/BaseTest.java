import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    final String BASE_URL = "http://chatty.telran-edu.de:8089/login";

    @Before

    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 4000;

        open(BASE_URL);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @After
    public void tearDown() {
        closeWebDriver();

    }
    LoginPage loginPage = new LoginPage();
    BlogPage blogPage = new BlogPage();
    RegistrationPage registrationPage = new RegistrationPage();
    CreatePostPage createPostPage = new CreatePostPage();

    MyDraftsPage myDraftsPage = new MyDraftsPage();
    MyPostsPage myPostsPage = new MyPostsPage();

    ProfilePage profilePage = new ProfilePage();
    PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
    ContactPage contactPage = new ContactPage();


    public String getUsernameFromEmail(String email) {
        String[] parts = email.split("@");
        return StringUtils.left(parts[0], 12);
    }

    Faker faker = new Faker();

    protected String registeredUsername;
    protected String password;
    public void registerNewUser() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));

        String email = faker.internet().emailAddress();
        String registeredUsername = getUsernameFromEmail(email);
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);

        registrationPage.clickOnRegistrationButton();

        blogPage.getUserGreeting().shouldHave(Condition.text(registeredUsername));
    }
    protected String generateRandomTitle() {
        String title = faker.lorem().sentence(5);
        return title.substring(0, Math.min(title.length(), 40));
    }

    protected String generateRandomDescription() {
        String description = faker.lorem().sentence(10);
        return description.substring(0, Math.min(description.length(), 100));
    }

    protected String generateRandomContent() {
        String content = faker.lorem().paragraph(20);
        return content.substring(0, Math.min(content.length(), 1000));
    }
    }

