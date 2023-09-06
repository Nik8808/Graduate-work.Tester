package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

public class PurchaseTest {
    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void buyingATourWithAnApprovedCard() { //Сценарий 1. Покупка тура картой "ОДОБРЕННЫЙ"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getValidApprovedCard();
        formPage.setValues(cardInfo);
        formPage.checkSuccessNotification();
        var info = SQLHelper.purchaseStatusCard();
        SQLHelper.statusConfirmation("APPROVED", info);
    }

    @Test
    void buyingATourWithAnDeclinedCard() { //Сценарий 2. Покупка тура картой "ОТКЛОНЕННЫЙ"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getValidDeclinedCard();
        formPage.setValues(cardInfo);
        formPage.checkErrorNotification();
        var info = SQLHelper.purchaseStatusCard();
        SQLHelper.statusConfirmation("DECLINED", info);
    }

    @Test
    void buyingATourWithTheWrongCard() { //Сценарий 5. В поле "Номер карты" ввести не существующую карту.
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCard();
        formPage.setValues(cardInfo);
        formPage.checkErrorNotification();
    }

    @Test
    void buyingATourWithTheWrongCardLessThanSixteen() { //Сценарий 6. В поле "Номер карты" ввести меньше 16 цифр
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLessThanSixteen();
        formPage.setValues(cardInfo);
        formPage.invalidFormatNumber("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCardLetters() { //Сценарий 7. В поле "Номер карты" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatNumber("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCardSpecialCharacters() { //Сценарий 8. В поле "Номер карты" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatNumber("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongMonthDigit() { //Сценарий 9. В поле "Месяц" ввести 1 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDigit();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongMonthZero() { //Сценарий 10. В поле "Месяц" ввести 00
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongMonthWrongDate() { //Сценарий 11. В поле "Месяц" ввести не существующий номер месяца
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthWrongDate();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверно указан срок действия карты ");
    }

    @Test
    void buyingATourWithTheWrongMonthOverdueDate() { //Сценарий 12. В поле "Месяц" и "Год" ввести просроченную дату
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthOverdueDate();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверно указан срок действия карты ");
    }

    @Test
    void buyingATourWithTheWrongMonthLetters() { //Сценарий 13. В поле "Месяц" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongMonthSpecialCharacters() { //Сценарий 14. В поле "Месяц" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongYearDigit() { //Сценарий 15. В поле "Год" ввести 1 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDigit();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongYearZero() { //Сценарий 16. В поле "Год" ввести значение 00
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Истёк срок действия карты ");
    }

    @Test
    void buyingATourWithTheWrongYearPrevious() { //Сценарий 17. В поле "Год" ввести предыдущий год
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearPrevious();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Истёк срок действия карты ");
    }

    @Test
    void buyingATourWithTheWrongYearNonExistentTerm() { //Сценарий 18. В поле "Год" ввести + шесть лет от текущего года
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearNonExistentTerm();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Неверно указан срок действия карты ");
    }

    @Test
    void buyingATourWithTheWrongYearLetters() { //Сценарий 19. В поле "Год" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongYearSpecialCharacters() { //Сценарий 20. В поле "Год" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongNameNumbers() { //Сценарий 21. В поле "Владелец" ввести цифры
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameNumbers();
        formPage.setValues(cardInfo);
        formPage.invalidFormatName("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongNameCyrillic() { //Сценарий 22. В поле "Владелец" ввести не латинские буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameCyrillic();
        formPage.setValues(cardInfo);
        formPage.invalidFormatName("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongNameSpecialCharacters() { //Сценарий 23. В поле "Владелец" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatName("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCVCLessThanThree() { //Сценарий 24. В поле "CVC/CVV" ввести меньше 3 цифр
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLessThanThree();
        formPage.setValues(cardInfo);
        formPage.invalidFormatCvc("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCVCZero() { //Сценарий 25. В поле "CVC/CVV" ввести все 000
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormatCvc("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCVCLetters() { //Сценарий 26. В поле "CVC/CVV" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatCvc("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCVCSpecialCharacters() { //Сценарий 27. В поле "CVC/CVV" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormatCvc("Неверный формат ");
    }

    @Test
    void buyingATourWithTheWrongCardDoNotFillOut() { //Сценарий 28. Не заполнено поле "Номер карты"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormatNumber("Поле обязательно для заполнения ");
    }

    @Test
    void buyingATourWithTheWrongMonthDoNotFillOut() { //Сценарий 29. Не заполнено поле "Месяц"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormatMonth("Поле обязательно для заполнения ");
    }

    @Test
    void buyingATourWithTheWrongYearDoNotFillOut() { //Сценарий 30. Не заполнено поля "Год"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormatYear("Поле обязательно для заполнения ");
    }

    @Test
    void buyingATourWithTheWrongNameDoNotFillOut() { //Сценарий 31. Не заполнено поля "Владелец"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormatName("Поле обязательно для заполнения ");
    }

    @Test
    void buyingATourWithTheWrongCVCDoNotFillOut() { //Сценарий 32. Не заполнено поля "CVC/CVV"
        var mainPage = new MainPage();
        var formPage = mainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormatCvc("Поле обязательно для заполнения ");
    }
}
