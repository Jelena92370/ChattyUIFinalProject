import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class PasswordChangeForm {
    private SelenideElement formTitle = $(byClassName("PasswordModal_passParagraph__feEN9"));
    private SelenideElement oldPasswordField = $(byCssSelector("input[placeholder='Old password']"));
    private SelenideElement newPasswordField = $(byCssSelector("input[placeholder='New password']"));
    private SelenideElement confirmPasswordField = $(byCssSelector("input[placeholder='Confirm new password']"));

    private SelenideElement saveButton = $(byClassName("PasswordModal_pass_btn__eGL9h"));
    private SelenideElement errorMessage = $(byClassName("PasswordModal_error__9a5OG"));

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

        public SelenideElement getFormTitle() {
        return formTitle;
    }

    public void enterOldPassword(String oldPasswordValue){
        oldPasswordField.setValue(oldPasswordValue);
    }

    public void enterNewPassword(String newPasswordValue){
        newPasswordField.setValue(newPasswordValue);
    }
    public void confirmPassword(String confirmPasswordValue){
        confirmPasswordField.setValue(confirmPasswordValue);
    }

    public void clickOnSaveButton() {
        saveButton.click();

    }
}
