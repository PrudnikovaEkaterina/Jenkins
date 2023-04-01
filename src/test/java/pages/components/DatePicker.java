package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DatePicker {
    private final SelenideElement
            MONTH_SELECT = $x("//select[@class='react-datepicker__month-select']"),
            YEAR_SELECT = $x("//select[@class='react-datepicker__year-select']");

    public void setDay(String day, String month, String year) {
        MONTH_SELECT.selectOption(month);
        YEAR_SELECT.selectOption(year);
        $x("//div[@class='react-datepicker__month-container']//descendant::div[text()=" + day + "]").click();
    }


}
