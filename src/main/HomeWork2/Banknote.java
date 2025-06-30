package main.HomeWork2;


import java.math.BigDecimal;
import java.util.Arrays;

public abstract class Banknote {
    public final Currency currency;
    public final int nominal;

    public Banknote(Currency currency, int nominal) {
        this.currency = currency;
        if(!isValidNominal(nominal)) {
            throw new IllegalArgumentException
                    ("Invalid nominal for this currency " +
                            getClass().getSimpleName() +
                            ", available values = " +
                            Arrays.toString(getAvailableNominals()));
        }
        else {
            this.nominal = nominal;
        }
    }



    public abstract BigDecimal convertTo(Currency currencyTarget);

    public boolean isValidNominal(int nominal) {
        int[] availableNominals = getAvailableNominals();
        for (int i : availableNominals) {
            if ( i == nominal) {
                return true;
            }
        } return false;
    }

    public abstract int[] getAvailableNominals();

}
