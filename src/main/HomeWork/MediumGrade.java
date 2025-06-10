package main.HomeWork;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MediumGrade {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        String[] existingArray = {"1", "2", "3", "4", "5"};

        try {
            System.out.println("Enter the values separated by commas:" + "\nThe correct values: " + Arrays.toString(existingArray));
            String[] inputArray = scan.nextLine().split(",");

            int sum = 0;
            int average = 0;

            for (int i = 0; i < inputArray.length; i++) {
                int inputArrayInt = Integer.parseInt(inputArray[i]);
                if (Arrays.equals(inputArray, existingArray)) {
                    sum += inputArrayInt;
                    average = sum / inputArray.length;
                } else {
                    System.err.println("Values must be from list: " + Arrays.toString(existingArray));
                    System.exit(1);
                }
            }
            System.out.println("Average value from sum(" + Arrays.toString(inputArray) + ") = " + average);

        } catch (NumberFormatException e) {
            System.err.println("Invalid data-type");
        }
    }
}

