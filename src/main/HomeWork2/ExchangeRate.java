package main.HomeWork2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRate {

    final static BigDecimal usdToEur = BigDecimal.valueOf(0.9), usdToRub = BigDecimal.valueOf(75);

    static BigDecimal convert(BigDecimal amount, Currency currencyFrom, Currency currencyTo) {
        switch (currencyFrom) {
            case USD:
                switch (currencyTo) {
                    case USD:
                        return amount.multiply(BigDecimal.ONE);
                    case EUR: return amount.multiply(usdToEur);
                    case RUB: return amount.multiply(usdToRub);
                }
                break;
            case EUR:
                switch (currencyTo) {
                    case USD: return amount.divide(usdToEur, RoundingMode.HALF_UP);
                    case EUR: return amount.multiply(BigDecimal.ONE);
                }
            case RUB:
                switch (currencyTo) {
                    case USD: return amount.divide(usdToRub, RoundingMode.HALF_UP);
                    case RUB: return amount.multiply(BigDecimal.ONE);
                }
        } throw new IllegalArgumentException("Invalid input currency");
    }
}

