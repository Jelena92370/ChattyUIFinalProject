import com.codeborne.selenide.Condition;
import org.junit.Test;

public class PasswordChangeTest extends BaseTest {

    @Test
    public void successfulPasswordChangeWithValidData() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        String newPassword = faker.internet().password(8, 100);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));

}

    @Test
    public void noPasswordChangeWithEmptyOldPassword() {
        registerNewUser();

        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        String newPassword = faker.internet().password(8, 100);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }

    @Test
    public void noPasswordChangeWithInvalidOldPassword() {
        registerNewUser();

        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword("irena888888");
        String newPassword = faker.internet().password(8, 100);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }

    @Test
    public void noPasswordChangeWithEmptyNewPassword() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        String newPassword = faker.internet().password(8, 100);

        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));

    }

    @Test
    public void noPasswordChangeWithEmptyConfirmPassword() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        String newPassword = faker.internet().password(8, 100);

        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));

    }

    @Test
    public void noPasswordChangeIfNewPasswordNotMatchConfirmPassword() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        String newPassword = faker.internet().password(8, 100);

        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword("13jagodamalina");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));

    }
    @Test
    public void noPasswordChangeIfNewPasswordHasNonLatinLetters() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword("ягодамалина14");
        passwordChangeForm.confirmPassword("ягодамалина14");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }

    @Test
    public void noPasswordChangeIfNewPasswordHasLessThanEightSymbols() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword("br1");
        passwordChangeForm.confirmPassword("br1");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }
    @Test
    public void noPasswordChangeIfNewPasswordLongerThanHundredSymbols() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword("ddddddddddddddddddddddddvvfffftgyhjkfdvkneuifhfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        passwordChangeForm.confirmPassword("ddddddddddddddddddddddddvvfffftgyhjkfdvkneuifhfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }
    @Test
    public void noPasswordChangeIfNewPasswordHasNoLetters() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword("12345654321");
        passwordChangeForm.confirmPassword("12345654321");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }
    @Test
    public void noPasswordChangeIfNewPasswordHasNoNumbers() {
        registerNewUser();
        String oldPassword = password;
        blogPage.clickOnUserGreeting();
        blogPage.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword("barracuda");
        passwordChangeForm.confirmPassword("barracuda");
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));

    }
}
