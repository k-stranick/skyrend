package dtcc.itn262.gameutilities;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;
import dtcc.itn262.dungeon.Room;
import dtcc.itn262.monster.MonsterAttributes;

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

    public static boolean isValidRoom(int newRow, int newCol, Room[][] map) {
        return newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length;
    }

    public static boolean move(Room currentRoom, String direction) {
        try {
            direction = direction.toLowerCase();  // character is a byte that represents a letter

            switch (direction) {
                case "north":
                    if (currentRoom.getN() != Constants.CANNOT_TRAVERSE) {
                        //moves.push("north");
                        return true;
                    } else {
                        System.out.println("You cannot go north from here!");
                        return false;
                    }
                case "south":
                    if (currentRoom.getS() != Constants.CANNOT_TRAVERSE) {
                        //moves.push("south");
                        return true;
                    } else {
                        System.out.println("You cannot go south from here!");
                        return false;
                    }
                case "east":
                    if (currentRoom.getE() != Constants.CANNOT_TRAVERSE) {
                        // moves.push("east");
                        return true;
                    } else {
                        System.out.println("You cannot go east from here!");
                        return false;
                    }
                case "west":
                    if (currentRoom.getW() != Constants.CANNOT_TRAVERSE) {
                        //moves.push("west");
                        return true;
                    } else {
                        System.out.println("You cannot go west from here!");
                        return false;
                    }
                default:
                    System.out.println("What did you mean? Invalid move use north, south, east, west");
                    return false;
            }
        } catch (Exception ex) {
            // you in business store it into an Error Log File
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public static boolean checkPostTurnConditions(Player player, MonsterAttributes monsterAttributes) {
        // Check if the player has lost
        if (Validation.checkLoseCondition(player)) {
            System.out.println("Monster wins! Game Over.");
            System.exit(0);  // Exit the game after losing
            return true;  // Break out of the fight
        }

        // Check if the monster is defeated
        if (monsterAttributes.getHealth() <= 0) {
            System.out.println("Player wins! The monster is defeated.");
            return true;  // Break out of the fight
        }

        return false;  // Continue the fight
    }
    protected static boolean checkWinCondition(Maze maze) {
        // Logic for checking if the player has won (e.g., visited all special rooms)
        return maze.getUniqueVisitedRooms().size() >= maze.getRequiredVisitedRooms();
    }

    protected static boolean checkLoseCondition(Player player) {
        // Logic for checking if the player has lost (e.g., health is 0)
        return !player.isAlive();
    }
}
