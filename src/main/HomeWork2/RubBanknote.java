package main.HomeWork2;

import java.math.BigDecimal;

public class RubBanknote extends Banknote {

    public RubBanknote(Currency currency, int nominal) {
        super(currency, nominal);
    }

    private static final int[] AVAILABLE_NOMINALS = {50,100,200,500,1000,5000};

    @Override
    public BigDecimal convertTo(Currency currencyTarget) {
        return ExchangeRate.convert(new BigDecimal(nominal), this.currency, currencyTarget);
    }

    @Override
    public int[] getAvailableNominals() {
        return AVAILABLE_NOMINALS.clone();
    }
}