package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static SelenideElement buyButton = $(".button__content");
    private static SelenideElement creditButton = $(".button_view_extra");

    public FormPage clickBuyButton() {
        buyButton.click();
        return new FormPage();
    }
    public FormPage clickCreditButton() {
        creditButton.click();
        return new FormPage();
    }
}
