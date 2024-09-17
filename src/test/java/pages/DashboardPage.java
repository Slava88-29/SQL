package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement main =  $x("//h2[contains(text(),'Личный кабинет')]");

    public DashboardPage() {
        main.shouldBe(visible);
    }
}