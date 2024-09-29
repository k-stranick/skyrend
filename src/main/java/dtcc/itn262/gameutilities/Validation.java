package dtcc.itn262.gameutilities;

import java.io.File;

public class Validation { // this will be a class of static methods

    private Validation() { // private constructor to prevent instantiation
    }

    // validates the task id by checking if it is within the range of the task list size
    public static boolean isValidTask(int taskId, int taskListSize) {
        return taskId > 0 && taskId <= taskListSize;
    }

    // validates the menu choice by checking if it is within the range of the menu options which are set in Constants class
    public static boolean isValidMenuChoice(int minVal, int maxVal, int choice) {
        return choice >= minVal && choice <= maxVal;
    }
    // Method for validating the player name
    public static String validateName(String name) {
        if (name.isEmpty()) {
            return "Hero";  // Default name
        } else if (name.length() < 2 || name.length() > 20) {
            System.out.println("Name must be between 2 and 20 characters. Defaulting to 'Hero'.");
            return "Hero";
        } else if (!name.matches("[a-zA-Z]+")) {
            System.out.println("Name contains invalid characters. Only letters are allowed. Defaulting to 'Hero'.");
            return "Hero";
        } else {
            // Capitalize first letter and lower the rest
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }

}
