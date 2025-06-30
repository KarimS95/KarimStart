package main.HomeWork2;

public enum Currency {
    USD, EUR, RUB;

    boolean isValidCurrency() {
        return this == USD || this == EUR || this == RUB;
    }
}
