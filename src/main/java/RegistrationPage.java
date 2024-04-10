import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private SelenideElement emailInputField = $(byAttribute("data-test", "email"));

    private SelenideElement passwordInputField = $(byAttribute("data-test", "password"));

    //
    private SelenideElement confirmPasswordInputField = $(byAttribute("data-test", "confirmPassword"));

private SelenideElement usernameInputField = $(byAttribute("data-test", "username"));
    private SelenideElement nameInputField = $(byAttribute("data-test", "profileName"));

    private SelenideElement surnameInputField = $(byAttribute("data-test", "profileSurname"));

    private SelenideElement birthDateInputField = $(byId("birthDate"));
    private SelenideElement genderOption = $(byId("gender"));
    private SelenideElement phoneInputField = $("input[name='phone']");







    public SelenideElement getEmailInputField() {
        return emailInputField;
    }

    public SelenideElement getPasswordInputField() {
        return passwordInputField;
    }

    public SelenideElement getConfirmPasswordInputField() {
        return confirmPasswordInputField;
    }

    public SelenideElement getUsernameInputField() {
        return usernameInputField;
    }

    public SelenideElement getNameInputField() {
        return nameInputField;
    }

    public SelenideElement getSurnameInputField() {
        return surnameInputField;
    }

    public SelenideElement getBirthDateInputField() {
        return birthDateInputField;
    }

    public SelenideElement getGenderOption() {
        return genderOption;
    }

    public SelenideElement getPhoneInputField() {
        return phoneInputField;
    }

    private SelenideElement registrationButton = $(byClassName("registration-btn"));
    private SelenideElement formTitle = $(byTagName("h1"));

    public SelenideElement getFormTitle() {
        return formTitle;
    }

    private SelenideElement errorMessage = $(byClassName("text-error"));

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    public SelenideElement getRegistrationButton() {
        return registrationButton;
    }

    public void enterEmail(String usernameValue){
        emailInputField.shouldBe(visible, Duration.ofSeconds(5));
        emailInputField.setValue(usernameValue);
    }

    public void enterPassword(String passwordValue){
        passwordInputField.setValue(passwordValue);
    }

    public void enterConfirmPassword(String confirmPasswordValue){
        confirmPasswordInputField.setValue(confirmPasswordValue);
    }
    public void clickOnRegistrationButton() {
        registrationButton.click();

    }

    public void enterUsername(String usernameValue) {
        usernameInputField.setValue(usernameValue);
    }

    public void enterName(String nameValue) {
        nameInputField.setValue(nameValue);
    }

    public void enterSurname(String surnameValue) {
        surnameInputField.setValue(surnameValue);
    }

    public String generateRandomBirthDate() {
        Random random = new Random();

        LocalDate minDate = LocalDate.now().minusYears(100);
        LocalDate maxDate = LocalDate.now().minusYears(13);


        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();


        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));


        return LocalDate.ofEpochDay(randomDay).toString();
    }

    public void selectGender(String genderValue) {
        genderOption.selectOptionContainingText(genderValue);
    }

    public void enterPhone(String phoneValue) {
        phoneInputField.setValue(phoneValue);
    }

    public void enterBirthDate(String birthDateValue) {
        birthDateInputField.setValue(birthDateValue);
    }
    //
}
