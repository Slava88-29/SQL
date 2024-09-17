package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.User;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement form = $(".form");
    private final SelenideElement login = form.$("[name=login]");
    private final SelenideElement password = form.$("[name=password]");
    private final SelenideElement button = $(".button");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public LoginPage() {
       open("http://localhost:9999");
    }

    public LoginPage loginIn(User user) {
        login.should(Condition.visible).setValue(user.getLogin());
        password.should(Condition.visible).setValue(user.getPassword());
        button.should(Condition.visible).click();
        return this;
    }

    public LoginPage clear() {
        login.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        password.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        return this;
    }

    public LoginPage checkErrorNotification() {
        errorNotification.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage checkDisable() {
        button.shouldBe(Condition.disabled);
        login.shouldBe(Condition.disabled);
        password.shouldBe(Condition.disabled);
        return this;
    }
}

