import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import org.junit.Test;


import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class CreatePostTest extends BaseTest {

    @Test
    public void successfulPostCreation() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.uploadImage("C:\\Users\\Arnaud\\Downloads\\Tree.jpg");
        createPostPage.clickSubmitButton();
                blogPage.clickOnMyPostsButton();


        myPostsPage.getFirstPostTitle().shouldHave(text(title));
    }

    @Test
    public void noEmptyPostCreation() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        createPostPage.clickSubmitButton();
        createPostPage.getTitleField().shouldBe(Condition.visible);
        createPostPage.getDescriptionField().shouldBe(Condition.visible);
        createPostPage.getContentField().shouldBe(Condition.visible);
        createPostPage.getErrorMessage().shouldHave(text("Please fill the field"));

    }

    @Test
    public void onlyOneErrorMessageIfPostIsEmpty() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();
        createPostPage.clickSubmitButton();
        createPostPage.getErrorMessages().shouldHave(CollectionCondition.size(1));

    }

    @Test
    public void noPostCreationWithTitleLongerThan40Symbols() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle("ffffffffffffffffffffffffffffffffffffffffffffffff");
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.clickSubmitButton();
        createPostPage.getContentField().shouldBe(Condition.visible);
        createPostPage.getErrorMessage().shouldHave(text("40 symbols max"));

    }

    @Test
    public void noPostCreationWithDescriptionLongerThan100Symbols() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();
        String title = generateRandomTitle();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription("fffffffffffffffffffffffffffffffffffffffffffffffffgrdhhhhhhhfffffffbheyrttrfgettttttzyefeeeeeeeeeeeeeeeeeerffff");
        createPostPage.enterContent(content);
        createPostPage.clickSubmitButton();
        createPostPage.getContentField().shouldBe(Condition.visible);
        createPostPage.getErrorMessage().shouldHave(text("100 symbols max"));

    }

    @Test
    public void noPostWithImageInWrongFormat() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.uploadImage("C:\\Users\\Arnaud\\Downloads\\PPS 24-162.pdf");
        createPostPage.getImageError().shouldHave(text("Only jpg, jpeg, png"));
        createPostPage.clickSubmitButton();
        createPostPage.getContentField().shouldBe(Condition.visible);

    }

    @Test
    public void noPostWithImageHeavierThan2MB() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.uploadImage("C:\\Users\\Arnaud\\Downloads\\Untitled design.png");
        createPostPage.getImageError().shouldHave(text("File size exceeds the 2MB limit"));
        createPostPage.clickSubmitButton();
        createPostPage.getContentField().shouldBe(Condition.visible);

    }

    @Test
    public void successfulPlaceholderIfNoUserImage() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.clickSubmitButton();

        blogPage.clickOnMyPostsButton();
        myPostsPage.getFirstPostTitle().shouldHave(text(title));
        myPostsPage.getFirstPostImage().shouldBe(visible);
    }

    @Test
    public void successfulSavingPostAsDraft() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.clickDraftButton();
        createPostPage.clickSubmitButton();

        blogPage.clickOnMyDraftsButton();
        myDraftsPage.getFirstPostTitle().shouldHave(text(title));
    }

    @Test
    public void postAsDraftIsNotSavedInPostList() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.clickDraftButton();
        createPostPage.clickSubmitButton();

        blogPage.clickOnMyPostsButton();
        ElementsCollection filteredTitles = myPostsPage.getPostTitles().filterBy(text(title));

        filteredTitles.shouldHave(size(0));

}

    @Test
    public void noPostIfDelayWithDateInThePast() {

        registerNewUser();
        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);
        createPostPage.enterPublishDate("04-05-2023");
        createPostPage.getErrorMessage().shouldHave(text("Date can’t be earlier then today"));
        createPostPage.clickSubmitButton();

        blogPage.clickOnMyPostsButton();
        ElementsCollection filteredTitles = myPostsPage.getPostTitles().filterBy(text(title));

        filteredTitles.shouldHave(size(0));

    }


    @Test
    public void noEminentPostPublishingWithDelayInTheFuture() {

        registerNewUser();

        blogPage.clickOnCreatePostButton();

        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPostPage.enterTitle(title);
        createPostPage.enterDescription(description);
        createPostPage.enterContent(content);

        createPostPage.enterPublishDate("04-05-2025");
        createPostPage.clickSubmitButton();

        blogPage.clickOnMyPostsButton();



        ElementsCollection filteredTitles = myPostsPage.getPostTitles().filterBy(text(title));


        filteredTitles.shouldHave(size(0));




    }
}

