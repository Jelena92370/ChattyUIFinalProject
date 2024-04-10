import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ContactPage {

    private SelenideElement formTitle = $(byTagName("h1"));
    private SelenideElement nameField = $(byId("name"));
    private SelenideElement emailField = $(byId("email"));
    private SelenideElement contentField = $(byId("content"));
    private SelenideElement sendMessageButton = $("[type='submit']");

    private SelenideElement successMessage = $(byClassName("success-message"));
    private SelenideElement errorMessage = $(byClassName("error"));


    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }

    public SelenideElement getFormTitle() {
        return formTitle;
    }

    public void enterName(String name) {
        nameField.setValue(name);
    }
    public void enterEmail(String email) {
        emailField.setValue(email);
    }

    public void enterMessage(String message) {
        contentField.setValue(message);
    }

    public void clickSendMessageButton() {
        sendMessageButton.click();
    }

    //success-message
}
