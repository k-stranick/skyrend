package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.utilities.display.TextDisplayUtility;
import dtcc.itn262.utilities.input.UserInput;
import dtcc.itn262.utilities.input.Validation;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.PlayerSkill;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CombatLogic {
	private final Scanner s = new Scanner(System.in);
	private final Player player;
	private final Monster monster;
	private final PlayerActions playerActions = new PlayerActions(CombatLogic.this);
	protected List<BuffAndDeBuff<PlayerAttributes>> activeBuffs;
	protected List<BuffAndDeBuff<MonsterAttributes>> activeMonsterBuffs;
	private MonsterActions monsterActions = new MonsterActions(CombatLogic.this);
	private ArrayList<PlayerSkill> cooldownList = new ArrayList<>();  // this is a generic stack that holds PlayerSkill objects
	private boolean playerFailedToRun = false;  // Flag to track failed run attempts


	public CombatLogic(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		this.activeBuffs = new ArrayList<>();
	}


	private static void determineOutcome(boolean battleEnded, Player player, Monster monster) {

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
			playerFailedToRun = false;  // Reset at the beginning of each player turn
			reduceCooldown();  // Reduce cooldowns at the start of each turn

			if (playerGoesFirst) {   // Player goes first if their speed is higher
				battleHasEnded = playerTurn();  // Check if the player ends the battle
				if (Validation.keepBattleGoing(battleHasEnded, player, monster)) { // monster's turn still goes if dead...
					monsterTurn();  // Monster's turn if the fight continues
				}
			} else {
				monsterTurn(); // Monster's turn first
				if (player.isAlive()) {
					battleHasEnded = playerTurn(); // Player's turn after monster
				}
			}
			updateBuffs(player); // Update buffs at the end of each turn TODO: TEST
		}
		determineOutcome(battleHasEnded, player, monster); // Check for win/loss conditions once the battle is over

	}


	private boolean playerTurn() {
		displayBattleMenu();
		int choice = UserInput.getPlayerChoice();  // Extracted method for getting choice

		try {
			// If the method returns true, it means the player successfully ran away
			return battleMenuChoice(choice);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
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
			case 5 -> {
				return handleRunAttempt();
			}
			default -> {
				System.out.println("Invalid choice. Please select a valid option.");
				return false;
			}
		}
		return false;
	}


	private boolean handleRunAttempt() {
		if (playerActions.run(player)) {
			return true;  // Successfully ran away, exit battle loop
		} else {
			playerFailedToRun = true;  // Track that the monster already attacked
			return false;  // Continue combat if the player is still alive
		}
	}


	private void monsterTurn() {
		System.out.println("Monster attacks!"); // Implement monster attack logic here
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


	public void handleSkillUsage() {
		playerActions.showSkills();  // Show available skills
		System.out.println("Choose a skill: ");
		int skillIndex = UserInput.getPlayerChoice() - 1;  // Get skill choice by converting user input to 0-based index

		if (skillIndex < 0 || skillIndex >= playerActions.skills.size()) {
			System.out.println("Invalid skill index.");
			return;  // Skill choice was invalid
		}

		PlayerSkill skill = playerActions.getSkill(skillIndex);
		if (skill.isOnCooldown()) {
			System.out.println("Cannot use " + skill.getSkillName() + " for " + skill.getCurrentCooldown() + " more turns.");
			return;  // Skill is on cooldown
		}

		playerActions.useSkill(player, monster, skillIndex);
		cooldownList.add(skill);  // Add skill to cooldown stack
	}


	public void reduceCooldown() { //todo: test this method
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
	public void updateBuffs(Player player) {
		Iterator<BuffAndDeBuff<PlayerAttributes>> iterator = activeBuffs.iterator();
		while (iterator.hasNext()) {
			BuffAndDeBuff<PlayerAttributes> buff = iterator.next();
			buff.decreaseDuration();  // Decrease the duration of each buff
			if (buff.isExpired()) {
				buff.revert(player.getPlayerAttributes());  // Revert the buff when it expires
				iterator.remove();  // Remove the expired buff from the list
				System.out.println("Defense buff expired for " + player.getHero());
			}
		}
	}

	private void displayBattleMenu() {
		TextDisplayUtility.printSeparator(20);
		System.out.println("1. Attack");
		System.out.println("2. Defend");
		System.out.println("3. Use Skill");
		System.out.println("4. Scan Enemy");
		System.out.println("5. Run");
	}
}
