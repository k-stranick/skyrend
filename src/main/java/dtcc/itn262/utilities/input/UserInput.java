package dtcc.itn262.utilities.input;

import java.util.Scanner;

public class UserInput {
    private static final Scanner s= new Scanner(System.in);

    private UserInput(){} // private constructor to prevent instantiation

    // Helper method to get player's input
    public static int getPlayerChoice(String message) {
        System.out.print(message);
        while (!s.hasNextInt()) { // this is logical should I use a try catch block?
            System.out.println("Invalid input. Please enter a number.");
            s.nextLine();  // Clear invalid input
        }
        int choice = s.nextInt();
        s.nextLine();  // Clear buffer
        return choice;
    }
}
