package main.HomeWork2;

import java.math.BigDecimal;

public class EuroBanknote extends Banknote {

    public EuroBanknote(Currency currency, int nominal) {
        super(currency, nominal);
    }

    private static final int[] AVAILABLE_NOMINALS = {5,10,20,50,100,200};

    @Override
    public BigDecimal convertTo(Currency currencyTarget) {
        return ExchangeRate.convert(new BigDecimal(nominal), this.currency, currencyTarget);
    }

    @Override
    public int[] getAvailableNominals() {
        return AVAILABLE_NOMINALS.clone();
    }
}