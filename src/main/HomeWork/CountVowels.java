package main.HomeWork;

import java.util.Arrays;
import java.util.Scanner;


public class CountVowels {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        final String[] values = {"а", "у", "о", "и", "э", "ы", "я", "ю", "е", "ё", "a", "e", "i", "o", "u"};
        int countOfVowels = 0;

        System.out.println("Enter comma-separated vowel letters" + "\n(The vowel letters: " + Arrays.toString(values) + ")");
        String inputValues = scan.nextLine();

        for (int i = 0; i < inputValues.length(); i++) {
            char currentChar = inputValues.charAt(i);

            if (inputValues.isBlank()) {
                System.err.println("Values is blank");

            } else if (Character.isDigit(currentChar)) {
                System.err.println("Values must be a letters. Try again");
                System.exit(1);

            } else {
                for(String value : values) {
                    char exampleChar = value.charAt(0);
                    if(currentChar == exampleChar) {
                        countOfVowels++;

                    }
                }
            }
        }

        System.out.println("\nThe count of vowels letters in the entered values = " + countOfVowels);
    }
}
