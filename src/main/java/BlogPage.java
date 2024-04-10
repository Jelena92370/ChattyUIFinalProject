import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BlogPage {

    private SelenideElement userGreeting = $(By.className("header__user"));
    private SelenideElement myPostsButton = $(By.cssSelector("label[for='myPostsId']"));
   private SelenideElement createPostButton = $(byAttribute("data-test", "post-header__plus"));

    private SelenideElement contactFormLink = $(By.xpath("//a[contains(@href, 'contact')]"));
    private ElementsCollection pElements = $$(By.tagName("p"));

    public ElementsCollection getPElements() {
        return pElements;
    }

    private SelenideElement firstPostTitle = $(byTagName("h3"));
    private ElementsCollection postTitles = $$(byTagName("h3"));
    private SelenideElement headerDropdownMenu = $(By.className("dropdown-menu"));
    private SelenideElement homeButton = $(By.xpath("//a[contains(@href, 'homeblog')]"));

    public SelenideElement getHeaderDropdownMenu() {
        return headerDropdownMenu;
    }

    public void selectYourProfileOption() {

        headerDropdownMenu.$(By.xpath(".//a[contains(text(), 'Your Profile')]")).click();


    }

    //dropdown-menu;

    public ElementsCollection getPostTitles() {
        return postTitles;
    }

    private SelenideElement myDraftsButton = $(byAttribute("alt", "Drafts"));



    public SelenideElement getFirstPostTitle() {
        return firstPostTitle;
    }
//

    public SelenideElement getUserGreeting() {
        return userGreeting;
    }

    public void clickOnCreatePostButton() {
        createPostButton.click();

    }

    public void clickOnUserGreeting() {
        userGreeting.click();

    }

    public void clickOnContactLink() {
        contactFormLink.click();

    }

    public void clickOnMyPostsButton() {
        myPostsButton.click();

    }

    public void clickOnMyDraftsButton() {
        myDraftsButton.click();

    }

    public void clickOnHomeButton() {
        homeButton.click();
    }
}