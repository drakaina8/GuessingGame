package com;

import java.util.*;
import java.lang.Character;

public class HHGuessingGame {

    public static void main(String[] args)
    {
        final int MAX_RANDOM_NUMBER = 100;
        int totalGuess = 0;
        int numGamesPlayed = 0;
        int bestGuess = 99999;
        boolean wantToPlay;
        Scanner input = new Scanner(System.in);

        instruction(MAX_RANDOM_NUMBER);
        // Initiates gameplay if user input evaluates true
        while (inputYesOrNo(input))
        {
            int numGuessSingleGame = gamePlay(input, MAX_RANDOM_NUMBER);
            if (numGuessSingleGame < bestGuess)
            {
                bestGuess = numGuessSingleGame;
            }
            totalGuess += numGuessSingleGame;
            numGamesPlayed++;
        } // end while
        result(bestGuess, totalGuess, numGamesPlayed);
        System.out.println("Thanks for playing! I'm going to take a nap now..Zzz");
    } // end of main

    // instruction method, prints rules of game
    public static void instruction(int MAX_RANDOM_NUMBER)
    {
        System.out.printf("This program allows you to play a guessing game.%n"
                + "I will think of a number between 1 and %d.%n"
                + "For each guess, I'll tell you if my number is greater%n"
                + "or smaller.%n%n", MAX_RANDOM_NUMBER);
    } // end of instruction method

    // gamePlay method: plays one game with user, returns # of guesses in 1 game
    public static int gamePlay(Scanner input, int MAX_RANDOM_NUMBER)
        throws InputMismatchException
    {
        //Computer picks number randomly between 1 & MAX_RANDOM_NUMBER
        int randomNumber = pickRandomNum(MAX_RANDOM_NUMBER);
        int userGuess = 0;
        int numGuessSingleGame = 0;

        System.out.printf("%nI'm thinking of a number between 1 and %d.%n", MAX_RANDOM_NUMBER);
        System.out.println("\tCan you guess what it is?");

        // evaluates user guesses against random number and gives hints
        while(userGuess != randomNumber)
        {
            userGuess = input.nextInt();
            numGuessSingleGame++;
            if (userGuess < randomNumber)
            {
                System.out.println("Not quite! My number is larger than that.");
                System.out.println("Guess again!");
            }
            else if (userGuess > randomNumber)
            {
                System.out.println("Nope, my number is smaller than that.");
                System.out.println("Guess again!");
            }
            else
            {
                System.out.println("Yay, you guessed my number!");
            }
        }
        System.out.printf("You got it right in %d tries.%n%n", numGuessSingleGame);
        return numGuessSingleGame;
    } // end of gamePlay method

    // inputYesOrNo method, returns user's desire to continue gameplay as a boolean
    public static boolean inputYesOrNo(Scanner input)
    {
        char userResponse;
        System.out.printf("%nI'm ready to pick a new number!%n%n");

        do
        {
            System.out.println("Would you like to continue?");
            userResponse = input.next().charAt(0);
            if (Character.toLowerCase(userResponse) == 'y')
            {
                return true;
            }
            else if (Character.toLowerCase(userResponse) == 'n')
            {
                return false;
            }
        } while (Character.toLowerCase(userResponse) != 'y' || Character.toLowerCase(userResponse) != 'n');
        return true;
    } // end of inputYesOrNo method

    // pickRandomNum method, returns random number as int
    public static int pickRandomNum(int MAX_RANDOM_NUMBER)
    {
        //Computer picks number randomly between 0 & MAX_RANDOM_NUMBER + 1
        // 1 is added to offset comp counting from 0
        Random rand = new Random();
        int randomNumber = rand.nextInt(MAX_RANDOM_NUMBER + 1);
        return randomNumber;
    } // end of pickRandomNumber method

    // result method, outputs users gameplay stats
    public static void result(int bestGuess, int totalGuess, int numGamesPlayed)
    {
        if (numGamesPlayed == 1)
        {
            System.out.printf("You played %d game,%n", numGamesPlayed);
        }
        else
        {
            System.out.printf("You played a total of %d games,%n", numGamesPlayed);
        }
        System.out.printf("with a total of %d guesses"
                + "%nand an average of %d guesses per game.%n", totalGuess, totalGuess/numGamesPlayed);
        System.out.printf("Your best game had only %d guesses!%n", bestGuess);
    } // end of result method

} // end of class HHGuessingGame