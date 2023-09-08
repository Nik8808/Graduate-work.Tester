package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));
    private static Faker fakerCyrillic = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String getApprovedCard() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCard() {
        return "4444 4444 4444 4442";
    }

    public static String generateRandomCard() {
        return faker.numerify("################");
    }

    public static String generateRandomCardLessThanSixteen() {
        return String.valueOf(faker.number().numberBetween(1, 999999999999L));
    }
    private static String generateValidDate(String pattern) {
        int addMonths = new Random().nextInt(60);
        return LocalDate.now().plusMonths(addMonths).format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String generateValidYear() {
        int shift = faker.number().numberBetween(0, 5);
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateValidMonth() {
        int shift = faker.number().numberBetween(0, 11);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static int changeToWrongMonth() {
        int month = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int min = 12 - month + 1;
        return faker.number().numberBetween(min, 11);
    }

    public static String generateNoValidMonth() {
        return LocalDate.now().plusMonths(changeToWrongMonth()).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateRandomWrongDate() {
        return String.valueOf(faker.number().numberBetween(13, 99));
    }

    public static String generateValidYearNotCurrent() {
        int shift = faker.number().numberBetween(1, 5);
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String thisYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateNoValidYear() {
        int thisYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        int shift = faker.number().numberBetween(1, thisYear - 1);
        return LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateNoValidYearNonExistentTerm() {
        int thisYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        int shift = faker.number().numberBetween(5, 99 - thisYear);
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateRandomName() {
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }

    private static String generateRandomNameCyrillic() {
        return fakerCyrillic.name().firstName().toUpperCase() + " " + fakerCyrillic.name().lastName().toUpperCase();
    }

    public static String generateRandomCVC() {
        return faker.numerify("###");
    }

    public static String generateRandomCVCLessThanThree() {
        return String.valueOf(faker.number().numberBetween(1, 99));
    }

    public static String generateRandomNumbers() {
        return faker.numerify("#");
    }


    public static CardInfo getValidApprovedCard() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getValidDeclinedCard() {
        return new CardInfo(getDeclinedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCard() {
                return new CardInfo(generateRandomCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardLessThanSixteen() {
        return new CardInfo(generateRandomCardLessThanSixteen(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardLetters() {
        return new CardInfo("абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardSpecialCharacters() {
                return new CardInfo("!@#$%^&*()_+!№;%:?*()", generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthDigit() {
        return new CardInfo(getApprovedCard(), generateRandomNumbers(), generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthZero() {
        return new CardInfo(getApprovedCard(), "00", generateValidYearNotCurrent(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthWrongDate() {
        return new CardInfo(getApprovedCard(), generateRandomWrongDate(), generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthOverdueDate() {
        return new CardInfo(getApprovedCard(), generateNoValidMonth(), thisYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthLetters() {
        return new CardInfo(getApprovedCard(), "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthSpecialCharacters() {
        return new CardInfo(getApprovedCard(), "!@#$%^&*()_+!№;%:?*()", generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearDigit() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), generateRandomNumbers(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearZero() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), "00", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearPrevious() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), generateNoValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearNonExistentTerm() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), generateNoValidYearNonExistentTerm(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearLetters() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearSpecialCharacters() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), "!@#$%^&*()_+!№;%:?*()", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameNumbers() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), "0123456789", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameCyrillic() {
               return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomNameCyrillic(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameSpecialCharacters() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), "!@#$%^&*()_+!№;%:?*()", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCVCLessThanThree() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVCLessThanThree());
    }

    public static CardInfo getNotValidDeclinedCVCZero() {
         return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), "000");
    }

    public static CardInfo getNotValidDeclinedCVCLetters() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public static CardInfo getNotValidDeclinedCVCSpecialCharacters() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), "!@#$%^&*()_+!№;%:?*()");
    }

    public static CardInfo getNotValidDeclinedCardDoNotFillOut() {
        return new CardInfo("", generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthDoNotFillOut() {
        return new CardInfo(getApprovedCard(), "", generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearDoNotFillOut() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), "", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameDoNotFillOut() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), "", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCVCDoNotFillOut() {
        return new CardInfo(getApprovedCard(), generateValidDate("MM"), generateValidDate("yy"), generateRandomName(), "");
    }

    @Value
    @Setter
    public static class CardInfo {
        String number;
        String month;
        String year;
        String Name;
        String cvc;
    }
}
