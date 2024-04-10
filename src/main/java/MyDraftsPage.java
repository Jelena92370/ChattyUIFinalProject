import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;

public class MyDraftsPage {

    private SelenideElement firstPostTitle = $(byTagName("h3"));

    public SelenideElement getFirstPostTitle() {
        return firstPostTitle;
    }
}
