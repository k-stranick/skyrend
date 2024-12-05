package dtcc.itn262.utilities.input;

import dtcc.itn262.character.Player;
import dtcc.itn262.maze.Maze;
import dtcc.itn262.maze.Room;
import dtcc.itn262.monster.Monster;

import java.util.List;

public class Validation { // this will be a class of static methods

	private Validation() { // private constructor to prevent instantiation
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

			return name;
		}
	}

	public static boolean isRoomValid(int newRow, int newCol, Room[][] map) {
		return newRow >= 0 && newRow < map.length &&
				newCol >= 0 && newCol < map[newRow].length && map[newRow][newCol] != null;
	}

/*	public static boolean checkWinCondition(Maze maze) {
		// Logic for checking if the player has won (e.g., visited all special rooms)
		return maze.getUniqueVisitedRooms().size() >= maze.getRequiredVisitedRooms();
	}*/

	public static boolean checkLoseCondition(Player player) {
		// Logic for checking if the player has lost (e.g., player's health is 0)
		return !player.isAlive();
	}

	public static boolean keepBattleGoing(boolean battleHasEnded, Player player, Monster monster) {
		return !battleHasEnded && player.isAlive() && monster.isAlive();
	}

	public static boolean isBattleOver(Player player, List<Monster> combatMonsters) {
		return !player.isAlive() || combatMonsters.isEmpty();
	}

}
