package dtcc.itn262.gameutilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private final Scanner scanner = new Scanner(System.in);

    // these are the integer methods
    private int getNextInt() {

        while (true) {

            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // clears newline character left after nextInt() will do this in overloaded methods

                return input;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }

        }
    }


    private int getNextInt(int taskListSize) {
        int input = 0; // Initialize choice with an invalid value
        while (true) {

            try {
                input = getNextInt();

                if (Validation.isValidTask(input, taskListSize)) {

                    return input;

                } else {
                    System.out.println("Invalid choice! Please select a task ID between 1 and " + taskListSize + ".");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid task ID.");
                scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }

        }
    }


    private int getNextInt(int minVal, int maxVal) {
        int input = 0; // Initialize input with an invalid value

        while (true) {

            try {
                input = getNextInt(); // calls simpler getNextInt() method to get the next integer input from the user
                if (Validation.isValidMenuChoice(minVal, maxVal, input)) {

                    return input;

                } else {
                    System.out.println("Invalid choice! Please select a task ID between 1 and " + maxVal + ".");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid task ID.");
                scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }

        }
    }


    public int getNextInt(String message, int minVal, int maxVal) {
        System.out.println(message);
        return getNextInt(minVal, maxVal);
    }


    public int getNextInt(String message) {
        System.out.println(message);
        return getNextInt();
    }


    public int getNextInt(String message, int taskListSize) {
        System.out.println(message);
        return getNextInt(taskListSize);
    }

    // These are the string methods
    public String getNextLine() { // Get the next line of input from the user
        return scanner.nextLine().trim();
    }

    public String getNextLine(String message) {
        System.out.println(message);
        return getNextLine();
    }


    public String getDate() {
        String date = getNextLine("Enter Task Due Date (YYYY-MM-DD): ");

        while (true) {

            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return date;
            } else {
                System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
                date = scanner.nextLine();  // Get the next line of input from the user
            }

        }
    }


    public void closeScanner() {
        scanner.close();
    }


}
