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

public class CreditTest {

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
    void tourOnCreditWithAnApprovedCard() { //Сценарий 3. Покупка тура в кредит "ОДОБРЕННЫЙ"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getValidApprovedCard();
        formPage.setValues(cardInfo);
        formPage.checkSuccessNotification();
        var info = SQLHelper.purchaseStatusCredit();
        SQLHelper.statusConfirmation("APPROVED", info);
    }

    @Test
    void tourOnCreditWithAnDeclinedCard() { //Сценарий 4. Покупка тура в кредит "ОТКЛОНЕННЫЙ"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getValidDeclinedCard();
        formPage.setValues(cardInfo);
        formPage.checkErrorNotification();
        var info = SQLHelper.purchaseStatusCredit();
        SQLHelper.statusConfirmation("DECLINED", info);
    }

    @Test
    void tourOnCreditWithTheWrongCard() { //Сценарий 33. В поле "Номер карты" ввести не существующую карту
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCard();
        formPage.setValues(cardInfo);
        formPage.checkErrorNotification();
    }

    @Test
    void tourOnCreditWithTheWrongCardLessThanSixteen() { //Сценарий 34. В поле "Номер карты" ввести меньше 16 цифр
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLessThanSixteen();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCardLetters() { //Сценарий 35. В поле "Номер карты" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCardSpecialCharacters() { //Сценарий 36. В поле "Номер карты" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthDigit() { //Сценарий 37. В поле "Месяц" ввести 1 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDigit();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthZero() { //Сценарий 38. В поле "Месяц" ввести 00 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthWrongDate() { //Сценарий 39. В поле "Месяц" ввести не существующий номер месяца
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthWrongDate();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверно указан срок действия карты ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthOverdueDate() { //Сценарий 40. В поле "Месяц" и "Год" ввести просроченную дату
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthOverdueDate();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверно указан срок действия карты ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthLetters() { //Сценарий 41. В поле "Месяц" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthSpecialCharacters() { //Сценарий 42. В поле "Месяц" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongYearDigit() { //Сценарий 43. В поле "Год" ввести 1 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDigit();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongYearZero() { //Сценарий 44. В поле "Год" ввести значение 00
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Истёк срок действия карты ");
    }

    @Test
    void tourOnCreditWithTheWrongYearPrevious() { //Сценарий 45. В поле "Год" ввести предыдущий год
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearPrevious();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Истёк срок действия карты ");
    }

    @Test
    void tourOnCreditWithTheWrongYearNonExistentTerm() { //Сценарий 46. В поле "Год" ввести + шесть лет от текущего года
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearNonExistentTerm();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверно указан срок действия карты ");
    }

    @Test
    void tourOnCreditWithTheWrongYearLetters() { //Сценарий 47. В поле "Год" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongYearSpecialCharacters() { //Сценарий 48. В поле "Год" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongNameNumbers() { //Сценарий 49. В поле "Владелец" ввести цифры
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameNumbers();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongNameCyrillic() { //Сценарий 50. В поле "Владелец" ввести не латинские буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameCyrillic();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongNameSpecialCharacters() { //Сценарий 51. В поле "Владелец" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCVCLessThanThree() { //Сценарий 52. В поле "CVC/CVV" ввести меньше 3 цифру
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLessThanThree();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCVCZero() { //Сценарий 53. В поле "CVC/CVV" ввести все 000
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCZero();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCVCLetters() { //Сценарий 54. В поле "CVC/CVV" ввести буквы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLetters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCVCSpecialCharacters() { //Сценарий 55. В поле "CVC/CVV" ввести спецсимволы
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCSpecialCharacters();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Неверный формат ");
    }

    @Test
    void tourOnCreditWithTheWrongCardDoNotFillOut() { //Сценарий 56. Не заполнено поле "Номер карты"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Поле обязательно для заполнения ");
    }

    @Test
    void tourOnCreditWithTheWrongMonthDoNotFillOut() { //Сценарий 57. Не заполнено поле "Месяц"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Поле обязательно для заполнения ");
    }

    @Test
    void tourOnCreditWithTheWrongYearDoNotFillOut() { //Сценарий 58. Не заполнено поля "Год"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Поле обязательно для заполнения ");
    }

    @Test
    void tourOnCreditWithTheWrongNameDoNotFillOut() { //Сценарий 59. Не заполнено поля "Владелец"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Поле обязательно для заполнения ");
    }

    @Test
    void tourOnCreditWithTheWrongCVCDoNotFillOut() { //Сценарий 60. Не заполнено поля "CVC/CVV"
        var mainPage = new MainPage();
        var formPage = mainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCDoNotFillOut();
        formPage.setValues(cardInfo);
        formPage.invalidFormat("Поле обязательно для заполнения ");
    }
}
