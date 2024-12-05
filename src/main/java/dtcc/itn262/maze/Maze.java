package dtcc.itn262.maze;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.items.ItemManagement;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.boss.GhostCodeManifested;
import dtcc.itn262.monster.boss.Gilgamesh;
import dtcc.itn262.monster.boss.TheArchitect;
import dtcc.itn262.monster.genericmonsters.ArcTechSoldier;
import dtcc.itn262.monster.genericmonsters.CorruptedAutomaton;
import dtcc.itn262.monster.genericmonsters.CyberHound;
import dtcc.itn262.monster.genericmonsters.SteelDevourer;
import dtcc.itn262.monster.hiddenbosses.TheCipher;
import dtcc.itn262.utilities.display.SceneManager;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.Command;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.utilities.gamecore.GameLogger;
import dtcc.itn262.utilities.input.Validation;

import java.util.*;

public class Maze {
	private static Maze instance;
	private final Room[][] map;
	private final Set<String> uniqueVisitedRooms;
	private final List<String> moveHistory = new ArrayList<>(); // tracks all moves
	private final Player player; // change this if I add more characters to the game
	private final SceneManager sceneManager;
	private final Random random = new Random();
	private final ItemManagement itemManagement;
	private Map<Integer, int[]> roomIndexToPosition;

	//constructor
	private Maze(Player player) {
		this.map = MazeLoader.loadMazeFromJson("src/main/java/dtcc/itn262/maze/maze.json");
		this.itemManagement = new ItemManagement();
		this.player = player;
		this.uniqueVisitedRooms = new HashSet<>();  //If a player visits the same room multiple times, the HashSet will only store that room once.
		this.sceneManager = SceneManager.getInstance();
		initializeRoomIndexMapping();
		map[player.getPlayerRow()][player.getPlayerCol()].visitRoom(this.uniqueVisitedRooms);  // Mark the starting room
		// as visited do i need this??
		TextDisplayUtility.showCurrentRoom(map, player); // <-does this need to be here?
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

	private void initializeRoomIndexMapping() {
		roomIndexToPosition = new HashMap<>();
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				Room room = map[row][col];
				if (room != null) {
					roomIndexToPosition.put(room.getRoomIndex(), new int[]{row, col});
				}
			}
		}
	}

	public void move(Command direction) {
		if (direction == null) {
			System.out.println("Invalid direction. Use 'north', 'south', 'east', 'west'.");
			return;
		}

		Room currentRoom = getCurrentRoom();
		int nextRoomIndex = -1;

		switch (direction) {
			case NORTH:
				nextRoomIndex = currentRoom.getN();
				break;
			case SOUTH:
				nextRoomIndex = currentRoom.getS();
				break;
			case EAST:
				nextRoomIndex = currentRoom.getE();
				break;
			case WEST:
				nextRoomIndex = currentRoom.getW();
				break;
			default:
				System.out.println("Invalid direction.");
				return;
		}

		if (nextRoomIndex == -1) {
			System.out.println("Cannot move " + direction + " from here");
			return;
		}

		int[] position = roomIndexToPosition.get(nextRoomIndex);
		if (position == null) {
			System.out.println("You can't move in that direction. INVALID ROOM");
			return;
		}

		int newRow = position[0];
		int newCol = position[1];

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
		TextDisplayUtility.showCurrentRoom(map, player); // <-- test this without
		if (checkNullRoom(newRoom)) {
			return;
		}

		try {
			if (newRoom.isSpecial() && !newRoom.isSpecialEventTriggered()) { // Check if the room is special and if the event has not been triggered
				sceneManager.displayScene(newRoom.getSceneIndex());
				triggerSpecialEvent(newRoom);
				newRoom.setSpecialEventTriggered(true); // Mark the event as triggered
			} else {
				triggerRandomEncounter();
			}
			newRoom.visitRoom(uniqueVisitedRooms);
			displayMap();
			updateMoveHistory(newRoom);

		} catch (ArrayIndexOutOfBoundsException e) {
			GameLogger.logInfo(e.getMessage());
		} catch (NullPointerException e) {
			GameLogger.logError("Critical Error " + e.getMessage());
		}
	}

	private void updateMoveHistory(Room room) {
		moveHistory.add(room.getName()); // Add the move to the history

	}

	private void triggerRandomEncounter() {
		int chance = random.nextInt(100); // Generate a random number between 0 and 99
		if (chance <= 20) { // 20% chance for an encounter //TODO 1

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
					System.out.print("[=]  ");  // Show unvisited rooms
				}
			}
			System.out.println();  // Newline after each row
		}
	}  // TODO move this outside of this class to display class

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
			case "Sky Bridge":
				monster = new Gilgamesh();
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "Final Frontier":
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
			case "Fractured Nexus":
				monster = new TheCipher();
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			default:
				break;
		}
	}

	private boolean checkNullRoom(Room currentRoom) {
		if (currentRoom == null) {
			System.out.println("This space is empty. You can't visit it.");
			return true;
		}
		return false;
	}

	private double chanceToFindItem() {
		return random.nextDouble();  // 20% chance //TODO
	}

	public void searchRoom(Room room) { //
		if (room.hasBeenSearched()) {
			System.out.println("You've already searched this room.");
			return;
		}

		room.setHasBeenSearched(true);

		// random item generator from a predefined list and adds it to the player's inventory
		if (chanceToFindItem() <= .2) {  // upgraded from if/else to enhanced switch
			System.out.println("You didn't find anything.");
			return;
		}
		HealingItems item = itemManagement.pickRandomItem(itemManagement.getUsableItems());
		try {
			player.addItemToPlayerInventory(item);
			System.out.println("You found a " + item.getName());
		} catch (ClassCastException e) {
			System.out.println("Found item is not a HealingItem.");
		}
	}

	public Room getCurrentRoom() {
		return map[player.getPlayerRow()][player.getPlayerCol()];
	}

	public void restoreState(Set<String> uniqueVisitedRooms, List<String> moveHistory) {
		// Clear and restore the uniqueVisitedRooms set
		this.uniqueVisitedRooms.clear();
		this.uniqueVisitedRooms.addAll(uniqueVisitedRooms);

		// Clear and restore the moveHistory list
		this.moveHistory.clear();
		this.moveHistory.addAll(moveHistory);

		// Mark rooms in the map as visited based on uniqueVisitedRooms
		for (Room[] row : map) {
			for (Room room : row) {
				if (room != null && uniqueVisitedRooms.contains(room.getName())) {
					room.setVisited(true); // Mark room as visited
					if (room.isSpecial()) {
						room.setSpecialEventTriggered(true); // Mark special events as triggered
					}
				}
			}
		}
	}
}


