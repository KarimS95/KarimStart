package main.HomeWork;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessMyNumberGame {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random random =  new Random();

        int minRange = 1;
        int maxRange = 100;

        int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
        int numberOfAttempts = 0;
        int userGuess = 0;
        boolean hasGuessedCorrectly = false;
        System.out.println("Welcome to the game 'Guess a number'\nI guessed a number between " + minRange + " and " + maxRange + "\nTry to guess");

        while(!hasGuessedCorrectly) {
            System.out.println("Enter a guess: ");
            try {
                userGuess = scan.nextInt();
                numberOfAttempts++;
                if(userGuess < minRange || userGuess > maxRange) {
                    System.out.println("Please, try again, enter a number between " + minRange + " and " + maxRange);
                    continue;
                }
                if(userGuess < numberToGuess) {
                    System.out.println("Please, try again, the number being guessed is greater than the one you entered");


                }
                else if(userGuess > numberToGuess) {
                    System.out.println("Please, try again, the number being guessed is less than the one you entered");

                } else {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations!\nYou're guessed a number: " + numberToGuess + " in " + numberOfAttempts + " attempts");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Please, enter an integer number");
                scan.next();
            }
            catch (IllegalStateException e) {
                System.out.println("Scan closed");
            }

        }


    }
}
