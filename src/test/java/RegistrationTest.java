import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;

public class RegistrationTest extends BaseTest {
    Faker faker = new Faker();
    @Test

    //These tests correspond to actual implementation of registration function, only with few fields

    public void successfulRegistration() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));

        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);

        registrationPage.clickOnRegistrationButton();

        String username = getUsernameFromEmail(email);
        blogPage.getUserGreeting().shouldHave(Condition.text(username));

    }

    @Test

    public void noRegistrationWithoutEmail() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));

                String password = faker.internet().password(8, 100);

        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Email cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithoutPassword() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("");
        String password = faker.internet().password(8, 100);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Password cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithEmailWithoutAt() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.enterEmail("pince5gmail.com");
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Incorrect email format"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithAlreadyExistingEmail() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.enterEmail("pince5@gmail.com");
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.clickOnRegistrationButton();
        registrationPage.getErrorMessage().shouldHave(text("Email already exists!"));

    }
    @Test

    public void noRegistrationWithPasswordContainingNonLatinLetters() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("Лена1234RR");

        registrationPage.enterConfirmPassword("Лена1234RR");
        registrationPage.getErrorMessage().shouldHave(text("The correct password can only consist of Latin letters and numbers"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithPasswordLongerThanHundredSymbols() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("ddddddddddddddddddddddddvvfffftgyhjkfdvkneuifhfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");

        registrationPage.enterConfirmPassword("ddddddddddddddddddddddddvvfffftgyhjkfdvkneuifhfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithPasswordContainingLessThanEightSymbols() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("dddd123");

        registrationPage.enterConfirmPassword("dddd123");
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithPasswordWithoutAnyLetters() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("123777789");

        registrationPage.enterConfirmPassword("123777789");
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationWithPasswordWithoutAnyNumbers() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("dododotyrue");

        registrationPage.enterConfirmPassword("dododotyrue");
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationIfConfirmPasswordIsEmpty() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);

        registrationPage.getErrorMessage().shouldHave(text("Confirm password cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }

    @Test

    public void noRegistrationIfConfirmPasswordIsDifferentFromPassword() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword("dodod1111");
        registrationPage.getErrorMessage().shouldHave(text("Passwords do not match"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));

    }
//

//These tests are failing right now because many fields are missing
// in the current implementation of this feature; we should run these tests after correction
    @Test

    public void allFieldsForRegistrationAreVisible() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.getEmailInputField().shouldBe(visible);
        registrationPage.getNameInputField().shouldBe(visible);
        registrationPage.getUsernameInputField().shouldBe(visible);
        registrationPage.getSurnameInputField().shouldBe(visible);
        registrationPage.getPasswordInputField().shouldBe(visible);
        registrationPage.getConfirmPasswordInputField().shouldBe(visible);
        registrationPage.getBirthDateInputField().shouldBe(visible);
        registrationPage.getPhoneInputField().shouldBe(visible);
        registrationPage.getGenderOption().shouldBe(visible);

    }

    @Test



    public void successfulRegistrationWithAllFields() {

        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));

        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);

        String username = faker.name().username();
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String phone = faker.number().digits(11);
        registrationPage.enterUsername(username);
        registrationPage.enterName(name);
        registrationPage.enterSurname(surname);
        String birthDate = registrationPage.generateRandomBirthDate();
        registrationPage.enterBirthDate(birthDate);
        registrationPage.selectGender("Male");
        registrationPage.enterPhone(phone);

        registrationPage.clickOnRegistrationButton();

        String usernameFromEmail = getUsernameFromEmail(email);
        blogPage.getUserGreeting().shouldHave(Condition.text(usernameFromEmail));

    }

}
