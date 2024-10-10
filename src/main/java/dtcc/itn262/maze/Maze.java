package dtcc.itn262.maze;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.monster.boss.GhostCodeManifested;
import dtcc.itn262.monster.boss.Gilgamesh;
import dtcc.itn262.monster.boss.TheArchitect;
import dtcc.itn262.monster.generic.*;
import dtcc.itn262.utilities.display.SceneManager;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.Command;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.utilities.gamecore.GameLogger;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;

import java.util.*;

import static dtcc.itn262.utilities.input.Validation.checkWinCondition;


public class Maze {
	private static Maze instance;
	private final Room[][] map;
	private final int requiredVisitedRooms;
	private final Set<String> uniqueVisitedRooms;
	private final List<String> moveHistory = new ArrayList<>(); // tracks all moves
	private final Player player; // change this if I add more characters to the game
	private final SceneManager sceneManager;
	private final Random random = new Random();

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


	public void move(Command direction) {
		int newRow = player.getPlayerRow();
		int newCol = player.getPlayerCol();
		switch (direction) {
			case NORTH:
				newRow--;break;
			case SOUTH:
				newRow++;break;
			case EAST:
				newCol++;break;
			case WEST:
				newCol--;break;
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
			GameLogger.logWarning("You can't move in that direction.");
		} catch (NullPointerException e) {
			GameLogger.logWarning(Constants.ROOM_ERROR + e.getMessage());
		}
	}


	private void processRoomAfterMove(Room newRoom) {
		TextDisplayUtility.showCurrentRoom(map, player);
		try {
			if (checkNullRoom(newRoom)) {
				return;
			} else if (newRoom.isSpecial() && !newRoom.isSpecialEventTriggered()) { // Check if the room is special and if the event has not been triggered
				sceneManager.displayScene(newRoom.getSceneIndex());
				triggerSpecialEvent(newRoom);
				newRoom.setSpecialEventTriggered(true); // Mark the event as triggered
			} else {
				triggerRandomEncounter();
			}
			visitRoom(newRoom);
			displayMap();
			checkEscapeCondition();
			updateMoveHistory(newRoom);

		} catch (ArrayIndexOutOfBoundsException e) {
			GameLogger.logInfo(e.getMessage());
		} catch (NullPointerException e) {
			GameLogger.logError("Critical Error " + e.getMessage());
		}
	}


	private void updateMoveHistory(Room room) {
		moveHistory.add("Moved to " + room.getName()); // Add the move to the history

	}


	private void triggerRandomEncounter() {
		int chance = random.nextInt(100); // Generate a random number between 0 and 99
		if (chance <= 20) { // 20% chance for an encounter
			System.out.println("A wild monster appears!");
			List<Monster> monsters = Arrays.asList(
					new ArcTechSoldier(),
					new CorruptedAutomaton(),
					new CyberHound(),
					new SteelDevourer()
			); // Add your custom monster instances here
			Monster randomMonster = monsters.get(random.nextInt(monsters.size())); // Select a random monster
			CombatLogic combat = new CombatLogic(player, randomMonster);
			combat.startFight();
		}
	}


