package main.HomeWork;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class IsEven {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Enter a number: ");
        try {
            int number = scan.nextInt();
            if (number % 2 == 0) {
                System.out.println("Number is even: " + number);
            } else {
                System.out.println("Error. Number is not even: " + number);
            }

            scan.close();
        } catch (InputMismatchException e) {
            System.err.println("Invalid value");
        }

    }


}
