package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    private DataHelper() {
    }


    private static Faker faker = new Faker(new Locale("ru"));

    public static CardInfo getFirstCardNumberAndStatus() {
        return new CardInfo("4444 4444 4444 4441", "APPROVED");
    }

    public static CardInfo getSecondCardNumberAndStatus() {
        return new CardInfo("4444 4444 4444 4442", "DECLINED");
    }

    public static LocalDate generateValidDate() {
        return LocalDate.now().plusMonths(14);
    }

    public static String generateMonth() {

        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String generateYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateFullName() {
        return faker.name().firstName().replaceAll("ё", "е") + " "
                + faker.name().lastName().replaceAll("ё", "е");
    }

    public static String generateCvc() {
        return faker.number().digits(3);
    }

    public static String generateCardNumber() {
        return faker.business().creditCardNumber();
    }


    @Value
    public static class CardInfo {
        private String cardNumber;
        private String status;
    }

}
