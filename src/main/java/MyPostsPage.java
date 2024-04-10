import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyPostsPage {

    private SelenideElement firstPostTitle = $(byTagName("h3"));

    private ElementsCollection postTitles = $$(byTagName("h3"));

    private SelenideElement firstPostImage = $(byClassName("post-photo"));



    public SelenideElement getFirstPostImage() {
        return firstPostImage;
    }

    public ElementsCollection getPostTitles() {
        return postTitles;
    }

    public SelenideElement getFirstPostTitle() {
        return firstPostTitle;
    }
}
