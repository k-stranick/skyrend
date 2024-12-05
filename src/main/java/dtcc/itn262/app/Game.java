package dtcc.itn262.app;

import dtcc.itn262.SaveLoad.GameSaver;
import dtcc.itn262.SaveLoad.GameState;
import dtcc.itn262.character.Player;
import dtcc.itn262.combat.PlayerActions;
import dtcc.itn262.items.Item;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;
import dtcc.itn262.helpmenu.HelpMenu;
import dtcc.itn262.maze.Maze;
import dtcc.itn262.maze.Room;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.Command;
import dtcc.itn262.utilities.gamecore.GameLogger;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;
import dtcc.itn262.utilities.soundandmusic.Music;

import java.util.Map;
import java.util.Scanner;

public class Game {
	private Player player;
	private PlayerActions playerActions;
	private final HelpMenu helpMenu = new HelpMenu();

	public Game() {
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
					Music.stopAllSounds();
					startGame();  // Start a new game
					running = false;  // Exit the menu after starting the game //TODO WHY IS THIS HERE
					break;
				case "2":
					loadGame();  // Load a saved game (optional)
					break;
				case "3":
					helpMenu.displayHelp();  // Show the help menu
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
		GameState gameState = GameSaver.loadGame("src/main/java/dtcc/itn262/SaveLoad/game_state.json");

		if (gameState != null) {
			player = gameState.getPlayer();
			playerActions = new PlayerActions(player); // Initialize PlayerActions for non-combat
			Maze maze = Maze.getInstance(player); // Use the same Player instance
			maze.restoreState(gameState.getUniqueVisitedRooms(), gameState.getMoveHistory());
			System.out.println("Game loaded successfully!");
			System.out.println("Welcome back, " + player.getHeroName() + "!");
			maze.displayMap();
			startGame(); // Continue the game
		} else {
			System.err.println("Failed to load the game.");
		}
	}

	private void startGame() {
		try (Scanner input = new Scanner(System.in)) {
			if (player == null) { // Create a new Player if one does not already exist
				System.out.print("Enter your hero's name: ");
				String playerName = input.nextLine().trim();
				player = Player.createPlayer(playerName, 0, 0); // Create and assign the Player
				System.out.println(player);
				System.out.println("Welcome, " + player.getHeroName() + "!");
			} else {
				System.out.println("Resuming game for " + player.getHeroName() + "!");
			}
			playerActions = new PlayerActions(player); // Initialize PlayerActions for non-combat
			Maze maze = Maze.getInstance(player); // Use the same Player instance
			boolean continueGame = true;
			Music.playBackgroundMusic("src/main/java/dtcc/itn262/utilities/soundandmusic/soundfiles/over_world_music.wav");

			do {
				System.out.print("?: ");
				String value = input.nextLine().trim().toLowerCase();
				inGameCommands(value, maze);

				if (value.equalsIgnoreCase("exit")) {
					continueGame = false;
				/*} else if (Validation.checkWinCondition(maze)) {
					System.out.println("Congratulations! You've won the game!");
					continueGame = false;*/
				} else if (Validation.checkLoseCondition(player)) {
					System.out.println("Game Over! You have lost the game.");
					continueGame = false;
				}
			} while (continueGame);
		} catch (Exception e) {
			GameLogger.logError("An error occurred: " + e.getMessage());
		}
		System.out.println("Thank you for playing!");
		Music.stopAllSounds();
		System.exit(0);
	}

	private void inGameCommands(String value, Maze m) {
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
			case PROGRESS: //TODO track progress for hidden boss?
				TextDisplayUtility.showProgress(m.getUniqueVisitedRooms());
				break;
			case HELP:
				helpMenu.displayHelp();
				break;
			case HISTORY:
				TextDisplayUtility.showMoveHistory(m.getMoveHistory());
				break;
			case SEARCH:
				Room currentRoom = m.getCurrentRoom(); // Get the current room from the Maze
				m.searchRoom(currentRoom);
				break;
			case INVENTORY:
				handleInventory();
				break;
			case EAST, NORTH, SOUTH, WEST:
				m.move(command);
				break;
			case SAVE:
				// Create and save the game state
				GameState gameState = new GameState(
						player,
						Maze.getInstance(player).getUniqueVisitedRooms(),
						Maze.getInstance(player).getMoveHistory()
				);
				GameSaver.saveGame(gameState, "src/main/java/dtcc/itn262/SaveLoad/game_state.json");
				break;
			case STATS:
				System.out.println(player);
				break;
			default:
				GameLogger.logWarning("Invalid command. Please try again.");
		}
	}

	private void handleInventory() {
		if (!TextDisplayUtility.displayInventory(player)) {
			return;
		}

		while (true) {
			int index = UserInput.getPlayerChoice("\nEnter the index of the item you want to equip or use (-1 to exit): ");

			if (index == -1) {
				System.out.println("Returning to Skyrend...");
				break; // Exit the inventory menu
			}

			Map<Integer, Item> indexToItemMap = player.getInventoryIndexMap();

			if (indexToItemMap.containsKey(index)) {
				Item selectedItem = indexToItemMap.get(index);

				// Check item type and perform the corresponding action
				switch (selectedItem) {
					case Weapon weapon -> playerActions.equipWeapon(weapon);
					case Armor armor -> playerActions.equipArmor(armor);
					case HealingItems healingItems -> playerActions.useItem(healingItems);
					case null, default -> System.out.println("Invalid item type.");
				}
			} else {
				System.out.println("Invalid index.");
			}

			// After action, display inventory again
			TextDisplayUtility.displayInventory(player);

		}
	}
}
