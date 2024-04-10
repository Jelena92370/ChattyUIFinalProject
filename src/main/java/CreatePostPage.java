import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreatePostPage {


    private SelenideElement titleField = $(byAttribute("data-test", "title-input"));
    private SelenideElement descriptionField = $(byAttribute("data-test", "description-input"));

    private SelenideElement contentField = $(byAttribute("data-test", "textarea"));

    private SelenideElement imageUploadField = $(By.cssSelector("input[type='file']"));

    private SelenideElement submitButton = $(byAttribute("data-test", "submit"));

    private SelenideElement errorMessage = $(byClassName("error"));
    private ElementsCollection errorMessages = $$(byClassName("error"));

    private SelenideElement saveAsDraftButton = $(By.cssSelector("label[for='draftCheckbox']"));
    private SelenideElement imageError = $(byClassName("post_error_message__FQTrb"));

    private SelenideElement publishDate = $(byId("publishDate"));

    //


    public SelenideElement getImageError() {
        return imageError;
    }

    public ElementsCollection getErrorMessages() {
        return errorMessages;
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    public SelenideElement getTitleField() {
        return titleField;
    }

    public SelenideElement getDescriptionField() {
        return descriptionField;
    }

    public SelenideElement getContentField() {
        return contentField;
    }

    public void enterTitle(String title) {
        titleField.setValue(title);
    }

    public void enterPublishDate (String date) {
        publishDate.setValue(date);
    }

    public void enterDescription(String description) {
        descriptionField.setValue(description);
    }

    public void enterContent(String content) {
        contentField.setValue(content);
    }

    public void uploadImage(String imagePath) {
        imageUploadField.uploadFile(new File(imagePath));
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickDraftButton() {
        saveAsDraftButton.click();
    }
}
