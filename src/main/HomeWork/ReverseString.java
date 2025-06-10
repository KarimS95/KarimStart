package main.HomeWork;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //1 вариант

        System.out.println("Enter a value: ");
        String example = scan.nextLine();
        String reversedExample = new StringBuilder(example).reverse().toString();
        System.out.println("Reversed string: " + reversedExample);

        //2 вариант

        System.out.println("Enter a value: ");
        String example2 = scan.nextLine();
        String reversedExmaple2 = "";

        for(int i = example2.length() - 1; i >= 0; i--) {
            reversedExmaple2 += example2.charAt(i);
        }
        System.out.println("Reversed string: " + reversedExmaple2);
    }
}
