import com.codeborne.selenide.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertNotEquals;

public class ProfileEditTest extends BaseTest {

    @Test
    public void noOpeningProfilePageByUnauthorisedUser() {

        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        String profilePageUrl = WebDriverRunner.url();
        closeWebDriver();
        open(profilePageUrl);
        loginPage.getFormTitle().shouldHave(text("Login Form"));

    }

    @Test
    public void successfulNameChangeWithValidName() {

        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("Lilian");
        profilePage.clickOnSaveButton();
        blogPage.clickOnHomeButton();
        blogPage.clickOnMyPostsButton();

        ElementsCollection lilian = blogPage.getPElements().filterBy(text("Lilian"));
        lilian.shouldHave(sizeGreaterThan(0));
    }

    @Test
    public void successfulAvatarEditing() {

        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        String avatarSrcBefore = $("img[data-test='uploaded-image']").getAttribute("src");

        profilePage.uploadImage("C:\\Users\\Arnaud\\Downloads\\free-icon-download-file-7543111.png");
        profilePage.clickOnSaveButton();

        Selenide.sleep(5000);


        String avatarSrcAfter = $("img[data-test='uploaded-image']").getAttribute("src");


        assertNotEquals(avatarSrcBefore, avatarSrcAfter, "Аватар должен был измениться, но ссылка осталась прежней.");

    }

    @Test
    public void noSavingWithEmptyName() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field cannot be empty"));

}

    @Test
    public void noSavingWithNameLessThanThreeSymbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("Br");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));

    }

    @Test
    public void noSavingWithNameContainingNumbers() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("Br12345");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));

    }

    @Test
    public void noSavingWithNameLongerThanTwentySymbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("Brergtdskgturnfcbdhtrssf");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));

    }
    @Test
    public void noSavingWithEmptySurname() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field cannot be empty"));

    }

    @Test
    public void noSavingWithSurnameLessThanThreeSymbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("Br");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));

    }
    @Test
    public void noSavingWithSurnameLongerThanTwentySymbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("Brergtdskgturnfcbdhtrssf");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));

    }
    @Test
    public void noSavingWithSurnameContainingNumbers() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("Br12345");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));

    }

    @Test
    public void noSavingWithSurnameContainingSpecialCharacters() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("Bryyyy@");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));

    }

    @Test
    public void noSavingWithCurrentDateAsBirthdate() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        profilePage.enterBirthDate(currentDateStr);

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));

    }

    @Test
    public void noSavingWithDateInTheFutureAsBirthdate() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterBirthDate("05.11.2055");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));

    }

    @Test
    public void noSavingBirthdateIfAgeGreaterThan100() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterBirthDate("05.11.1900");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));

    }

    @Test
    public void noSavingBirthdateIfAgeUnder13() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        String birthdate = profilePage.generateRandomBirthDateUnder13Years();
        profilePage.enterBirthDate(birthdate);

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));

    }

    @Test
    public void noSavingIfBirthdateIsEmpty() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.enterBirthDate("");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));

    }

    @Test
    public void noEditingIfPhoneNumberIsEmpty() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.enterPhoneNumber("");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));

    }
    @Test
    public void noEditingIfPhoneNumberHasLessThan10Symbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.enterPhoneNumber("123");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));

    }

    @Test
    public void noEditingIfPhoneNumberHasMoreThan20Symbols() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.enterPhoneNumber("123456654322445677775678");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));

    }
    @Test
    public void noEditingIfPhoneNumberHasNumbersAndOneLetter() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.enterPhoneNumber("12345665L8974");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));

    }
    @Test
    public void noEditingIfPhoneNumberIsNotUnique() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        String phone = faker.number().digits(11);

        profilePage.enterPhoneNumber(phone);

        profilePage.clickOnSaveButton();

        profilePage.getPhoneInputField().shouldHave(value("+" + phone));
        closeWebDriver();
        open("http://chatty.telran-edu.de:8089/login");

        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("is not unique"));


    }


    @Test
    public void noEditingIfGenderIsNotSet() {
        registerNewUser();
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();

        profilePage.selectGender("Select");

        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));

    }

}
