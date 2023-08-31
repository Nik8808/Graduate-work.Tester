package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.MainPage;
import ru.netology.web.util.LoggingUtils;
import ru.netology.web.util.ScreenShooterReportPortalExtension;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

//public class CreditTest {
    @ExtendWith({ScreenShooterReportPortalExtension.class})
    class CreditTest {
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
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getValidApprovedCard();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.checkSuccessNotification();
        var Info = SQLHelper.purchaseStatusCredit();
        SQLHelper.statusConfirmation("APPROVED", Info);
    }

    @Test
    void tourOnCreditWithAnDeclinedCard() { //Сценарий 4. Покупка тура в кредит "ОТКЛОНЕННЫЙ"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getValidDeclinedCard();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.checkErrorNotification();
        var Info = SQLHelper.purchaseStatusCredit();
        SQLHelper.statusConfirmation("DECLINED", Info);
    }

    @Test
    void tourOnCreditWithTheWrongCard() { //Сценарий 33. В поле "Номер карты" ввести не существующую карту
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCard();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.checkErrorNotification();
    }

    @Test
    void tourOnCreditWithTheWrongCardLessThanSixteen() { //Сценарий 34. В поле "Номер карты" ввести меньше 16 цифр
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLessThanSixteen();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }

    @Test
    void tourOnCreditWithTheWrongCardLetters() { //Сценарий 35. В поле "Номер карты" ввести буквы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLetters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCardSpecialCharacters() { //Сценарий 36. В поле "Номер карты" ввести спецсимволы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardSpecialCharacters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongMonthDigit() { //Сценарий 37. В поле "Месяц" ввести 1 цифру
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDigit();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongMonthZero() { //Сценарий 38. В поле "Месяц" ввести 00 цифру
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthZero();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongMonthWrongDate() { //Сценарий 39. В поле "Месяц" ввести не существующий номер месяца
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthWrongDate();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidCardExpirationDate();
    }
    @Test
    void tourOnCreditWithTheWrongMonthOverdueDate() { //Сценарий 40. В поле "Месяц" и "Год" ввести просроченную дату
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthOverdueDate();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidCardExpirationDate();
    }
    @Test
    void tourOnCreditWithTheWrongMonthLetters() { //Сценарий 41. В поле "Месяц" ввести буквы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthLetters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongMonthSpecialCharacters() { //Сценарий 42. В поле "Месяц" ввести спецсимволы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthSpecialCharacters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongYearDigit() { //Сценарий 43. В поле "Год" ввести 1 цифру
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDigit();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongYearZero() { //Сценарий 44. В поле "Год" ввести значение 00
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearZero();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.cardExpired();
    }
    @Test
    void tourOnCreditWithTheWrongYearPrevious() { //Сценарий 45.В поле "Год" ввести предыдущий год
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearPrevious();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.cardExpired();
    }
    @Test
    void tourOnCreditWithTheWrongYearNonExistentTerm() { //Сценарий 46. В поле "Год" ввести + шесть лет от текущего года
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearNonExistentTerm();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidCardExpirationDate();
    }
    @Test
    void tourOnCreditWithTheWrongYearLetters() { //Сценарий 47. В поле "Год" ввести буквы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearLetters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongYearSpecialCharacters() { //Сценарий 48. В поле "Год" ввести спецсимволы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearSpecialCharacters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongNameNumbers() { //Сценарий 49. В поле "Владелец" ввести цифры
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameNumbers();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongNameCyrillic() { //Сценарий 50. В поле "Владелец" ввести не латинские буквы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameCyrillic();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongNameSpecialCharacters() { //Сценарий 51. В поле "Владелец" ввести спецсимволы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameSpecialCharacters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCVCLessThanThree() { //Сценарий 52. В поле "CVC/CVV" ввести меньше 3 цифру
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLessThanThree();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCVCZero() { //Сценарий 53. В поле "CVC/CVV" ввести все 000
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCZero();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCVCLetters() { //Сценарий 54. В поле "CVC/CVV" ввести буквы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLetters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCVCSpecialCharacters() { //Сценарий 55. В поле "CVC/CVV" ввести спецсимволы
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCSpecialCharacters();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.invalidFormat();
    }
    @Test
    void tourOnCreditWithTheWrongCardDoNotFillOut() { //Сценарий 56. Не заполнено поле "Номер карты"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardDoNotFillOut();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.requiredToFill();
    }
    @Test
    void tourOnCreditWithTheWrongMonthDoNotFillOut() { //Сценарий 57. Не заполнено поле "Месяц"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDoNotFillOut();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.requiredToFill();
    }
    @Test
    void tourOnCreditWithTheWrongYearDoNotFillOut() { //Сценарий 58. Не заполнено поля "Год"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDoNotFillOut();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.requiredToFill();
    }
    @Test
    void tourOnCreditWithTheWrongNameDoNotFillOut() { //Сценарий 59. Не заполнено поля "Владелец"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameDoNotFillOut();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.requiredToFill();
    }
    @Test
    void tourOnCreditWithTheWrongCVCDoNotFillOut() { //Сценарий 60. Не заполнено поля "CVC/CVV"
        var clickCreditButton = MainPage.clickCreditButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCDoNotFillOut();
        clickCreditButton.setValues(cardInfo);
        clickCreditButton.requiredToFill();
    }
}
