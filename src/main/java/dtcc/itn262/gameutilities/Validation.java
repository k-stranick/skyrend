package dtcc.itn262.gameutilities;

import java.io.File;

public class Validation { // this will be a class of static methods

    private Validation() { // private constructor to prevent instantiation
    }

/*
    // Check if the CSV file exists and is not a directory
    public static boolean csvExists() {
        File file = new File(Constants.FILENAME);
        return file.exists() && !file.isDirectory();
    }

*/

    // validates the task id by checking if it is within the range of the task list size
    public static boolean isValidTask(int taskId, int taskListSize) {
        return taskId > 0 && taskId <= taskListSize;
    }

    // validates the menu choice by checking if it is within the range of the menu options which are set in Constants class
    public static boolean isValidMenuChoice(int minVal, int maxVal, int choice) {
        return choice >= minVal && choice <= maxVal;
    }


    // Check if the task list is empty
   // public static boolean isTaskListEmpty(/*(TaskManagement taskManagement*/) {
/*
        if (taskManagement.getTaskList().isEmpty()) {
            System.out.println("No tasks available.");

            return true;
        }

        return false;*/
   // }


    // Check if the user wants to return to the main menu
/*    public static boolean shouldReturnToMainMenu(*//*InputHandler inputHandler*//*) {
*//*
        String pressEnter = inputHandler.getNextLine("Press 'ENTER' to select another task or type 'E' to return to main menu.");
        return pressEnter.equalsIgnoreCase("E");

*//*

    } return false;*/


}
