package dtcc.itn262.dungeon;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.utilities.display.SceneManager;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dtcc.itn262.utilities.input.Validation.checkWinCondition;


public class Maze {
	private static Maze instance;
	private final Room[][] map;
	private final int requiredVisitedRooms;
	private final Set<String> uniqueVisitedRooms;
	private final List<String> moveHistory = new ArrayList<>(); // tracks all moves
	private final Player player; // change this if I add more characters to the game
	private final SceneManager sceneManager;

	//constructor
	private Maze(Player player) {
		this.map = initializeMap();
		this.player = player;
		this.requiredVisitedRooms = countSpecialRooms();
		this.uniqueVisitedRooms = new HashSet<>();  //If a player visits the same room multiple times, the HashSet will only store that room once.
		this.sceneManager = SceneManager.getInstance();
		visitRoom(map[player.getPlayerRow()][player.getPlayerCol()]);  // Mark the starting room as visited
		TextDisplayUtility.showCurrentRoom(map, player);
	}


	public static Maze getInstance(Player player) {
		if (instance == null) {
			instance = new Maze(player);
		}
		return instance;
	}


	public List<String> getMoveHistory() {
		return moveHistory;
	}


	public Set<String> getUniqueVisitedRooms() {
		return uniqueVisitedRooms;
	}


	public void move(String direction) {
		int newRow = player.getPlayerRow();
		int newCol = player.getPlayerCol();
		Room currentRoom = map[newRow][newCol];
		switch (direction.toLowerCase()) {
			case "north":
				if (!currentRoom.hasNorthExit()) {
					System.out.println("You can't move in that direction.");
					return;
				}
				newRow--;
				break;
			case "south":
				if (!currentRoom.hasSouthExit()) {
					System.out.println("You can't move south.");
					return;
				}
				newRow++;
				break;
			case "east":
				if (!currentRoom.hasEastExit()) {
					System.out.println("You can't move east.");
					return;
				}
				newCol++;
				break;
			case "west":
				if (!currentRoom.hasWestExit()) {
					System.out.println("You can't move west.");
					return;
				}
				newCol--;
				break;
			default:
				System.out.println("Invalid direction. Use 'north', 'south', 'east', 'west'.");
				return;
		}
		positionValidation(newRow, newCol);
	}


