package dtcc.itn262.utilities.gamecore;

import com.google.gson.Gson;
import dtcc.itn262.character.Player;
import dtcc.itn262.json.HelpMenu;
import dtcc.itn262.maze.Maze;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.input.Validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private final HelpMenu helpMenu;

	public Game() {
		this.helpMenu = loadHelpMenu();
		showStartMenu();
	}

	private void showStartMenu() {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {
			displayAsciiArt("src/main/java/dtcc/itn262/assets/logo.txt");  // Display the game logo
			System.out.println("===== START MENU =====");
			System.out.println("1. Start New Game");
			System.out.println("2. Load Game");
			System.out.println("3. View Help");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");

			String choice = scanner.nextLine().trim();

			switch (choice) {
				case "1":
					startGame();  // Start a new game
					running = false;  // Exit the menu after starting the game
					break;
				case "2":
					loadGame();  // Load a saved game (optional)
					break;
				case "3":
					displayHelp();  // Show the help menu
					break;
				case "4":
					System.out.println("Thank you for playing! Exiting...");
					running = false;  // Exit the game
					break;
				default:
					System.out.println("Invalid choice, please try again.");
					break;
			}
		}
	}

	private void loadGame() {
		System.out.println("Loading game...NOT I will figure this out at some poiint");  // Placeholder for loading a saved game
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
				value = input.nextLine().trim().toLowerCase(); // Read and trim player input
				handleInput(value, m);
				if(value.equalsIgnoreCase("exit")) {
					cont = false;
				}
				else if (Validation.checkWinCondition(m)) { // Check for win or lose conditions
					System.out.println("Congratulations! You've won the game!");
					cont = false;  // End the game loop
				} else if (Validation.checkLoseCondition(player)) {
					System.out.println("Game Over! You have lost the game.");
					cont = false;  // End the game loop
				}else {
					// Continue the game loop
				}
			} while (cont);
		}catch (Exception e) {
			e.printStackTrace();
			GameLogger.logError("An error occurred: " + e.getMessage());
		}
		System.out.println("Thank You for playing!");
		System.exit(0);

	}


	private void handleInput(String value, Maze m) {
		Command command = Command.fromString(value);
		if (command == null) {
			GameLogger.logWarning("Invalid command. Please try again.");
			return; // Return true if the command is invalid and continue the loop
		}
		switch (command) {
			case EXIT:
				System.out.println("Thank you for playing!");
				System.exit(0);
				return;
			case MAP:
				m.displayMap();
				break;
			case PROGRESS:
				TextDisplayUtility.showProgress(m.getUniqueVisitedRooms());
				break;
			case HELP:
				displayHelp();
				break;
			case HISTORY:
				TextDisplayUtility.showMoveHistory(m.getMoveHistory());
				break;
			case EAST, NORTH, SOUTH, WEST:
				m. move(command);
				break;
			default:
				GameLogger.logWarning("Invalid command. Please try again.");
		}
	}


	private HelpMenu loadHelpMenu() {
		Gson gson = new Gson();
		HelpMenu helpMenu = new HelpMenu();

		try (FileReader reader = new FileReader("src/main/java/dtcc/itn262/json/help_menu.json")) {
			helpMenu = gson.fromJson(reader, HelpMenu.class); // Deserialize JSON into HelpMenu class
		} catch (IOException e) {
			e.printStackTrace();
		}

		return helpMenu;
	}

	public void displayHelp() {
		if (helpMenu != null) {
			helpMenu.displayHelpMenu(); // Display the help menu when requested
		} else {
			System.out.println("Help menu not available.");
		}
	}
	// Method to display ASCII art
	public static void displayAsciiArt(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
