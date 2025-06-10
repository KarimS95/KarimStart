package main.HomeWork;

import java.util.Scanner;

public class TimeCalculator {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            System.out.println("Enter the number: ");
            String userInput = scan.nextLine();
            int totalSeconds = Integer.parseInt(userInput);
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;

            if (hours != 0 && minutes == 0 && seconds == 0) { // ровно час
                System.out.println("Result: in " + totalSeconds + " seconds " + hours + " hours");
            } else if (hours != 0 && minutes != 0 && seconds == 0) { // час + минуты
                System.out.println("Result: in " + totalSeconds + " seconds " + hours + " hours " + minutes + " minutes ");
            } else if (hours == 0 && minutes != 0 && seconds == 0) { // минуты
                System.out.println("Result: in " + totalSeconds + " seconds " + minutes + " minutes ");
            } else if (hours == 0 && minutes != 0 && seconds != 0) { // минуты + секунды
                System.out.println("Result: in " + totalSeconds + " seconds " + minutes + " minutes " + seconds + " seconds");
            } else if (hours == 0 && minutes == 0 && seconds != 0) { // секунды
                System.out.println("Result: in " + totalSeconds + " seconds " + seconds + " seconds");
            } else {
                System.out.println("Result: in " + totalSeconds + " seconds " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
            }


        } catch (NumberFormatException e) {
            System.err.println("Invalid format");
        }
    }

}
