package dtcc.itn262.utilities.display;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.PriorityManager;
import dtcc.itn262.combat.TurnOrder;
import dtcc.itn262.items.Item;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;
import dtcc.itn262.maze.Room;
import dtcc.itn262.monster.Monster;

import java.util.*;

public class TextDisplayUtility {

    private TextDisplayUtility() {
    }


	public static void printSeparator(int i) {
		for (int j = 0; j < i; j++) {
			System.out.print("-");
		}
		System.out.println();
	}


	// Show the rooms the player has visited
	public static void showProgress(Set<String> uniqueVisitedRooms) {
		System.out.println("Special rooms you have visited: ");
		for (String room : uniqueVisitedRooms) {
			System.out.println(room);
		}
	}


	public static void showMoveHistory(List<String> moveHistory) {
		System.out.println("Move History: ");
		printSeparator(20);
		int counter = 1;
		for (String move : moveHistory) {
			System.out.println(counter + ": Moved to " + move);  // Print each move on a new line
			counter++;
		}
	}


	public static void showCurrentRoom(Room[][] map, Player player) {
		Room currentRoom = map[player.getPlayerRow()][player.getPlayerCol()];
		System.out.println("You are in: " + currentRoom.getName());
		System.out.println(currentRoom.getDescription());
	}


	private static String generateBar(int current, int max) {
		if (max <= 0) {
			return "[Invalid values: max <= 0]";
		}
		if (current < 0) {
			return "[Invalid values: current < 0]";
		}
		if (current > max) {
			current = max; // Cap current to max to avoid invalid bar lengths
		}
		int barLength = 20; // Fixed length for the bar
		int filledLength = (int) (((double) current / max) * barLength);
		String filled = "=".repeat(filledLength);
		String empty = " ".repeat(barLength - filledLength);
		return "[" + filled + empty + "] " + current + "/" + max;
	}

	// Prints the footer of the battle screen
	private static void printFooter(int totalWidth) {
		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}

	public static void showBattleScreen(Player player/*, PriorityManager priorityManager*/, Monster enemies) {
		final int TOTAL_WIDTH = 104; // Total width of the display

		printHeader(TOTAL_WIDTH);
		//displayBattleQueue(priorityManager, TOTAL_WIDTH);
		printHeroStats(player, TOTAL_WIDTH);
		printMonsterStats(enemies, TOTAL_WIDTH);
		printActionMenu(TOTAL_WIDTH);
		printFooter(TOTAL_WIDTH);
	}

	// Prints the header of the battle screen
	private static void printHeader(int totalWidth) {
		String title = "Battle";
		int padding = (totalWidth - title.length()) / 2; // Calculate padding for centering
		String centeredTitle = " ".repeat(padding) + title + " ".repeat(totalWidth - title.length() - padding);

		System.out.println("+" + "-".repeat(totalWidth) + "+");
		System.out.println("|" + centeredTitle + "|");
		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}


	// Prints the stats of the hero (player)
	private static void printHeroStats(Player player, int totalWidth) {
		String playerHpBar = generateBar(player.getPlayerAttributes().getHealth(), player.getPlayerAttributes().getMaxHealth());
		String playerMpBar = generateBar(player.getPlayerAttributes().getMana(), player.getPlayerAttributes().getMaxMana());

		System.out.println("| Hero: " + player.getHeroName());
		System.out.println("| HP: " + padRight(playerHpBar, totalWidth - 5) + "|");
		System.out.println("| MP: " + padRight(playerMpBar, totalWidth - 5) + "|");
		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}

	// Prints the stats of each monster in the battle
	private static void printMonsterStats(Monster monster, int totalWidth) {
		if (monster.getMonsterAttributes().getHealth() <= 0) {
			return; // Skip if the monster is defeated
		}
		String enemyHpBar = generateBar(monster.getMonsterAttributes().getHealth(), monster.getMonsterAttributes().getMaxHealth());
		String enemyMpBar = generateBar(monster.getMonsterAttributes().getMana(), monster.getMonsterAttributes().getMaxMana());

		System.out.printf("| Monster: %-20s HP: %-30s MP: %-30s  %n",
				padRight(monster.getMonster(), 20),
				padRight(enemyHpBar, 30),
				padRight(enemyMpBar, 30));
		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}
	/*	private static void printMonsterStats(List<Monster> monsters, int totalWidth) {
		for (int i = 0; i < monsters.size(); i++) {
			Monster enemy = monsters.get(i);
			if (enemy.getMonsterAttributes().getHealth() <= 0) {
				continue; // Skip defeated monsters
			}
			String enemyHpBar = generateBar(enemy.getMonsterAttributes().getHealth(), enemy.getMonsterAttributes().getMaxHealth());
			String enemyMpBar = generateBar(enemy.getMonsterAttributes().getMana(), enemy.getMonsterAttributes().getMaxMana());

			System.out.printf("| Monster %d: %-20s HP: %-30s MP: %-30s  %n",
					i + 1,
					padRight(enemy.getMonster(), 20),
					padRight(enemyHpBar, 30),
					padRight(enemyMpBar, 30));
		}
		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}*/

