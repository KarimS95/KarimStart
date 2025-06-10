package main.HomeWork;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        final BigDecimal usd = BigDecimal.valueOf(100); //Курс доллара к рублю
        BigDecimal usdAmount = null; // количество долларов для конвертации
        BigDecimal rubAmount = null; // количество рублей для конвертации
        BigDecimal result = BigDecimal.valueOf(0); // результат с исходным значением = 0

        System.out.println("Enter a currency to convert: usd/rub");
        String currency = scan.nextLine();

        try {
            switch (currency) {
                case "usd":
                    System.out.println("Convert usd->rub:\nEnter a usd amount");
                    usdAmount = scan.nextBigDecimal();

                    if (usdAmount.compareTo(BigDecimal.valueOf(0)) >= 0) {
                        result = usdAmount.multiply(usd);
                    } else {
                        System.err.println("Invalid amount");
                        System.exit(1);
                    }

                    System.out.println("Result: " + result);
                    break;

                case "rub":
                    System.out.println("Convert rub->usd:\nEnter a rub amount");
                    rubAmount = scan.nextBigDecimal();

                    if (rubAmount.compareTo(BigDecimal.valueOf(0)) >= 0) {
                        result = rubAmount.divide(usd, RoundingMode.HALF_UP);
                    } else {
                        System.err.println("Invalid amount");
                        System.exit(1);
                    }
                    System.out.println("Result: " + result);
                    break;
                default:
                    System.err.println("Invalid currency");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error");
        }
    }
}
