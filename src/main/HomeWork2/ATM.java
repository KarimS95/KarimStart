package main.HomeWork2;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static main.HomeWork2.Currency.*;

public class ATM {
    private static final Map<Currency, Integer> CASSETTE_CAPACITY = Map.of( //Макс. вместимость кассет валют
            USD, 100,
            EUR, 100,
            RUB, 100
    );
    static Map<Currency, Integer> cassette = new HashMap<>();

    static { //Инициализация кассет валют
        cassette.put(USD, 20); //20 купюр (если totalAmount = 2000 y.e., то 20 шт usd купюр = 100 номиналом каждая)
        cassette.put(EUR, 10); //10 купюр (если totalAmount = 2000 y.e., то 10 шт eur купюр = 200 номиналом каждая)
        cassette.put(RUB, 40); //40 купюр (если totalAmount = 2000 y.e., то 40 шт rub купюр = 50 номиналом каждая)
    }

    private static BigDecimal totalAmount = BigDecimal.valueOf(2000); //Инициализация общего начального баланса банкомата: 200 у.е.

    public static void deposit(Banknote banknote, BigDecimal amount) {
        if (cassette != null) {
            int banknoteCount = amountValidator (amount, banknote.currency);
            Integer currentCurrencyNominalsCount = cassette.get(banknote.currency); //Создание Integer переменной-ссылки на значение кассеты определенной валюты
            if (currentCurrencyNominalsCount >= CASSETTE_CAPACITY.getOrDefault(banknote.currency, 0)) {
                System.err.println("Cassette for " + banknote.currency + " is full");
            } else {
                System.out.println("Test banknote counts: " + banknoteCount);
                int cassetteCapacity = CASSETTE_CAPACITY.get(banknote.currency);

                if (currentCurrencyNominalsCount + banknoteCount <= cassetteCapacity) {
                    int updatedNominalCount = currentCurrencyNominalsCount + banknoteCount; //Обновление количества номиналов в кассете валюты

                    System.out.println("\nCurrent balance: " + ATM.getBalance() + "\nCurrent capacity: " + cassette.entrySet());
                    cassette.put(banknote.currency, updatedNominalCount);
                    System.out.println("\nDeposit successful: +" + amount + " " + banknote.currency);
                } else {
                    System.err.println("Nominals count in cassette must be: " + cassetteCapacity + "\nEntered nominals: " + banknoteCount + " + " + "current nominals: " + currentCurrencyNominalsCount + " = " + (banknoteCount + currentCurrencyNominalsCount));
                    System.exit(1);
                }
            }
        } else {
            System.err.println("Error");
        }
    }

    public static void withdraw(Banknote banknote, BigDecimal amount) {
        if (cassette != null) {
            int banknoteCount = amountValidator (amount, banknote.currency);
            Integer currentCurrencyNominalsCount = cassette.get(banknote.currency);
            int updatedNominalCount = currentCurrencyNominalsCount - banknoteCount;

            if ((currentCurrencyNominalsCount - banknoteCount >= currentCurrencyNominalsCount) || currentCurrencyNominalsCount > 1 && totalAmount.compareTo(amount) > 0) {
                //Если кассета валюты и общая вместимость кассеты равны или больше 1 и общий баланс больше 0, то можно провести снятие средств

                System.out.println("\nCurrent balance: " + ATM.getBalance() + "\nCurrent capacity: " + cassette.entrySet());
                cassette.put(banknote.currency, updatedNominalCount);
                System.out.println("\nWithdrawn successful: -" + amount + " " + banknote.currency);

            } else if (currentCurrencyNominalsCount < banknoteCount) {
                System.err.println("There are not enough nominals in the cassette: " + "\n" + "Current nominals state: " + currentCurrencyNominalsCount + "\nEntered nominals: " + banknoteCount);
                System.exit(1);
            } else if((totalAmount.subtract(amount).compareTo(BigDecimal.ZERO) < 0)) {
                System.err.println("There are not enough balance in ATM: " + "\n" + "Current balance: " + ATM.getBalance() + "\nEntered amount: " + amount);
                System.exit(1);
            }
        }
    }

    public static Banknote banknoteValidator(Currency currency, int nominal) {
        switch (currency) {
            case USD:
                return new DollarBanknote(currency, nominal);
            case EUR:
                return new EuroBanknote(currency, nominal);
            case RUB:
                return new RubBanknote(currency, nominal);
            default:
                throw new IllegalArgumentException("Invalid currency");
        }
    }

    public static void calcTotalAmount(BigDecimal amount, int action) {
        switch (action) {
            case 1:
                totalAmount = totalAmount.add(amount);
                break;
            case 2:
                totalAmount = totalAmount.subtract(amount);
                break;
        }

    }

    public static void getTotalAmount(int action, Banknote banknote) {
        switch (action) {
            case 1, 2:
                switch (banknote.currency) {
                    case USD:
                        System.out.println("Operation result:\nUSD amount: " + totalAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                    case EUR:
                        System.out.println("Operation result:\nEUR " + totalAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                    case RUB:
                        System.out.println("Operation result:\nRUB " + totalAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                }

                break;
        }
    }

    public static BigDecimal getBalance() {
        return totalAmount;
    }

    //Логика проверки вычисления суммы купюр, необходимых для покрытия суммы amount. Например: Для суммы 1470 EUR нужно использовать 7 купюр по 200, 1 купюру 50 и 1 купюру 20
    public static int amountValidator(BigDecimal amount, Currency currency) {
        int countOfBills = 0;
        if (amount.remainder(BigDecimal.valueOf(5)).equals(BigDecimal.ZERO)) { //Проверка на возможность внести сумму, с учетом доступных для ввода номиналов купюр: например: сумме в 1471 евро не получится внести, т.к. минимальная возможная купюра, которую банкомат может принять для данной валюты, не покроет всю сумму без остатка
            switch (currency) {
                case USD:
                    String[] AVAILABLE_USD_NOMINALS = {"100", "50", "20", "10", "5", "1"};
                    int[] countUsdBills = new int[AVAILABLE_USD_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_USD_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_USD_NOMINALS[i]);
                        countUsdBills[i] = amount.divideToIntegralValue(billValue).intValue();
                        amount = amount.remainder(billValue);
                        countOfBills += countUsdBills[i];
                    }
                    break;

                case EUR:
                    String[] AVAILABLE_EUR_NOMINALS = {"200", "100", "50", "20", "10", "5"};
                    int[] countEurBills = new int[AVAILABLE_EUR_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_EUR_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_EUR_NOMINALS[i]);
                        countEurBills[i] = amount.divideToIntegralValue(billValue).intValue();
                        amount = amount.remainder(billValue);
                        countOfBills += countEurBills[i];
                    }
                    break;

                case RUB:
                    String[] AVAILABLE_RUB_NOMINALS = {"5000", "1000", "500", "200", "100", "50"};
                    int[] countRubBills = new int[AVAILABLE_RUB_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_RUB_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_RUB_NOMINALS[i]);
                        countRubBills[i] = amount.divideToIntegralValue(billValue).intValue();
                        amount = amount.remainder(billValue);
                        countOfBills += countRubBills[i];
                    }
                    break;
            }
        } else {
            System.err.println("Invalid amount. Enter a correct amount for " + currency);
            System.exit(1);
        }
        return countOfBills;
    }
}