	private static void displayBattleQueue(PriorityManager priorityManager, int totalWidth) {
		System.out.println("+" + "-".repeat(totalWidth) + "+");
		System.out.println("| Current Turn Queue: ");
		System.out.println("+" + "-".repeat(totalWidth) + "+");

		// Get a snapshot of the queue
		PriorityQueue<TurnOrder> queueSnapshot = new PriorityQueue<>(priorityManager.getCurrentQueue());

		// Display each turn order in the queue
		int position = 1;
		while (!queueSnapshot.isEmpty()) {
			TurnOrder turnOrder = queueSnapshot.poll();
			String entityName = turnOrder.getName();
			int speed = turnOrder.getPriority();
			System.out.printf("| %-3d: %-30s Speed: %-5d |\n", position++, entityName, speed);
		}

		System.out.println("+" + "-".repeat(totalWidth) + "+");
	}


	// Prints the action menu for the player
	private static void printActionMenu(int totalWidth) {
		System.out.println("| [1] Attack      [2] Defend            [3] Use Skill       [4] Scan     [5] Item");
		System.out.println("| [6] Stats       [7] Swap Weapon       [8] Swap Armor      [9] Run");
	}

	// Pads a string with spaces to ensure consistent width
	private static String padRight(String text, int length) {
		if (text.length() >= length) {
			return text; // If the text is already the desired length or longer, return as-is
		}
		StringBuilder padded = new StringBuilder(text);
		while (padded.length() < length) {
			padded.append(" ");
		}
		return padded.toString();
	}

	public static void showMainMenu() {
		AsciiArt.displayAsciiArt("src/main/java/dtcc/itn262/json/ascii-text-art.txt");  // Display the game logo
		System.out.println("========================= START MENU ===============================");
		System.out.println("1. Start New Game - Begin a new adventure");
		System.out.println("2. Load Game - Load a saved game");
		System.out.println("3. View Help - Learn how to play the game");
		System.out.println("4. Exit");
		System.out.print("Enter your choice: ");
	}

	public static boolean displayInventory(Player player) {
		List<Weapon> weapons = player.getPlayerWeaponList();
		List<Armor> armors = player.getPlayerArmorList();
		List<HealingItems> consumables = player.getPlayerItemsList();

		if (weapons.isEmpty() && armors.isEmpty() && consumables.isEmpty()) {
			System.out.println("Your inventory is empty.");
			return false;
		}

		System.out.println("\n====== INVENTORY ======\n");

		int index = 0; // This will be used to assign a unique index to each item

		Map<Integer, Item> indexToItemMap = new HashMap<>(); // To map indices to items for selection

		// Display Weapons
		if (!weapons.isEmpty()) {
			System.out.println("Weapons:");
			System.out.printf("%-5s %-25s %-15s %-30s%n", "Index", "Name", "Type", "Description");
			System.out.println("--------------------------------------------------------------------------");
			for (Weapon weapon : weapons) {
				String equippedIndicator = weapon.equals(player.getEquippedWeapon()) ? "(Equipped)" : "";
				System.out.printf("%-5d %-25s %-15s %-30s%n", index, weapon.getName() + " " + equippedIndicator, "Weapon", weapon.getDescription());
				indexToItemMap.put(index, weapon);
				index++;
			}
			System.out.println();
		}

		// Display Armor
		if (!armors.isEmpty()) {
			System.out.println("Armor:");
			System.out.printf("%-5s %-25s %-15s %-30s%n", "Index", "Name", "Type", "Description");
			System.out.println("--------------------------------------------------------------------------");
			for (Armor armor : armors) {
				String equippedIndicator = armor.equals(player.getEquippedArmor()) ? "(Equipped)" : "";
				System.out.printf("%-5d %-25s %-15s %-30s%n", index, armor.getName() + " " + equippedIndicator, "Armor", armor.getDescription());
				indexToItemMap.put(index, armor);
				index++;
			}
			System.out.println();
		}

		// Display Consumables
		if (!consumables.isEmpty()) {
			System.out.println("Consumables:");
			System.out.printf("%-5s %-25s %-15s %-30s%n", "Index", "Name", "Type", "Description");
			System.out.println("--------------------------------------------------------------------------");
			for (HealingItems item : consumables) {
				System.out.printf("%-5d %-25s %-15s %-30s%n", index, item.getName(), "Consumable", item.getDescription());
				indexToItemMap.put(index, item);
				index++;
			}
			System.out.println();
		}

		// Store the index-to-item mapping for later use
		player.setInventoryIndexMap(indexToItemMap);

		return true;
	}

	private void displayConsumablesForInventory(List<HealingItems> consumables){

	}


}
