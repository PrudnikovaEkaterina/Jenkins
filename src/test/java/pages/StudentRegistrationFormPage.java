package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DatePicker;
import pages.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {

    DatePicker datePicker = new DatePicker();

    RegistrationResultsModal resultsModal = new RegistrationResultsModal();

    private final SelenideElement
            FIRSTNAMEINPUT = $("#firstName"),
            LASTNAMEINPUT = $("#lastName"),
            EMAILINPUT = $("#userEmail"),
            PHONEINPUT = $x("//input[@id='userNumber']"),
            DATEOFBIRTHINPUT = $x("//input[@id='dateOfBirthInput']"),
            SUBJECTSAUTOCOMPLETE = $x("//input[@id='subjectsInput']"),
            UPLOADPICTURE = $x("//input[@id='uploadPicture']"),
            CURRENTADDRESS = $x("//textarea[@placeholder='Current Address']"),
            SELECTSTATE = $x("//div[text()='Select State']"),
            SELECTCITY = $x("//div[text()='Select City']"),
            SUBMITBUTTON = $x("//button[@id='submit']");


    public StudentRegistrationFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public StudentRegistrationFormPage removeTheBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public StudentRegistrationFormPage setFirstName(String value) {
        FIRSTNAMEINPUT.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage setLastName(String value) {
        LASTNAMEINPUT.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage setEmail(String value) {
        EMAILINPUT.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage chooseGender(String gender) {
        $x("//label[text()='" + gender + "']").click();
        return this;
    }

    public StudentRegistrationFormPage setPhone(String value) {
        PHONEINPUT.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage setDateOfBirth(String day, String month, String year) {
        DATEOFBIRTHINPUT.click();
        datePicker.setDay(day, month, year);
        return this;
    }

    public StudentRegistrationFormPage chooseSubjects(String subject) {
        SUBJECTSAUTOCOMPLETE.setValue(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        return this;
    }

    public StudentRegistrationFormPage chooseHobbies(String hobby) {
        $x("//label[text()='" + hobby + "']").click();
        return this;
    }

    public StudentRegistrationFormPage uploadPicture(File file) {
        UPLOADPICTURE.uploadFile(file);
        return this;
    }

    public StudentRegistrationFormPage setCurrentAdress(String value) {
        CURRENTADDRESS.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage selectState(String state) {
        SELECTSTATE.click();
        $x("//div[text()='" + state + "']").click();
        return this;
    }

    public StudentRegistrationFormPage selectCity(String city) {
        SELECTCITY.click();
        $x("//div[text()='" + city + "']").click();
        return this;
    }

    public void submitButtonClick() {
        SUBMITBUTTON.click();
    }

    public StudentRegistrationFormPage verifyModalAppears() {
        resultsModal.verifyModalAppears();
        return this;
    }

    public StudentRegistrationFormPage verifyResult(String value, String result) {
        resultsModal.verifyResult(value, result);
        return this;
    }


}