	private void visitRoom(Room currentRoom) {
		try {
			if (!currentRoom.isVisited()) {
				currentRoom.setVisited(true);  // Mark room as visited
				if (currentRoom.isSpecial()) {
					uniqueVisitedRooms.add(currentRoom.getName());  // Track special rooms
				}
			}
		} catch (NullPointerException e) {
			GameLogger.logError(Constants.ROOM_ERROR + e.getMessage());
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


	// Define a method to trigger special events
	private void triggerSpecialEvent(Room room) { //TODO add more
		Monster monster;
		CombatLogic combat;
		switch (room.getName()) {
			case "Arcane Synth Bay": // set up for 3 rounds of fighting??
				System.out.println("some kind of logic here get a weapon?");

				break;
			case "Abandoned Tech Labs":
				System.out.println("some kind of logic here get a weapon?");

				break;
			case "Sky bridge":
				monster = new Gilgamesh();
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "Aetheric Sanctum":
				monster = new GhostCodeManifested();
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "NullSpace Hub":
				System.out.println("some kind of logic here get a weapon?");

				break;
			case "Aether Nexus":
				System.out.println("The final confrontation awaits...");
				monster = new TheArchitect();
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			default:
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


	public int getRequiredVisitedRooms() {
		return requiredVisitedRooms;
	}


	private boolean checkNullRoom(Room currentRoom) {
		if (currentRoom == null) {
			System.out.println("This space is empty. You can't visit it.");
			return true;
		}
		return false;
	}


	private Room[][] initializeMap() {
		return new Room[][]{
				// Row 1
				{
						new Room(new RoomConfiguration("The Outskirts", "A lawless area on the edge of the city.", -1, -1, 1, -1, false, Constants.NO_SCENE)), // Room 0
						new Room(new RoomConfiguration("Neon Corridor", "A bustling district filled with traders.", -1, -1, 0, 2, false, Constants.NO_SCENE)),  // Room 1
						new Room(new RoomConfiguration("Fusion Park", "A public park where nature and tech blend.", -1, -1, 1, 3, false, Constants.NO_SCENE)),   // Room 2
						new Room(new RoomConfiguration("Cyber Alley", "A narrow alley with questionable tech.", -1, 4, 3, -1, false, Constants.NO_SCENE))  // Room 3
				},
				// Row 2
				{
						null, null, null,
						new Room(new RoomConfiguration("DataFall Plaza", "A bustling square surrounded by neon billboards.", 3, -1, -1, 5, false, Constants.NO_SCENE)), // Room 4
						new Room(new RoomConfiguration("Power Conduit", "A maintenance area for energy conduits.", -1, 6, 4, -1, false, Constants.NO_SCENE))  // Room 5
				},
				// Row 3
				{
						null, null, null, null,
						new Room(new RoomConfiguration("StormW3ll Station", "An old train station repurposed as a meeting ground.", 5, 12, 7, -1, false, Constants.NO_SCENE)),  // Room 6
						new Room(new RoomConfiguration("The Drift Way", "A dangerous floating sky rail.", -1, -1, 6, 8, false, Constants.NO_SCENE)),  // Room 7
						new Room(new RoomConfiguration("Rune Street Markets", "A chaotic black market.", -1, 13, 7, -1, false, Constants.NO_SCENE)) // Room 8
				},
				// Row 4
				{
						null,
						new Room(new RoomConfiguration("Arcane Synth Bay", "A fusion lab for cybernetic enhancements.", -1, -1, 10, -1, true, Constants.SCENE_1)), // Room 9
						new Room(new RoomConfiguration("Ghost Terminal", "A server room where Ghost Code emerged.", -1, 14, 11, 9, false, Constants.NO_SCENE)),  // Room 10
						new Room(new RoomConfiguration("Giga Tower", "A towering skyscraper.", -1, -1, 12, 10, false, Constants.NO_SCENE)),  // Room 11
						new Room(new RoomConfiguration("Circuit Yard", "A spacious yard used by traders and scavengers.", 6, -1, -1, 11, false, Constants.NO_SCENE)),  // Room 12
						null,
						new Room(new RoomConfiguration("Abandoned Tech Labs", "A research facility overrun with AI.", 8, -1, -1, -1, true, Constants.SCENE_2)),  // Room 13
				},
				// Row 5
				{
						null, null,
						new Room(new RoomConfiguration("Sky bridge", "A high-altitude bridge connecting sectors.", 10, 16, -1, -1, true, Constants.SCENE_3)),  // Room 14
						null,
						new Room(new RoomConfiguration("Aetheric Sanctum", "A hidden sanctum where the Aether flows.", -1, 18, -1, -1, true, Constants.SCENE_4)) // Room 15
				},
				// Row 6
				{
						null, null,
						new Room(new RoomConfiguration("Iron District", "An industrial zone filled with factories.", 14, -1, 17, -1, false, Constants.NO_SCENE)), // Room 16
						new Room(new RoomConfiguration("Scrapyard", "A graveyard of discarded machinery.", -1, -1, 18, 16, false, Constants.NO_SCENE)),  // Room 17
						new Room(new RoomConfiguration("Red Circuit Warehouse", "A storage facility for stolen tech.", 15, 19, -1, 17, false, Constants.NO_SCENE)), // Room 18
				},
				// Row 7
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Obsidian Relay", "A central hub of the AetherGrid.", 18, 21, 20, -1, false, Constants.NO_SCENE)),  // Room 19
						new Room(new RoomConfiguration("NullSpace Hub", "A digital realm where space and time distort.", -1, -1, -1, 19, true, Constants.SCENE_5))  // Room 20

				},
				// Row 8
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Aether Nexus", "The towering heart of Skyrend.", 19, 22, -1, -1, true, Constants.SCENE_6)),  // Room 21
				},
				// Row 9
				{
						null, null, null, null,
						new Room(new RoomConfiguration("Echo Vault", "A hidden digital vault.", 21, -1, -1, -1, false, Constants.NO_SCENE))  // Room 22
				}
		};
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


