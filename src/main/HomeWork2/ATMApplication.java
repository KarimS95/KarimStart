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
        Currency enteredCurrencyFrom;
        Currency enteredCurrencyTo;
        int enteredNominal = 0;
        BigDecimal currencyConversionResultAmount = new BigDecimal(0);
        Banknote banknote;
        boolean isValidAction = false;


        try {
            System.out.println("Welcome to ATM!\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - check balance\n4 - exit\nEntered action:");
            action = Integer.parseInt(scan.nextLine());
            for (int validAction : availableActions) {
                if (validAction == action) {
                    isValidAction = true;
                    break;
                }
            }
            if (!isValidAction) {
                System.err.println("Invalid action\nPlease, enter between " + Arrays.toString(availableActions));
                return;
            }
            switch (action) {
                case 1:
                    System.out.println("Enter a currency from: ");

                    enteredCurrencyFrom = Currency.valueOf(scan.nextLine().toUpperCase());

                    System.out.println("Enter a currency to: ");
                    enteredCurrencyTo = Currency.valueOf(scan.nextLine().toUpperCase());

                    System.out.println("Enter a amount to converting: ");
                    amount = new BigDecimal(scan.nextLine());

                    ATM.amountValidator(amount, enteredCurrencyTo);
                    currencyConversionResultAmount = convert(amount, enteredCurrencyFrom, enteredCurrencyTo);

                    System.out.println("Converting result: from " + enteredCurrencyFrom + " to " + enteredCurrencyTo + " = " + currencyConversionResultAmount);

                    System.out.println("Enter the nominal with which you want to receive the requested amount " + currencyConversionResultAmount + " :");
                    enteredNominal = Integer.parseInt(scan.nextLine());

                    banknote = ATM.banknoteValidator(enteredCurrencyTo, enteredNominal);

                    ATM.deposit(banknote, currencyConversionResultAmount);
                    ATM.calcTotalAmount(currencyConversionResultAmount, 1);
                    ATM.getTotalAmount(action, banknote);

                    break;

                case 2:
                    System.out.println("Enter a currency from: ");
                    enteredCurrencyFrom = valueOf(scan.nextLine().toUpperCase());

                    System.out.println("Enter a currency to: ");
                    enteredCurrencyTo = valueOf(scan.nextLine().toUpperCase());

                    System.out.println("Enter a amount to converting: ");
                    amount = new BigDecimal(scan.nextLine());

                    ATM.amountValidator(amount, enteredCurrencyTo);

                    currencyConversionResultAmount = convert(amount, enteredCurrencyFrom, enteredCurrencyTo);

                    System.out.println("Enter the nominal with which you want to receive the requested amount " + currencyConversionResultAmount + " :");
                    enteredNominal = Integer.parseInt(scan.nextLine());

                    banknote = ATM.banknoteValidator(enteredCurrencyTo, enteredNominal);

                    ATM.withdraw(banknote, currencyConversionResultAmount);
                    ATM.calcTotalAmount(currencyConversionResultAmount, 2);
                    ATM.getTotalAmount(action, banknote);

                case 3:
                    BigDecimal balance = ATM.getBalance();
                    System.out.println("Your balance is " + balance);
                    break;
                case 4:
                    System.out.println("Exiting the program");
                    System.exit(1);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Invalid value\nEnter a action:\n1 - deposit\n2 - withdraw\n3 - checking balance\n4 - Exit");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}

