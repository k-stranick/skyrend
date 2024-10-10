package dtcc.itn262.utilities.input;

import java.util.Scanner;

public class UserInput {
    private static final Scanner s= new Scanner(System.in);


    private UserInput(){} // private constructor to prevent instantiation

    // Helper method to get player's input
    public static int getPlayerChoice() {
        while (!s.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            s.nextLine();  // Clear invalid input
        }
        int choice = s.nextInt();
        s.nextLine();  // Clear buffer
        return choice;
    }


    public static boolean askToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to continue the game? (yes/no)");
        String response = input.nextLine().trim().toLowerCase();

        if (response.equals("no")) {
            System.out.println("Thanks for playing! Goodbye.");
            return false;  // Return false to signal game over
        } else if (!response.equals("yes")) {
            System.out.println("Invalid input. Please type 'yes' or 'no'.");
            return askToContinue(); // Re-prompt if input is invalid
        }
        return true; // Return true to continue playing
    }


}
