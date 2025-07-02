package main.HomeWork2;

import java.math.BigDecimal;
import java.util.*;

import static main.HomeWork2.Currency.*;
import static main.HomeWork2.ExchangeRate.convert;

public class ATMApplication {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        int[] availableActions = {1, 2, 3, 4};
        int action = 0;
        BigDecimal amount;
        Currency currency;
        Currency enteredCurrencyFrom;
        Currency enteredCurrencyTo;
        int enteredNominal = 0;
        BigDecimal currencyConversionResultAmount = new BigDecimal(0);
        Banknote banknote;
        String enteredAction = null;
        boolean isValidAction = false;
        boolean isProcessGoing = true;
        boolean isFirstIteration = true;

        while (isProcessGoing) {

            if (isFirstIteration) {
                System.out.println("\nWelcome to ATM!\nATM menu:\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - check balance\n4 - exit");
                isFirstIteration = false;

            } else {
                System.out.println("\nATM menu:\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - check balance\n4 - exit");
            }

            try {
                action = Integer.parseInt(scan.nextLine());

                for (int validAction : availableActions) {
                    if (validAction == action) {
                        isValidAction = true;
                        break;
                    }
                }

                if (!isValidAction) {
                    System.err.println("Invalid action\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - checking balance\n4 - Exit");
                }

                isValidAction = false;

                switch (action) {
                    case 1:
                        enteredAction = "balance deposit";
                        System.out.println("Entered action: " + enteredAction);
                        break;
                    case 2:
                        enteredAction = "balance withdraw";
                        System.out.println("Entered action: " + enteredAction);
                        break;
                    case 3:
                        enteredAction = "checking balance";
                        System.out.println("Entered action: " + enteredAction);
                        break;
                }

                switch (action) {

                    case 1:
                        System.out.println("Enter a currency from: ");

                        enteredCurrencyFrom = Currency.valueOf(scan.nextLine().toUpperCase());

                        System.out.println("Enter a currency to: ");
                        enteredCurrencyTo = Currency.valueOf(scan.nextLine().toUpperCase());

                        System.out.println("Enter a amount to converting: ");
                        amount = new BigDecimal(scan.nextLine());

                        currencyConversionResultAmount = convert(amount, enteredCurrencyFrom, enteredCurrencyTo);

                        ATM.amountValidator(currencyConversionResultAmount, enteredCurrencyTo);

                        System.out.println("Converting result: from " + enteredCurrencyFrom + " to " + enteredCurrencyTo + " = " + currencyConversionResultAmount);

                        System.out.println("Enter the nominal with which you want to receive the requested amount " + currencyConversionResultAmount + " :");
                        enteredNominal = Integer.parseInt(scan.nextLine());

                        banknote = ATM.banknoteValidator(enteredCurrencyTo, enteredNominal);

                        ATM.deposit(enteredCurrencyTo, banknote, currencyConversionResultAmount);
                        ATM.calcTotalAmount(enteredCurrencyTo, currencyConversionResultAmount, 1);
                        ATM.getTotalAmount(action, banknote);

                        break;

                    case 2:
                        System.out.println("Enter a currency from: ");
                        enteredCurrencyFrom = valueOf(scan.nextLine().toUpperCase());

                        System.out.println("Enter a currency to: ");
                        enteredCurrencyTo = valueOf(scan.nextLine().toUpperCase());

                        System.out.println("Enter a amount to converting: ");
                        amount = new BigDecimal(scan.nextLine());

                        currencyConversionResultAmount = convert(amount, enteredCurrencyFrom, enteredCurrencyTo);

                        ATM.amountValidator(currencyConversionResultAmount, enteredCurrencyTo);

                        System.out.println("Converting result: from " + enteredCurrencyFrom + " to " + enteredCurrencyTo + " = " + currencyConversionResultAmount);

                        System.out.println("Enter the nominal with which you want to receive the requested amount " + currencyConversionResultAmount + " :");
                        enteredNominal = Integer.parseInt(scan.nextLine());

                        banknote = ATM.banknoteValidator(enteredCurrencyTo, enteredNominal);

                        ATM.withdraw(enteredCurrencyTo, banknote, currencyConversionResultAmount);
                        ATM.calcTotalAmount(enteredCurrencyTo, currencyConversionResultAmount, 2);
                        ATM.getTotalAmount(action, banknote);

                        break;

                    case 3:

                        System.out.println("Enter a currency");
                        currency = valueOf(scan.nextLine().toUpperCase());

                        BigDecimal balance = ATM.getBalance(currency);
                        System.out.println("Your balance is " + balance);
                        break;

                    case 4:

                        System.out.println("Exiting the program");
                        System.exit(1);

                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.err.println("Invalid value");
            }

            if (action == 4) {
                isProcessGoing = false;
            }
        }
    }
}