	private void positionValidation(int newRow, int newCol) {
		try {
			if (Validation.isRoomValid(newRow, newCol, map)) { // Check if the new position is valid
				player.moveTo(newRow, newCol); // Move the player to the new room
				Room newRoom = map[newRow][newCol];
				processRoomAfterMove(newRoom);
			} else {
				System.out.println("You can't move in that direction.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You can't move in that direction.");
		} catch (NullPointerException e) {
			System.out.println("Room is not initialized");
		}
	}


	private void processRoomAfterMove(Room newRoom) {
		try {
			if (checkNullRoom(newRoom)) {
				return;
			}
			visitRoom(newRoom);
			TextDisplayUtility.showCurrentRoom(map, player);
			sceneManager.displayScene(newRoom.getSceneIndex());

			triggerSpecialEvent(newRoom);
			//displayMap();
			checkEscapeCondition();
			updateMoveHistory(newRoom);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You can't move in that direction.");
		} catch (NullPointerException e) {
			System.out.println("Room is not initialized");
		}
	}


	private void updateMoveHistory(Room room) {
		moveHistory.add("Moved to " + room.getName()); // Add the move to the history

	}


	public void visitRoom(Room currentRoom) {
		try {
			if (!currentRoom.isVisited()) {
				currentRoom.setVisited(true);  // Mark room as visited
				if (currentRoom.isSpecial()) {
					uniqueVisitedRooms.add(currentRoom.getName());  // Track special rooms
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Room is not initialized");
		}
	}


	public void displayMap() {    // Display the map with the player's current position
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				Room room = this.map[row][col];  // Get the room from the map

				if (room == null) {
					System.out.print("     ");  // Empty spaces for null rooms
				} else if (row == player.getPlayerRow() && col == player.getPlayerCol()) {
					System.out.print("[P]  ");  // Show player's position
				} else if (room.isVisited()) {
					System.out.print("[ ]  ");  // Show visited rooms
				} else {
					System.out.print("[X]  ");  // Show unvisited rooms
				}
			}
			System.out.println();  // Newline after each row
		}
	}


	private void checkEscapeCondition() {
		if (checkWinCondition(this)) { // Check if the player has visited enough rooms to escape
			System.out.println("You have visited enough rooms to escape the city!");
			// Ask the player if they want to continue playing
			if (!UserInput.askToContinue()) {
				System.exit(0); // Only exit if they say "no"
			} else {
				System.out.println("Continuing the game... You are free to explore more.");
			}
		}
	}


	// Define a method to trigger special events
	private void triggerSpecialEvent(Room room) { //TODO add more
		switch (room.getName()) {
			case "Ghost Terminal":
				System.out.println("You encounter ArcTech enforcers! Prepare for battle.");
				Monster monster = new Monster("ArcTech Enforcer");
				CombatLogic combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "Aether Nexus":
				System.out.println("The final confrontation with the Council of Circuits awaits...");
				break;
			default:
				System.out.println("logic for random encounter here .");
		}
	}


	// Method to count special rooms
	private int countSpecialRooms() {
		int count = 0;
		for (Room[] row : map) {
			for (Room room : row) {
				if (room != null && room.isSpecial()) {
					count++;
				}
			}
		}
		return count;
	}


	private Room[][] initializeMap() {
		return new Room[][]{
				// Row 1
				{
						new Room(new RoomConfiguration("The Outskirts", "A lawless area on the edge of the city.", false, false, true, false, false, Constants.NO_SCENE)), // Room 0
						new Room(new RoomConfiguration("Neon Corridor", "A bustling district filled with traders.", false, false, true, true, false, Constants.NO_SCENE)),  // Room 1
						new Room(new RoomConfiguration("Fusion Park", "A public park where nature and tech blend.", false, false, true, true, false, Constants.NO_SCENE)),   // Room 2
						new Room(new RoomConfiguration("Cyber Alley", "A narrow alley with questionable tech.", false, true, false, true, false, Constants.NO_SCENE))  // Room 3
				},
				// Row 2
				{
						null, null, null,
						new Room(new RoomConfiguration("DataFall Plaza", "A bustling square surrounded by neon billboards.", true, true, true, true, false, Constants.NO_SCENE)), // Room 4
						new Room(new RoomConfiguration("Power Conduit", "A maintenance area for energy conduits.", true, true, true, true, false, Constants.NO_SCENE))  // Room 5
				},
				// Row 3
				{
						null, null, null, null,
						new Room(new RoomConfiguration("StormW3ll Station", "An old train station repurposed as a meeting ground.", true, true, true, false, false, Constants.NO_SCENE)),  // Room 6
						new Room(new RoomConfiguration("The Drift Way", "A dangerous floating sky rail.", false, false, true, true, false, Constants.NO_SCENE)),  // Room 7
						new Room(new RoomConfiguration("Rune Street Markets", "A chaotic black market.", false, true, false, true, false, Constants.NO_SCENE)) // Room 8
				},
				// Row 4
				{
						null,
						new Room(new RoomConfiguration("Arcane Synth Bay", "A fusion lab for cybernetic enhancements.", false, false, true, false, true, Constants.SCENE_1)), // Room 9
						new Room(new RoomConfiguration("Ghost Terminal", "A server room where Ghost Code emerged.", false, true, true, true, false, Constants.NO_SCENE)),  // Room 10
						new Room(new RoomConfiguration("Giga Tower", "A towering skyscraper.", false, false, true, true, false, Constants.NO_SCENE)),  // Room 11
						new Room(new RoomConfiguration("Circuit Yard", "A spacious yard used by traders and scavengers.", true, false, false, true, false, Constants.NO_SCENE)),  // Room 12
						null,
						new Room(new RoomConfiguration("Abandoned Tech Labs", "A research facility overrun with AI.", true, false, false, false, true, Constants.SCENE_2)),  // Room 13
				},
				// Row 5
				{
						null, null,
						new Room(new RoomConfiguration("Sky bridge", "A high-altitude bridge connecting sectors.", true, true, false, false, true, Constants.SCENE_3)),  // Room 14
						null,
						new Room(new RoomConfiguration("Aetheric Sanctum", "A hidden sanctum where the Aether flows.", false, true, false, false, true, Constants.SCENE_4)) // Room 15
				},
				// Row 6
				{
						null, null,
						new Room(new RoomConfiguration("Iron District", "An industrial zone filled with factories.", true, false, true, false, false, Constants.NO_SCENE)), // Room 16
						new Room(new RoomConfiguration("Scrapyard", "A graveyard of discarded machinery.", false, false, true, true, false, Constants.NO_SCENE)),  // Room 17
						new Room(new RoomConfiguration("Red Circuit Warehouse", "A storage facility for stolen tech.", true, true, false, true, false, Constants.NO_SCENE)), // Room 18
				},
				// Row 7
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Obsidian Relay", "A central hub of the AetherGrid.", true, true, true, false, false, Constants.NO_SCENE)),  // Room 19
						new Room(new RoomConfiguration("NullSpace Hub", "A digital realm where space and time distort.", false, false, false, true, true, Constants.SCENE_5))  // Room 20

				},
				// Row 8
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Aether Nexus", "The towering heart of Skyrend.", true, true, false, false, true, Constants.SCENE_6)),  // Room 21
				},
				// Row 9
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Echo Vault", "A hidden digital vault.", true, false, false, false, false, Constants.NO_SCENE))  // Room 22
				}
		};
	}


	public int getRequiredVisitedRooms() {
		return requiredVisitedRooms;
	}


	public boolean checkNullRoom(Room currentRoom) {
		if (currentRoom == null) {
			System.out.println("This space is empty. You can't visit it.");
			return true;
		}
		return false;
	}
/*
    public void search() {
        Random rand = new Random();
        int intSearch = rand.nextInt(10);
        if (intSearch > 7) {

            keyFound = true;
            System.out.println("You found the key!");
            moves.push("keyFound");

        } else {
            System.out.println("You did not find the key.");
            moves.push("no key found");
        }
    }*/


}


