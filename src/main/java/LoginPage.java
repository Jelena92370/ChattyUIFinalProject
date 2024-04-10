import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {


    private SelenideElement emailInputField = $("[placeholder=\"Email\"]");
    private SelenideElement passwordInputField = $(byClassName("input-password"));

    private SelenideElement loginButton = $(byClassName("registration-btn"));

    private SelenideElement registrationLink = $("[href='/registration']");

    private SelenideElement formTitle = $(byTagName("h1"));
    private SelenideElement errorMessage = $(byClassName("text-error"));

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }

    //text-error

    public SelenideElement getFormTitle() {
        return formTitle;
    }


    public void enterEmail(String usernameValue){
        emailInputField.shouldBe(visible, Duration.ofSeconds(5));
        emailInputField.setValue(usernameValue);
    }

    public void enterPassword(String passwordValue){
        passwordInputField.setValue(passwordValue);
    }

    public void clickOnLoginButton() {
        loginButton.click();

    }



    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public void clickOnRegistrationLink() {
        registrationLink.click();

    }
}
