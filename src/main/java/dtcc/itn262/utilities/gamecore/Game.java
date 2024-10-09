package dtcc.itn262.utilities.gamecore;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.input.Validation;
import java.util.Scanner;

public class Game {

	public Game() {
		startGame();
	}


	private void startGame() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter your hero's name: "); // Prompt for the player's name first
			String playerName = input.nextLine().trim();  // Read and trim player input
			Player player = Player.createPlayer(playerName, 0, 0);  // create the player with the provided name
			System.out.println("Welcome, " + player.getHero() + "!");
			System.out.println(player); //This will invoke player.toString() and print the stats after the name has been entered
			Maze m = Maze.getInstance(player); // ensures I am using the same instance of Maze by using a Singleton pattern
			String value;
			boolean cont = true;

			do {
				System.out.print("?: "); // Prompt for the player's input
				value = input.nextLine().trim(); // Read and trim player input
				handleInput(value, m);

				if (Validation.checkWinCondition(m)) { // Check for win or lose conditions
					System.out.println("Congratulations! You've won the game!");
					cont = false;  // End the game loop
				} else if (!player.isAlive()) {
					System.out.println("Game Over! You have lost the game.");
					cont = false;  // End the game loop
				}
			} while (cont);
		}
		System.out.println("Thank You for playing!");
		System.exit(0);

	}

	private void handleInput(String value, Maze m) {
		Command command = parseCommand(value);
		if (command == null) {
			System.out.println("Invalid command. Please try again.");
			return; // Return true if the command is invalid and continue the loop
		}
		switch (command) {
			case EXIT:
				System.out.println("Thank you for playing!");
				return;
			case MAP:
				m.displayMap();
				break;
			case PROGRESS:
				TextDisplayUtility.showProgress(m.getUniqueVisitedRooms());
				break;
			case HELP:
				System.out.println("Commands: map, progress, help, exit, north, south, east, west");
				break;
			case HISTORY:
				TextDisplayUtility.showMoveHistory(m.getMoveHistory());
				break;
			case EAST, NORTH, SOUTH, WEST:
				m.move(value);
				break;
			default:
				System.out.println("Invalid command. Please try again.");
		}
	}


	private Command parseCommand(String value) {
		try {
			return Command.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;  // Return null if the command is invalid
		}
	}

}
