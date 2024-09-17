package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private final SelenideElement verify = $("[data-test-id=code] input");
    private final SelenideElement button = $(".button");

    public void verifyIn(String codeValue) {
        verify.should(Condition.visible).setValue(codeValue);
        button.should(Condition.visible).click();
    }

}

