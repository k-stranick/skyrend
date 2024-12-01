package dtcc.itn262.utilities.gamecore;

import com.google.gson.Gson;
import dtcc.itn262.SaveLoad.GameSaver;
import dtcc.itn262.SaveLoad.GameState;
import dtcc.itn262.character.Player;
import dtcc.itn262.json.HelpMenu;
import dtcc.itn262.maze.Maze;
import dtcc.itn262.maze.Room;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.input.Validation;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private final HelpMenu helpMenu;
	private Player player;

	public Game() {
		this.helpMenu = loadHelpMenu();
		showStartMenu();
	}

	private void showStartMenu() {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {
			TextDisplayUtility.showMainMenu();  // Display the main menu

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
		// Load the game state from the file
		GameState gameState = GameSaver.loadGame("src/main/java/dtcc/itn262/SaveLoad/game_state.json");

		if (gameState != null) {
			// Load the player from the game state
			player = gameState.getPlayer();
			System.out.println("Game loaded successfully!");
			System.out.println(player); // Display the player's stats
			Maze m = Maze.getInstance(player); // Get the Maze instance
			m.displayMap(); // Display the map
			startGame(); // Start the game
		} else {
			System.out.println("Failed to load the game.");
		}
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


			do { //TODO CHANGE TO SWITCH
				System.out.print("?: "); // Prompt for the player's input
				value = input.nextLine().trim().toLowerCase(); // Read and trim player input
				handleInput(value, m);
				if (value.equalsIgnoreCase("exit")) {
					cont = false;
				} else if (Validation.checkWinCondition(m)) { // Check for win or lose conditions
					System.out.println("Congratulations! You've won the game!");
					cont = false;  // End the game loop
				} else if (Validation.checkLoseCondition(player)) {
					System.out.println("Game Over! You have lost the game.");
					cont = false;  // End the game loop
				} else {
					// Continue the game loop
				}
			} while (cont);
		} catch (Exception e) {

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
			case SEARCH:
				Room currentRoom = m.getCurrentRoom(); // Get the current room from the Maze
				m.searchRoom(currentRoom);
				break;
			case INVENTORY:
				m.displayInventory();
				break;
			case EAST, NORTH, SOUTH, WEST:
				m.move(command);
				break;
			case SAVE:
				// Create and save the game state
				GameState gameState = new GameState(player,  false); // Example: isBossDefeated is false
				GameSaver.saveGame(gameState, "src/main/java/dtcc/itn262/SaveLoad/game_state.json");
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
			GameLogger.logError("An error occurred while loading the help menu: " + e.getMessage());
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

}
