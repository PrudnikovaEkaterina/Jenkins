package pages.components;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationResultsModal {

    public void verifyModalAppears() {
        $(".modal-content").shouldHave(Condition.appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyResult(String value, String result) {
        $x("//td[text()='" + value + "']//following-sibling::td").shouldHave(text(result));
    }

}
