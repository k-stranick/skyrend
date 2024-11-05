package dtcc.itn262.json;

import java.util.Map;

public class HelpMenu {
	private String welcomeMessage;
	private Map<String, CommandDetails> combatCommands;
	private Map<String, CommandDetails> mazeAndGeneralCommands;
	private Map<String, CommandDetails> movementCommands;
	private Map<String, String> additionalInfo;

	// Getters and Setters
	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	public Map<String, CommandDetails> getCombatCommands() {
		return combatCommands;
	}

	public void setCombatCommands(Map<String, CommandDetails> combatCommands) {
		this.combatCommands = combatCommands;
	}

	public Map<String, CommandDetails> getMazeAndGeneralCommands() {
		return mazeAndGeneralCommands;
	}

	public void setMazeAndGeneralCommands(Map<String, CommandDetails> mazeAndGeneralCommands) {
		this.mazeAndGeneralCommands = mazeAndGeneralCommands;
	}

	public Map<String, CommandDetails> getMovementCommands() {
		return movementCommands;
	}

	public void setMovementCommands(Map<String, CommandDetails> movementCommands) {
		this.movementCommands = movementCommands;
	}

	public Map<String, String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

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


}

