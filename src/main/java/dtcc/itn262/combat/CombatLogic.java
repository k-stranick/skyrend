package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.combat.effects.BuffAndDeBuff;
import dtcc.itn262.items.usableitems.UsableItems;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.generic.Monster;
import dtcc.itn262.skills.playerskills.PlayerSkill;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.gamecore.GameLogger;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;

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
		this.player = player;
		this.monster = monster;
		this.playerActions = new PlayerActions(CombatLogic.this, player);
		this.monsterActions = new MonsterActions(CombatLogic.this);
		this.activePlayerBuffs = new ArrayList<>();
		this.activeMonsterBuffs = new ArrayList<>();
	}


	private static void determineOutcome(boolean battleEnded, Player player) {
		if (battleEnded) {
			System.out.println("You successfully ran away!");
		} else if (!player.isAlive()) {
			System.out.println("You were defeated!");
		} else {// will this print if I win??
			//!monster.isAlive()
			System.out.println("Player wins! The monster is defeated.");
		}
	}

	public void startFight() { // need to check status for this also
		boolean battleHasEnded = false;  // Declare battleEnded at the start of the method
		boolean playerGoesFirst = player.getPlayerAttributes().getSpeed() > monster.getMonsterAttributes().getSpeed(); // move inside the if statement if I introduce buffs or de-buffs for speed

		while (Validation.keepBattleGoing(battleHasEnded, player, monster)) {
			reduceCooldown();  // Reduce cooldowns at the start of each turn

			if (playerGoesFirst) {   // Player goes first if their speed is higher
				battleHasEnded = playerTurn();  // Check if the player ends the battle
				updateBuffs(monster.getMonsterAttributes(), activeMonsterBuffs); // Update buffs at the end of each PLAYER TURN
				if (Validation.keepBattleGoing(battleHasEnded, player, monster)) { // monster's turn still goes if dead...
					monsterTurn();  // Monster's turn if the fight continues
					updateBuffs(player.getPlayerAttributes(), activePlayerBuffs); // Update buffs at the end of each turn??
				}
			} else {
				monsterTurn(); // Monster's turn first
				updateBuffs(player.getPlayerAttributes(), activePlayerBuffs); // Update buffs at the end of each turn??
				if (player.isAlive()) {
					battleHasEnded = playerTurn(); // Player's turn after monster
					updateBuffs(monster.getMonsterAttributes(), activeMonsterBuffs); // Update buf fs at the end of each PLAYER TURN
				}
			}
			//CREATE METHOD FOR HERE TO HANDLE DEBUFF,COOLDOWN,STATUSEFFECTS, ETC
		}
		determineOutcome(battleHasEnded, player); // Check for win/loss conditions once the battle is over
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

	// Helper method to execute the player's choice
	private boolean battleMenuChoice(int choice) {
		switch (choice) {
			case 1 -> handleAttack();
			case 2 -> handleDefense();
			case 3 -> handleSkillUsage();
			case 4 -> handleEnemyScan();
			case 5 -> handleItemUsage();
			case 6 -> handleWeaponSwap();
			case 7 -> handleArmorSwap();
			case 8 -> {
				return handleRunAttempt();
			}
			default -> {
				GameLogger.logWarning("Invalid choice. Please select a valid option.");
				return false;
			}
		}
		return false;
	}

	private void handleArmorSwap() {
	}

	private void handleWeaponSwap() {
	}

	private void handleItemUsage() {
		//UsableItems healthPotion = new UsableItems("Health Potion", "Health Potion", 50);
		//playerActions.addItem(healthPotion);  // Add a health potion to the player's inventory
		player.displayInventory();  // Display player's items
		int itemChoice = UserInput.getPlayerChoice("Choose an item to use: ");  // why is this returning 0?
		playerActions.useItem(player, itemChoice);  // Use the selected item

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
		playerActions.showEnemyStats(monster);
	}

	private void handleSkillUsage() {
		playerActions.showSkills();  // Show available skills
		System.out.println("Choose a skill: ");
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


	// Add a method to update buffs at the end of each turn
	private <T> void updateBuffs(T character, List<BuffAndDeBuff<T>> activeBuffs) {
		Iterator<BuffAndDeBuff<T>> iterator = activeBuffs.iterator();
		while (iterator.hasNext()) {
			BuffAndDeBuff<T> buff = iterator.next();
			buff.decreaseDuration();  // Decrease the duration of each buff
			if (buff.isExpired()) {
				buff.revert(character);  // Revert the buff when it expires
				iterator.remove();  // Remove the expired buff from the list
				System.out.println("Defense buff expired for " + character);
			}
		}
	}
}
