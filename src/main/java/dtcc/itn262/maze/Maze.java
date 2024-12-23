package dtcc.itn262.maze;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.CombatLogic;
import dtcc.itn262.items.ItemManagement;
import dtcc.itn262.items.armor.PhantomCircuitArmor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Voidbreaker;
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
		// as visited
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
		if (!Validation.isRoomValid(newRow, newCol, map)) {
			System.out.println("Invalid room. Please try again.");
			return;
		}

		try {
			player.moveTo(newRow, newCol); // Move the player to the new room
			Room newRoom = map[newRow][newCol];
			processRoomAfterMove(newRoom);

		} catch (ArrayIndexOutOfBoundsException e) {
			GameLogger.logWarning("Error: " + e.getMessage());
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
			handleSpecialRoom(newRoom);
			newRoom.visitRoom(uniqueVisitedRooms);
			displayMap();
			updateMoveHistory(newRoom);
		} catch (ArrayIndexOutOfBoundsException e) {
			GameLogger.logInfo(e.getMessage());
		} catch (NullPointerException e) {
			GameLogger.logError("Critical Error " + e.getMessage());
		}
	}

	private void handleSpecialRoom(Room newRoom) {
		if (newRoom.isSpecial() && !newRoom.isSpecialEventTriggered()) {
			sceneManager.displayScene(newRoom.getSceneIndex());
			triggerSpecialEvent(newRoom);
			newRoom.setSpecialEventTriggered(true);
		} else {
			triggerRandomEncounter();
		}
	}

	private void updateMoveHistory(Room room) {
		moveHistory.add(room.getName()); // Add the move to the history

	}

	private void triggerRandomEncounter() {
		int chance = random.nextInt(100); // Generate a random number between 0 and 99
		if (chance <= 20) { // 20% chance for an encounter //TODO 1

			System.out.println("A wild monster appears!");
			Monster randomMonster = generateRandomMonsterFromList(player.getPlayerAttributes().getLevel());
			CombatLogic combat = new CombatLogic(player, randomMonster);
			combat.startFight();
		}
	}

	private Monster generateRandomMonsterFromList(int playerLevel) {
		List<Monster> monsters = Arrays.asList(
				new ArcTechSoldier(playerLevel),
				new CorruptedAutomaton(playerLevel),
				new CyberHound(playerLevel),
				new SteelDevourer(playerLevel)
		);
		return monsters.get(random.nextInt(monsters.size()));
	}

	public void displayMap() {    // Display the map with the player's current position
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				Room room = this.map[row][col];  // Get the room from the map

				if (room == null) {
					System.out.print("~~~~~~");  // Empty spaces for null rooms
				} else if (row == player.getPlayerRow() && col == player.getPlayerCol()) {
					System.out.print(" <[P]>");  // Show player's position
				} else if (room.isVisited()) {
					System.out.print(" <[x]>");  // Show visited rooms
				} else {
					System.out.print(" <[ ]>");  // Show unvisited rooms
				}
			}
			System.out.println();  // Newline after each row
		}
	}  // TODO move this outside of this class to display class

	// Define a method to trigger special events
	private void triggerSpecialEvent(Room room) { //TODO add more
		Monster monster;
		int playerLevel = player.getPlayerAttributes().getLevel();
		CombatLogic combat;
		switch (room.getName()) {
			case "Arcane Synth Bay": // set up for 3 rounds of fighting??
				monster = new CorruptedAutomaton(playerLevel);
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "Abandoned Tech Labs":
				player.addArmorToPlayerInventory(new PhantomCircuitArmor());
				break;
			case "Sky Bridge": // room 14
				monster = new Gilgamesh(playerLevel);
				combat = new CombatLogic(player, monster);
				combat.startFight();
				player.addWeaponToPlayerInventory(new Voidbreaker());
				break;
			case "Final Frontier": // room 43
				monster = new GhostCodeManifested(playerLevel);
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "NullSpace Hub": // room 19
				System.out.println("You've come this far be prepared for a formidable foe.");
				break;
			case "The Architect's Citadel": // room 35
				System.out.println("The final confrontation awaits...");
				monster = new TheArchitect(playerLevel);
				combat = new CombatLogic(player, monster);
				combat.startFight();
				break;
			case "Fractured Nexus": //room 27
				monster = new TheCipher(playerLevel);
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


