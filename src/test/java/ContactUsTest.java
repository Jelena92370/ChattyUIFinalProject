import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;

public class ContactUsTest extends BaseTest{

    @Test
    public void successfulContactWithValidData() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        String message = generateRandomContent();
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldHave(Condition.text("Feedback submitted successfully!"));

    }

    @Test
    public void NoSuccessWithEmptyName() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        String message = generateRandomContent();
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }
    @Test
    public void NoSuccessWithEmptyEmail() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        String message = generateRandomContent();
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void NoSuccessWithEmptyContest() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void NoSuccessWithNameContainingLessThanTwoSymbols() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("A");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        String message = generateRandomContent();
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void NoSuccessWithNameLongerThanThirtySymbols() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Atyrhfndbsbejtrldcbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        String message = generateRandomContent();
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void NoSuccessWithEmailWithoutAt() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        contactPage.enterEmail("arina.new.hotmail.com");
        String message = generateRandomContent();
        contactPage.enterMessage(message);

        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        contactPage.getErrorMessage().shouldHave(text("Invalid email format"));
    }

    @Test
    public void NoSuccessWithContentLessThanTwoSymbols() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        contactPage.enterMessage("a");
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);

    }

    @Test
    public void NoSuccessWithContentLongerThan1000Symbols() {
        registerNewUser();
        blogPage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName("Arina New");
        String email = faker.internet().emailAddress();
        contactPage.enterEmail(email);
        contactPage.enterMessage("cbdhueeyrrrrrvbfhhrjtyyyyyNCCCCCCCCCCCCCCCCCCDHHHHHHHHEERYYYYYYYYTEFFFFFFDGGGddbbbssssssssssssssssssssssssssssssssssssrrteyfbcndheyrybdddddddddddddddbbbbbbbbbbbbsssssssssssssssssssssscndjryfnchddddddddddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhherrrrrrrrennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnccbdbrhdbcgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr cbdhueeyrrrrrvbfhhrjtyyyyyNCCCCCCCCCCCCCCCCCCDHHHHHHHHEERYYYYYYYYTEFFFFFFDGGGddbbbssssssssssssssssssssssssssssssssssssrrteyfbcndheyrybdddddddddddddddbbbbbbbbbbbbsssssssssssssssssssssscndjryfnchddddddddddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhherrrrrrrrennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnccbdbrhdbcgrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr\n" +
                "cbdhueeyrrrrrvbfhhrjtyyyyyNCCCCCCCCCCCCCCCCCCDHHHHHHHHEERYYYYYYYYTEFFFFFFDghfnnnnnnnnnnnnnnnnnnnnnnnnnnvvvvvvvvvvvvvvvvvvvvvvvv\n");
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);

    }
}
