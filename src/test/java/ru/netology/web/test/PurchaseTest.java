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
import ru.netology.web.util.ScreenShooterReportPortalExtension;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

//public class PurchaseTest
@ExtendWith({ScreenShooterReportPortalExtension.class})
class PurchaseTest {
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
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getValidApprovedCard();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.checkSuccessNotification();
        var Info = SQLHelper.purchaseStatusCard();
        SQLHelper.statusConfirmation("APPROVED", Info);
    }

    @Test
    void buyingATourWithAnDeclinedCard() { //Сценарий 2. Покупка тура картой "ОТКЛОНЕННЫЙ"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getValidDeclinedCard();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.checkErrorNotification();
        var Info = SQLHelper.purchaseStatusCard();
        SQLHelper.statusConfirmation("DECLINED", Info);
    }

    @Test
    void buyingATourWithTheWrongCard() { //Сценарий 5. В поле "Номер карты" ввести не существующую карту.
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCard();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.checkErrorNotification();
    }

    @Test
    void buyingATourWithTheWrongCardLessThanSixteen() { //Сценарий 6. В поле "Номер карты" ввести меньше 16 цифр
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLessThanSixteen();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }

    @Test
    void buyingATourWithTheWrongCardLetters() { //Сценарий 7. В поле "Номер карты" ввести буквы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardLetters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCardSpecialCharacters() { //Сценарий 8. В поле "Номер карты" ввести спецсимволы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardSpecialCharacters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongMonthDigit() { //Сценарий 9. В поле "Месяц" ввести 1 цифру
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDigit();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }

    @Test
    void buyingATourWithTheWrongMonthZero() { //Сценарий 10. В поле "Месяц" ввести 00
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthZero();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongMonthWrongDate() { //Сценарий 11. В поле "Месяц" ввести не существующий номер месяца
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthWrongDate();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidCardExpirationDate();
    }
    @Test
    void buyingATourWithTheWrongMonthOverdueDate() { //Сценарий 12. В поле "Месяц" и "Год" ввести просроченную дату
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthOverdueDate();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidCardExpirationDate();
    }
    @Test
    void buyingATourWithTheWrongMonthLetters() { //Сценарий 13. В поле "Месяц" ввести буквы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthLetters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongMonthSpecialCharacters() { //Сценарий 14. В поле "Месяц" ввести спецсимволы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthSpecialCharacters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongYearDigit() { //Сценарий 15. В поле "Год" ввести 1 цифру
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDigit();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongYearZero() { //Сценарий 16. В поле "Год" ввести значение 00
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearZero();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.cardExpired();
    }
    @Test
    void buyingATourWithTheWrongYearPrevious() { //Сценарий 17. В поле "Год" ввести предыдущий год
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearPrevious();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.cardExpired();
    }
    @Test
    void buyingATourWithTheWrongYearNonExistentTerm() { //Сценарий 18. В поле "Год" ввести + шесть лет от текущего года
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearNonExistentTerm();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidCardExpirationDate();
    }
    @Test
    void buyingATourWithTheWrongYearLetters() { //Сценарий 19. В поле "Год" ввести буквы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearLetters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongYearSpecialCharacters() { //Сценарий 20. В поле "Год" ввести спецсимволы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearSpecialCharacters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongNameNumbers() { //Сценарий 21. В поле "Владелец" ввести цифры
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameNumbers();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongNameCyrillic() { //Сценарий 22. В поле "Владелец" ввести не латинские буквы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameCyrillic();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongNameSpecialCharacters() { //Сценарий 23. В поле "Владелец" ввести спецсимволы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameSpecialCharacters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCVCLessThanThree() { //Сценарий 24. В поле "CVC/CVV" ввести меньше 3 цифр
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLessThanThree();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCVCZero() { //Сценарий 25. В поле "CVC/CVV" ввести все 000
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCZero();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCVCLetters() { //Сценарий 26. В поле "CVC/CVV" ввести буквы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCLetters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCVCSpecialCharacters() { //Сценарий 27. В поле "CVC/CVV" ввести спецсимволы
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCSpecialCharacters();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.invalidFormat();
    }
    @Test
    void buyingATourWithTheWrongCardDoNotFillOut() { //Сценарий 28. Не заполнено поле "Номер карты"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCardDoNotFillOut();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.requiredToFill();
    }
    @Test
    void buyingATourWithTheWrongMonthDoNotFillOut() { //Сценарий 29. Не заполнено поле "Месяц"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedMonthDoNotFillOut();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.requiredToFill();
    }
    @Test
    void buyingATourWithTheWrongYearDoNotFillOut() { //Сценарий 30. Не заполнено поля "Год"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedYearDoNotFillOut();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.requiredToFill();
    }
    @Test
    void buyingATourWithTheWrongNameDoNotFillOut() { //Сценарий 31. Не заполнено поля "Владелец"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedNameDoNotFillOut();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.requiredToFill();
    }
    @Test
    void buyingATourWithTheWrongCVCDoNotFillOut() { //Сценарий 32. Не заполнено поля "CVC/CVV"
        var clickBuyButton = MainPage.clickBuyButton();
        var cardInfo = DataHelper.getNotValidDeclinedCVCDoNotFillOut();
        clickBuyButton.setValues(cardInfo);
        clickBuyButton.requiredToFill();
    }
}
