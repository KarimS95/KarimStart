package main.HomeWork;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        BigDecimal num1, num2, result = null;

        System.out.println("Enter a number1");
        num1 = scan.nextBigDecimal();

        System.out.println("Enter a number2");
        num2 = scan.nextBigDecimal();

        System.out.println("Enter a action");
        char action = scan.next().charAt(0);
        try {
            switch (action) {

                case '+':
                    result = num1.add(num2);
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = num1.subtract(num2);
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = num1.multiply(num2);
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    result = num1.divide(num2, RoundingMode.HALF_UP);
                    System.out.println("Result: " + result);
                default:
                    System.err.println("Invalid operation");
            }

        }
        catch (ArithmeticException e) {
            System.err.println("Error divide by zero!");
        }
    }
}
