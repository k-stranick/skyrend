package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.combat.effects.BuffAndDeBuff;
import dtcc.itn262.items.Item;
import dtcc.itn262.items.ItemManagement;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.HealingItems;
import dtcc.itn262.items.weapons.Weapon;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.playerskills.PlayerSkill;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.GameLogger;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;
import dtcc.itn262.utilities.soundandmusic.Music;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class CombatLogic {
	private final Player player;

	private final Monster monster;
	private final PlayerActions playerActions;
	private final MonsterActions monsterActions;
	private final ArrayList<PlayerSkill> cooldownList = new ArrayList<>();  // this is a generic stack that holds PlayerSkill objects
	protected List<BuffAndDeBuff<PlayerAttributes>> activePlayerBuffs;
	protected List<BuffAndDeBuff<MonsterAttributes>> activeMonsterBuffs;


	public CombatLogic(Player player, Monster monster) {
		Music.stopBackgroundMusic();
		Music.playBackgroundMusic("src/main/java/dtcc/itn262/utilities/soundandmusic/soundfiles/battle_music.wav");
		this.player = player;
		this.monster = monster;
		this.playerActions = new PlayerActions(CombatLogic.this, player);
		this.monsterActions = new MonsterActions(CombatLogic.this);
		this.activePlayerBuffs = new ArrayList<>();
		this.activeMonsterBuffs = new ArrayList<>();
	}

	private static void determineOutcome(boolean battleEnded, Player player, Monster monster) {
		if (battleEnded) {
			System.out.println("You successfully ran away!");
		} else if (!player.isAlive()) {
			System.out.println("You were defeated!");
		} else {
			System.out.println("Player wins! The monster is defeated.");
			int xpGained = monster.getMonsterAttributes().getExperience();
			player.gainExperience(xpGained);
			System.out.println(player.getHeroName() + " gained " + xpGained + " XP.");

			handleLootDrops(player);
		}
	}

	private static void handleLootDrops(Player player) {
		Random rand = new Random();
		ItemManagement itemManagement = new ItemManagement();

		// Decide whether the monster drops an item
		double dropChance = 0.5; // 50% chance to drop an item (adjust as needed)
		if (rand.nextDouble() < dropChance) {
			// Generate a random item
			Item item = itemManagement.generateRandomItem();
			System.out.println("The monster dropped an item: " + item.getName());

			// Clone the item before adding it to the player's inventory
			Item itemClone = item.clone();
			// Use a switch statement to determine the item type and add it to the inventory
			switch (itemClone) {
				case Weapon weapon -> player.addWeapon(weapon);
				case Armor armor -> player.addArmor(armor);
				case HealingItems usableItem -> player.addItem(usableItem);
				default -> System.out.println("Unknown item type: " + itemClone.getName());
			}

		} else {
			System.out.println("The monster didn't drop any items.");
		}
	}

	public void startFight() { // need to check status for this also

		boolean playerUsedRun = false;  // Declare playerUsedRun at the start of the method
		boolean playerGoesFirst = player.getPlayerAttributes().getSpeed() > monster.getMonsterAttributes().getSpeed(); // move inside the if statement if I introduce buffs or de-buffs for speed

		while (Validation.keepBattleGoing(playerUsedRun, player, monster)) {
			reduceCooldown();  // Reduce cooldowns at the start of each turn

			if (playerGoesFirst) {   // Player goes first if their speed is higher
				playerUsedRun = playerTurn();  // Check if the player ends the battle
				updateBuffs(monster.getMonsterAttributes(), activeMonsterBuffs); // Update buffs at the end of each PLAYER TURN
				if (Validation.keepBattleGoing(playerUsedRun, player, monster)) { // monster's turn still goes if dead...
					monsterTurn();  // Monster's turn if the fight continues
					updateBuffs(player.getPlayerAttributes(), activePlayerBuffs); // Update buffs at the end of each turn??
				}
			} else {
				monsterTurn(); // Monster's turn first
				updateBuffs(player.getPlayerAttributes(), activePlayerBuffs); // Update buffs at the end of each turn??
				if (player.isAlive()) {
					playerUsedRun = playerTurn(); // Player's turn after monster
					updateBuffs(monster.getMonsterAttributes(), activeMonsterBuffs); // Update buf fs at the end of each PLAYER TURN
				}
			}
			//CREATE METHOD FOR HERE TO HANDLE DEBUFF,COOLDOWN,STATUSEFFECTS, ETC
		}
		determineOutcome(playerUsedRun, player, monster); // Check for win/loss conditions once the battle is over
		Music.stopBackgroundMusic();
		Music.playBackgroundMusic("src/main/java/dtcc/itn262/utilities/soundandmusic/soundfiles/over_world_music.wav");
	}

	private void monsterTurn() {
		Random rand = new Random();
		int choice = rand.nextInt(3) + 1;  // Randomly choose between attack, defend, and skill usage
		switch (choice) {
			case 1 -> monsterActions.attack(monster, player);
			case 2 -> monsterActions.defend(monster);
			case 3 -> monsterActions.useSkill(monster, player);
		}
	}

	private boolean playerTurn() {
		TextDisplayUtility.showBattleScreen(player, monster);
		//System.out.print("Choose an action: ");
		int choice = UserInput.getPlayerChoice("Choose an Action: ");  // Extracted method for getting choice
		try {
			// If the method returns true, it means the player successfully ran away
			return battleMenuChoice(choice);
		} catch (Exception e) {
			GameLogger.logError("An error occurred: " + e.getMessage());
		}
		return false;
	}

	private boolean battleMenuChoice(int choice) {    // Helper method to execute the player's choice

		switch (choice) {
			case 1 -> handleAttack();
			case 2 -> handleDefense();
			case 3 -> handleSkillUsage();
			case 4 -> handleEnemyScan();
			case 5 -> handleItemUsage();
			case 6 -> playerActions.showPlayerStats();
			case 7 -> handleWeaponSwap();
			case 8 -> handleArmorSwap();
			case 9 -> {
				return handleRunAttempt();
			}
			default -> {
				GameLogger.logWarning("Invalid choice. Please select a valid option.");
			}
		}
		return false;
	}

	private void handleArmorSwap() {
		// Grab player's inventory exits if empty
		List<Armor> armorList = player.getPlayerArmorList();
		if (armorList.isEmpty()) {
			System.out.println("No armor in inventory.");
			return;
		}

		// Display armor with indices
		displayAndChooseItem("Armor", armorList, player.getEquippedArmor());
	}


	private void handleWeaponSwap() {
		List<Weapon> weaponList = player.getPlayerWeaponList();
		if (weaponList.isEmpty()) {
			System.out.println("No weapons in inventory.");
			return;
		}
		// Display weapons with indices
		displayAndChooseItem("Weapons", weaponList, player.getEquippedWeapon());
	}

	private void handleItemUsage() {
		List<HealingItems> itemsList = player.getPlayerItemsList();
		if (itemsList.isEmpty()) {
			System.out.println("No items in inventory.");
			return;
		}
		// Display items with indices
		displayAndChooseItem("Items", itemsList, null);

	}

	private <T extends Item> void displayAndChooseItem(String itemType, List<T> itemList, T equippedItem) {
		TextDisplayUtility.displayItemsInBattle(itemType, itemList, equippedItem);
		int itemChoice = UserInput.getPlayerChoice("Choose a " + itemType.toLowerCase() + " to equip: ");
		if (itemChoice == -1) {
			return;
		}
		while (itemChoice < -2 || itemChoice >= itemList.size()) {
			System.out.println("Invalid " + itemType.toLowerCase() + " choice.");
			itemChoice = UserInput.getPlayerChoice("Choose a " + itemType.toLowerCase() + " to equip: ");
		}
		T selectedItem = itemList.get(itemChoice);
		if (selectedItem instanceof Armor) {
			playerActions.equipArmor((Armor) selectedItem);
		} else if (selectedItem instanceof Weapon) {
			playerActions.equipWeapon((Weapon) selectedItem);
		}
	}

	private boolean handleRunAttempt() { //TODO test
		// Continue combat if the player is still alive
		return playerActions.run(player);  // Successfully ran away, exit battle loop
	}

	private void handleAttack() {
		playerActions.attack(player, monster);
	}

	private void handleDefense() {
		playerActions.defend(player);
	}

	private void handleEnemyScan() {
		playerActions.scanEnemy(monster);
	}

	private void handleSkillUsage() {
		playerActions.showSkills();  // Show available skills
		int skillIndex = UserInput.getPlayerChoice("Choose A Skill: ") - 1;  // Get skill choice by converting user input to 0-based index
		PlayerSkill skill = playerActions.getSkill(skillIndex);
		playerActions.useSkill(player, monster, skillIndex);
		cooldownList.add(skill);  // Add skill to cooldown stack
	}

	private void reduceCooldown() { //todo: test this method
		Iterator<PlayerSkill> iterator = cooldownList.iterator();
		while (iterator.hasNext()) {
			PlayerSkill currentSkill = iterator.next();
			currentSkill.reduceCooldown();
			if (currentSkill.getCurrentCooldown() == 0) {
				System.out.println(currentSkill.getSkillName() + " is off cooldown.");
				iterator.remove();
			}
		}
	}

	private <T> void updateBuffs(T character, List<BuffAndDeBuff<T>> activeBuffs) { // Add a method to update buffs at the end of each turn
		Iterator<BuffAndDeBuff<T>> iterator = activeBuffs.iterator();
		while (iterator.hasNext()) {
			BuffAndDeBuff<T> buff = iterator.next();
			buff.decreaseDuration();  // Decrease the duration of each buff
			if (buff.isExpired()) {
				buff.revert(character);  // Revert the buff when it expires
				iterator.remove();  // Remove the expired buff from the list
				if (character instanceof Player)
					System.out.println("Defense buff expired for " + ((Player) character).getHeroName() + ". Defense reverts back to " + ((Player) character).getPlayerAttributes().getDefense());
				else if (character instanceof Monster) {
					System.out.println("Defense buff expired for " + ((Monster) character).getMonster() + ". Defense reverts back to " + ((Monster) character).getMonsterAttributes().getDefense());
				}
			}
		}
	}

}
