package main.HomeWork;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckDate {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int day = 0, month = 0, year = 0;
        int daysInMonth = 0;

        try {
            System.out.println("Enter a day:");
            day = scan.nextInt();
            if (day < 0 || day > 35) {
                System.err.println("Day must be between 1 and 31\nPlease, try again:");
                return;
            }

            System.out.println("Enter a month:");
            month = scan.nextInt();
            if (month < 1 || month > 12) {
                System.err.println("Month must be between 1 and 31\nPlease, try again:");
                return;
            }

            System.out.println("Enter a year.\nYear must be start at 1900+");
            year = scan.nextInt();
            if (year < 1900) {
                System.err.println("Year must be start at 1900+\nPlease, try again");
                return;
            }

        } catch (InputMismatchException e) {
            System.out.println("Error. Invalid data-type");
            scan.next();
        }

        if (month == 2) {
            if (isLeapYear(year)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            daysInMonth = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        }

        if (day >= 1 && day <= daysInMonth) {
            if (isValidDate(day, month, year)) {
                System.out.println("Date is correct:\n" + day + ", " + month + ", " + year);
            } else {
                System.err.println("Date is not correct");
            }
        } else {
            System.err.println("Date is not correct");
        }

    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 != 0)) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else return (year % 400 == 0);
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1900) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1) {
            return false;
        }
        return true;
    }
}
