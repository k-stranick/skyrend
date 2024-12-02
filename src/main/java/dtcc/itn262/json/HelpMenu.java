package dtcc.itn262.json;

import com.google.gson.Gson;
import dtcc.itn262.utilities.gamecore.GameLogger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class HelpMenu {
	private String welcomeMessage;
	private Map<String, CommandDetails> combatCommands;
	private Map<String, CommandDetails> mazeAndGeneralCommands;
	private Map<String, CommandDetails> movementCommands;
	private Map<String, String> additionalInfo;


	// Display Help Menu Method
	public void displayHelpMenu() {
		System.out.println("===== HELP MENU =====");
		System.out.println(welcomeMessage);
		System.out.println();

		System.out.println("===== Combat Commands =====");
		combatCommands.forEach((key, command) -> {
			System.out.println(command.getCommand() + " - " + command.getDescription());
			System.out.println("   Usage: " + command.getUsage());
			System.out.println();
		});

		System.out.println("===== Maze and General Commands =====");
		mazeAndGeneralCommands.forEach((key, command) -> {
			System.out.println(command.getCommand() + " - " + command.getDescription());
			System.out.println("   Usage: " + command.getUsage());
			System.out.println();
		});

		System.out.println("===== Movement Commands =====");
		movementCommands.forEach((key, command) -> {
			System.out.println(command.getCommand() + " - " + command.getDescription());
			System.out.println("   Usage: " + command.getUsage());
			System.out.println();
		});

		System.out.println("===== Additional Info =====");
		additionalInfo.forEach((key, info) -> {
			System.out.println(info);
		});

		System.out.println("===== END OF HELP MENU =====");
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
		HelpMenu helpMenu = loadHelpMenu();
		if (helpMenu != null) {
			helpMenu.displayHelpMenu(); // Display the help menu when requested
		} else {
			System.out.println("Help menu not available.");
		}
	}
}

