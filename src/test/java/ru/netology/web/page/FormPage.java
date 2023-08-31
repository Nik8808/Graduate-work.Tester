package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
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
    private SelenideElement paddingError = $(".input__sub");


    public void setValues(DataHelper.CardInfo info) {
        numberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(String.valueOf(info.getYear()));
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        continueButton.click();
    }

    public void checkErrorNotification() {
        statusError.shouldBe(visible, Duration.ofSeconds(20));
        statusSuccess.should(disappear);
    }

    public void checkSuccessNotification() {
        statusSuccess.shouldBe(visible, Duration.ofSeconds(20));
        statusError.should(disappear);
    }

    public void invalidFormat() {
        paddingError.shouldHave(Condition.text("Неверный формат "), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }
    public void invalidCardExpirationDate() {
        paddingError.shouldHave(Condition.text("Неверно указан срок действия карты "), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }
    public void cardExpired() {
        paddingError.shouldHave(Condition.text("Истёк срок действия карты "), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }
    public void requiredToFill() {
        paddingError.shouldHave(Condition.text("Поле обязательно для заполнения "), Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }
}
