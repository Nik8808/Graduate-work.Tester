package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static SelenideElement buyButton = $(".button__content");
    private static SelenideElement breditButton = $(".button_view_extra");

    public static FormPage clickBuyButton() {
        buyButton.click();
        return new FormPage();
    }
    public static FormPage clickCreditButton() {
        breditButton.click();
        return new FormPage();
    }
}
