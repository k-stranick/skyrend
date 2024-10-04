package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.gameutilities.DisplayUtility;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.PlayerSkill;

import java.util.ArrayList;
import java.util.Scanner;
public class CombatLogic {
    private final Scanner s = new Scanner(System.in);
    private final Player player;
    private final Monster monster;
    private final PlayerActions playerActions = new PlayerActions();
    private MonsterActions monsterActions = new MonsterActions();
    private ArrayList<PlayerSkill> cooldownList = new ArrayList<>();  // this is a generic stack that holds PlayerSkill objects
    private boolean playerFailedToRun = false;  // Flag to track failed run attempts

    public CombatLogic(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    private static void determineOutcome(Player player, MonsterAttributes monsterAttributes) {
        if (player.isAlive() && monsterAttributes.getHealth() <= 0) {
            System.out.println("Player wins! The monster is defeated.");
        } else if (!player.isAlive()) {
            System.out.println("Monster wins!\nGame Over!");
            System.exit(0);  // Exit the game after losing
        } else {
            System.out.println("You successfully ran away!");
        }
    }

    public void startFight() {
        boolean fightOnGoing = true;

        do {
            playerFailedToRun = false;  // Reset at the beginning of each player turn

            // Player goes first if their speed is higher
            if (player.getPlayerAttributes().getSpeed() > monster.getMonsterAttributes().getSpeed()) {
                if (playerTurn()) {
                    fightOnGoing = false;  // Player successfully ends the battle by running away
                } else {
                    if (!playerFailedToRun) {
                        monsterTurn();  // Monster's turn if the fight continues
                    }
                }
            } else {
                monsterTurn();  // Monster's turn first
                if (!player.isAlive() || playerTurn()) {
                    fightOnGoing = false;  // Player turn ends the battle or player is dead
                }
            }

            // Reduce cooldowns if fight is ongoing
            if (fightOnGoing) {
                reduceCooldown();
            }

        } while (fightOnGoing && player.isAlive() && monster.getMonsterAttributes().getHealth() > 0);

        // Determine the outcome of the combat
        determineOutcome(player, monster.getMonsterAttributes());
    }

    private boolean playerTurn() {
        while (true) {  // Loop until a valid choice is made
            displayBattleMenu();
            int choice = getPlayerChoice();  // Extracted method for getting choice

            try {
                // If executePlayerChoice returns true, it means the player successfully ran away
                return executePlayerChoice(choice);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    // Helper method to get player's input
    private int getPlayerChoice() {
        while (!s.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            s.nextLine();  // Clear invalid input
        }
        int choice = s.nextInt();
        s.nextLine();  // Clear buffer
        return choice;
    }

    // Helper method to execute the player's choice
    private boolean executePlayerChoice(int choice) {
        switch (choice) {
            case 1 -> handleAttack();
            case 2 -> handleDefense();
            case 3 -> {
                return handleSkillUsage();
            }
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
            monsterTurn();  // Monster attacks if run fails
            if (!player.isAlive()) {
                return true;  // Player is dead, end combat
            }
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

    public boolean handleSkillUsage() {
        playerActions.showSkills();  // Show available skills
        System.out.println("Choose a skill: ");
        int skillIndex = (s.nextInt() - 1);  // Get skill choice by converting user input to 0-based index
        s.nextLine();  // clear buffer

        if (skillIndex < 0 || skillIndex >= playerActions.skills.size()) {
            System.out.println("Invalid skill index.");
            return false;  // Skill choice was invalid
        }

        PlayerSkill skill = playerActions.getSkill(skillIndex);
        if (skill.isOnCooldown()) {
            System.out.println("Cannot use " + skill.getSkillName() + " for " + skill.getCurrentCooldown() + " more turns.");
            return false;  // Skill is on cooldown
        }

        playerActions.useSkill(player, monster, skillIndex);
        cooldownList.add(skill);  // Add skill to cooldown stack
        return true;  // Skill was used successfully
    }

    public void reduceCooldown() {
        for (int i = 0; i < cooldownList.size(); i++) {
            PlayerSkill currentSkill = cooldownList.get(i);
            currentSkill.reduceCooldown();
            if (currentSkill.getCurrentCooldown() == 0) {
                System.out.println(currentSkill.getSkillName() + " is off cooldown.");
                cooldownList.remove(i);
                i--;
            }
        }
    }

    private void displayBattleMenu() {
        DisplayUtility.printSeparator(20);
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Skill");
        System.out.println("4. Scan Enemy");
        System.out.println("5. Run");
    }
}
