package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


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
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getValidDeclinedCard() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getDeclinedCard(), month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCard() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(generateRandomCard(), month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardLessThanSixteen() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(generateRandomCardLessThanSixteen(), month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardLetters() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCardSpecialCharacters() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("!@#$%^&*()_+!№;%:?*()", month, years, generateRandomName(), generateRandomCVC());
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
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, "0123456789", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameCyrillic() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomNameCyrillic(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameSpecialCharacters() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, "!@#$%^&*()_+!№;%:?*()", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCVCLessThanThree() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), generateRandomCVCLessThanThree());
    }

    public static CardInfo getNotValidDeclinedCVCZero() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), "000");
    }

    public static CardInfo getNotValidDeclinedCVCLetters() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public static CardInfo getNotValidDeclinedCVCSpecialCharacters() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), "!@#$%^&*()_+!№;%:?*()");
    }

    public static CardInfo getNotValidDeclinedCardDoNotFillOut() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("", month, years, generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedMonthDoNotFillOut() {
        return new CardInfo(getApprovedCard(), "", generateValidYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedYearDoNotFillOut() {
        return new CardInfo(getApprovedCard(), generateValidMonth(), "", generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedNameDoNotFillOut() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, "", generateRandomCVC());
    }

    public static CardInfo getNotValidDeclinedCVCDoNotFillOut() {
        int yearsShift = faker.number().numberBetween(0, 5);
        int thisMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));
        int max = 12 - thisMonth;
        int monthShift;
        if (yearsShift == 0) {
            monthShift = faker.number().numberBetween(0, max);
        } else {
            monthShift = faker.number().numberBetween(0, 11);
        }
        String years = LocalDate.now().plusYears(yearsShift).format(DateTimeFormatter.ofPattern("yy"));
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo(getApprovedCard(), month, years, generateRandomName(), "");
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
