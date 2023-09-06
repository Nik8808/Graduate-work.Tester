package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FormPage {
    private static SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private static SelenideElement monthField = $("[placeholder='08']");
    private static SelenideElement yearField = $("[placeholder='22']");
    private static SelenideElement nameField = $("input:not([placeholder])");
    private static SelenideElement cvcField = $("[placeholder='999']");
    private static SelenideElement continueButton = $("form button");
    private static SelenideElement statusSuccess = $(".notification_status_ok");
    private SelenideElement statusError = $(".notification_status_error");
    //private SelenideElement paddingErrorEnd = $(".input__sub");

    private SelenideElement paddingError(SelenideElement element) {
        return element.closest(".input__inner").find(".input__sub");
    }


    public void setValues(DataHelper.CardInfo info) {
        numberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(String.valueOf(info.getYear()));
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        continueButton.click();

    }

    public void checkErrorNotification() {
        statusError.shouldHave(text("Ошибка! Банк отказал в проведении операции. "), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        statusSuccess.shouldBe(disappear);
    }

    public void checkSuccessNotification() {
        statusSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        statusError.shouldBe(disappear);
    }

    public void invalidFormatNumber(String status) {
        paddingError(numberField).shouldHave(text(status), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        paddingError(monthField).should(disappear);
        paddingError(yearField).should(disappear);
        paddingError(nameField).should(disappear);
        paddingError(cvcField).should(disappear);
    }

    public void invalidFormatMonth(String status) {
        paddingError(monthField).shouldHave(text(status), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        paddingError(numberField).should(disappear);
        paddingError(yearField).should(disappear);
        paddingError(nameField).should(disappear);
        paddingError(cvcField).should(disappear);
    }

    public void invalidFormatYear(String status) {
        paddingError(yearField).shouldHave(text(status), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        paddingError(monthField).should(disappear);
        paddingError(numberField).should(disappear);
        paddingError(nameField).should(disappear);
        paddingError(cvcField).should(disappear);
    }

    public void invalidFormatName(String status) {
        paddingError(nameField).shouldHave(text(status), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        paddingError(monthField).should(disappear);
        paddingError(yearField).should(disappear);
        paddingError(numberField).should(disappear);
        paddingError(cvcField).should(disappear);
    }

    public void invalidFormatCvc(String status) {
        paddingError(cvcField).shouldHave(text(status), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        paddingError(monthField).should(disappear);
        paddingError(yearField).should(disappear);
        paddingError(nameField).should(disappear);
        paddingError(numberField).should(disappear);
    }
}
