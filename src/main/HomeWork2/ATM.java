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
        cassette.put(USD, 20); //20 купюр (если totalUsdAmount = 2000, то 20 шт usd купюр = например: 100 номиналом каждая)
        cassette.put(EUR, 40); //10 купюр (если totalEurAmount = 4000, то 40 шт eur купюр = например: 200 номиналом каждая)
        cassette.put(RUB, 50); //40 купюр (если totalRubAmount = 5000, то 50 шт rub купюр = например: 50 номиналом каждая)
    }

    //Инициализация баланса банкомата для каждой валюты
    private static BigDecimal totalUsdAmount = BigDecimal.valueOf(2000);
    private static BigDecimal totalEurAmount = BigDecimal.valueOf(4000);
    private static BigDecimal totalRubAmount = BigDecimal.valueOf(5000);

    static int[] availableActions = {1, 2, 3, 4};

    static String rates = "USD to EUR: 0.9\nUSD to RUB: 75";

    public static void deposit(Banknote banknote, BigDecimal amount) {
        if (cassette != null) {
            int banknoteCount = amountValidator(amount, banknote.currency);
            Integer currentCurrencyNominalsCount = cassette.get(banknote.currency); //Создание Integer переменной-ссылки на значение кассеты определенной валюты
            if (currentCurrencyNominalsCount >= CASSETTE_CAPACITY.getOrDefault(banknote.currency, 0)) {
                System.err.println("Cassette for " + banknote.currency + " is full");
            } else {
                int cassetteCapacity = CASSETTE_CAPACITY.get(banknote.currency);

                if (currentCurrencyNominalsCount + banknoteCount <= cassetteCapacity) {
                    int updatedNominalCount = currentCurrencyNominalsCount + banknoteCount; //Обновление количества номиналов в кассете валюты

                    System.out.println("\nCurrent balance: " + ATM.getBalance(banknote.currency) + "\nCurrent capacity: " + cassette.entrySet());
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
            int banknoteCount = amountValidator(amount, banknote.currency);
            Integer currentCurrencyNominalsCount = cassette.get(banknote.currency);
            int updatedNominalCount = currentCurrencyNominalsCount - banknoteCount;

            switch (banknote.currency) {
                case USD:
                    if ((currentCurrencyNominalsCount - banknoteCount >= currentCurrencyNominalsCount) || currentCurrencyNominalsCount > 1 && totalUsdAmount.compareTo(amount) > 0) {
                        //Если кассета валюты и общая вместимость кассеты равны или больше 1 и общий баланс больше 0, то можно провести снятие средств

                        System.out.println("\nCurrent balance: " + ATM.getBalance(banknote.currency) + "\nCurrent capacity: " + cassette.entrySet());
                        cassette.put(banknote.currency, updatedNominalCount);
                        System.out.println("\nWithdrawn successful: -" + amount + " " + banknote.currency);
                    } else if (currentCurrencyNominalsCount < banknoteCount) {
                        System.err.println("There are not enough nominals in the cassette: " + "\n" + "Current nominals state: " + currentCurrencyNominalsCount + "\nEntered nominals: " + banknoteCount);
                        System.exit(1);
                    } else if ((totalUsdAmount.subtract(amount).compareTo(BigDecimal.ZERO) < 0)) {
                        System.err.println("There are not enough balance in ATM: " + "\n" + "Current balance: " + ATM.getBalance(banknote.currency) + "\nEntered amount: " + amount);
                        System.exit(1);
                    }

                case EUR:
                    if ((currentCurrencyNominalsCount - banknoteCount >= currentCurrencyNominalsCount) || currentCurrencyNominalsCount > 1 && totalEurAmount.compareTo(amount) > 0) {

                        System.out.println("\nCurrent balance: " + ATM.getBalance(banknote.currency) + "\nCurrent capacity: " + cassette.entrySet());
                        cassette.put(banknote.currency, updatedNominalCount);
                        System.out.println("\nWithdrawn successful: -" + amount + " " + banknote.currency);
                    } else if (currentCurrencyNominalsCount < banknoteCount) {
                        System.err.println("There are not enough nominals in the cassette: " + "\n" + "Current nominals state: " + currentCurrencyNominalsCount + "\nEntered nominals: " + banknoteCount);
                        System.exit(1);
                    } else if ((totalEurAmount.subtract(amount).compareTo(BigDecimal.ZERO) < 0)) {
                        System.err.println("There are not enough balance in ATM: " + "\n" + "Current balance: " + ATM.getBalance(banknote.currency) + "\nEntered amount: " + amount);
                        System.exit(1);
                    }

                case RUB:
                    if ((currentCurrencyNominalsCount - banknoteCount >= currentCurrencyNominalsCount) || currentCurrencyNominalsCount > 1 && totalRubAmount.compareTo(amount) > 0) {

                        System.out.println("\nCurrent balance: " + ATM.getBalance(banknote.currency) + "\nCurrent capacity: " + cassette.entrySet());
                        cassette.put(banknote.currency, updatedNominalCount);
                        System.out.println("\nWithdrawn successful: -" + amount + " " + banknote.currency);
                    } else if (currentCurrencyNominalsCount < banknoteCount) {
                        System.err.println("There are not enough nominals in the cassette: " + "\n" + "Current nominals state: " + currentCurrencyNominalsCount + "\nEntered nominals: " + banknoteCount);
                        System.exit(1);
                    } else if ((totalRubAmount.subtract(amount).compareTo(BigDecimal.ZERO) < 0)) {
                        System.err.println("There are not enough balance in ATM: " + "\n" + "Current balance: " + ATM.getBalance(banknote.currency) + "\nEntered amount: " + amount);
                        System.exit(1);
                    }
            }
        }
    }

    public static Banknote createBanknote(Currency currency, int nominal) { //Создание объекта класса DollarBanknote/Eur/Rub
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

    public static void calcTotalAmount(Banknote banknote, BigDecimal amount, int action) {
        switch (action) {
            case 1:
                switch (banknote.currency) {
                    case USD:
                        totalUsdAmount = totalUsdAmount.add(amount);
                        break;
                    case EUR:
                        totalEurAmount = totalEurAmount.add(amount);
                        break;
                    case RUB:
                        totalRubAmount = totalRubAmount.add(amount);
                }
                break;

            case 2:
                switch (banknote.currency) {
                    case USD:
                        totalUsdAmount = totalUsdAmount.subtract(amount);
                        break;
                    case EUR:
                        totalEurAmount = totalEurAmount.subtract(amount);
                        break;
                    case RUB:
                        totalRubAmount = totalRubAmount.subtract(amount);
                }
                break;
        }

    }

    public static void getTotalAmount(int action, Banknote banknote) {
        switch (action) {
            case 1, 2:
                switch (banknote.currency) {
                    case USD:
                        System.out.println("Operation result:\nUSD amount: " + totalUsdAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalUsdAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                    case EUR:
                        System.out.println("Operation result:\nEUR amount: " + totalEurAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalEurAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                    case RUB:
                        System.out.println("Operation result:\nRUB amount: " + totalRubAmount + ", nominal: " + banknote.nominal + "\n\nCurrent balance: " + totalRubAmount + "\nCurrent capacity: " + cassette.entrySet());
                        return;
                }

                break;
        }
    }

    public static BigDecimal getBalance(Currency currency) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        switch (currency) {
            case USD:
                totalAmount = totalUsdAmount;
                break;
            case EUR:
                totalAmount = totalEurAmount;
                break;
            case RUB:
                totalAmount = totalRubAmount;
        }
        return totalAmount;
    }

    //Логика проверки вычисления суммы купюр, необходимых для покрытия суммы amount. Например: Для суммы 1470 EUR нужно использовать 7 купюр по 200, 1 купюру 50 и 1 купюру 20
    public static int amountValidator(BigDecimal amount, Currency currency) {
        int countOfBills = 0;

        switch (currency) {
            case USD:
                if (amount.remainder(BigDecimal.valueOf(1)).equals(BigDecimal.ZERO)) { //Проверка на возможность внести сумму, с учетом доступных для ввода номиналов купюр: например: сумму в 1471 евро не получится внести, т.к. минимальная возможная купюра, которую банкомат может принять для данной валюты, не покроет всю сумму без остатка
                    String[] AVAILABLE_USD_NOMINALS = {"100", "50", "20", "10", "5", "1"};
                    int[] countUsdBills = new int[AVAILABLE_USD_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_USD_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_USD_NOMINALS[i]);
                        countUsdBills[i] = amount.divideToIntegralValue(billValue).intValue(); //Вычисление значения целых купюр: 237 / 100 = 2 (без остатка) - сколько целых купюр номиналом 100 потребуется для покрытия суммы в 237
                        amount = amount.remainder(billValue); //Вычисление значения amount в результате получения остатка при делении: 237 / 100 = 2.37 ; 237 - (2*100) = 37; 37 / 20 = 1.85; 37 - (1*20) = 17 и т.д.
                        countOfBills += countUsdBills[i];
                    }
                } else {
                    System.err.println("Invalid amount. Enter a correct amount for " + currency);
                    System.exit(1);
                }
                break;

            case EUR:
                if (amount.remainder(BigDecimal.valueOf(5)).equals(BigDecimal.ZERO)) {
                    String[] AVAILABLE_EUR_NOMINALS = {"200", "100", "50", "20", "10", "5"};
                    int[] countEurBills = new int[AVAILABLE_EUR_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_EUR_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_EUR_NOMINALS[i]);
                        countEurBills[i] = amount.divideToIntegralValue(billValue).intValue();
                        amount = amount.remainder(billValue);
                        countOfBills += countEurBills[i];
                    }
                } else {
                    System.err.println("Invalid amount. Enter a correct amount for " + currency);
                    System.exit(1);
                }
                break;

            case RUB:
                if (amount.remainder(BigDecimal.valueOf(50)).equals(BigDecimal.ZERO)) {
                    String[] AVAILABLE_RUB_NOMINALS = {"5000", "1000", "500", "200", "100", "50"};
                    int[] countRubBills = new int[AVAILABLE_RUB_NOMINALS.length];
                    for (int i = 0; i < AVAILABLE_RUB_NOMINALS.length; i++) {
                        BigDecimal billValue = new BigDecimal(AVAILABLE_RUB_NOMINALS[i]);
                        countRubBills[i] = amount.divideToIntegralValue(billValue).intValue();
                        amount = amount.remainder(billValue);
                        countOfBills += countRubBills[i];
                    }
                } else {
                    System.err.println("Invalid amount. Enter a correct amount for " + currency);
                    System.exit(1);
                }
                break;
        }
        return countOfBills;
    }

    public static void isValidAction(int action) {
        boolean isValidAction = false;

        for (int validAction : availableActions) {
            if (validAction == action) {
                isValidAction = true;
                break;
            }
        }

        if (!isValidAction) {
            System.err.println("Invalid action\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - checking balance\n4 - Exit");
        }
    }

    public static String getRates() {
        return rates;
    }
}