import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;

public class LoginTest extends BaseTest {

    @Test

    public void successfulLogin() {


        String email = "pince5@gmail.com";
        String username = getUsernameFromEmail(email);

        loginPage.enterEmail(email);
        loginPage.enterPassword("Lena13456");
        loginPage.clickOnLoginButton();
        blogPage.getUserGreeting().shouldHave(Condition.text(username));


}

    @Test

    public void noLoginIfEmailHasNoAtSign() {
        loginPage.enterEmail("pince5gmail.com");
        loginPage.enterPassword("Lena13456");
        loginPage.getLoginButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noLoginWithInvalidPassword() {
        loginPage.enterEmail("pince5@gmail.com");
        loginPage.enterPassword("Laaa13456");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Invalid email or password. Please try again"));


    }

    @Test

    public void noLoginWithEmptyEmail() {

        loginPage.enterPassword("Lena13456");
        loginPage.getLoginButton().shouldHave(attribute("disabled"));
        loginPage.getErrorMessage().shouldHave(text("Email can not be empty"));

    }

    @Test

    public void noLoginWithEmptyPassword() {

        loginPage.enterEmail("pince5@gmail.com");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Password can not be empty"));

    }

    @Test

    public void noLoginWithNotExistingUser() {
        loginPage.enterEmail("Lola15@gmail.com");
        loginPage.enterPassword("Laaa13456");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("User not found. Please register."));


    }

    @Test

    public void noLoginWithEmptyCredentials() {


        loginPage.getLoginButton().shouldHave(attribute("disabled"));
}
}
