package dtcc.itn262.SaveLoad;

import java.util.List;

public class GameState {
	private Player player;
	private List<String> visitedRooms;
	private List<Monster> monsters;
	private GameStateInfo gameState;

	// Getters and setters (or use public fields for simplicity)
}

class Player {
	private String name;
	private int health;
	private List<String> inventory;
	private String currentPosition;

	// Getters and setters
}

class Monster {
	private String name;
	private int health;
	private boolean isDefeated;

	// Getters and setters
}

class GameStateInfo {
	private boolean isGhostCodeActive;
	private String aetherGridStatus;

	// Getters and setters
}