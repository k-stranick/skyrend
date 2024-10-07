package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.gameutilities.DisplayUtility;
import dtcc.itn262.gameutilities.UserInput;
import dtcc.itn262.monster.Monster;
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


    private static void determineOutcome(boolean battleEnded, Player player, Monster monster) {

        if (battleEnded) {
            System.out.println("You successfully ran away!");
        } else if (!player.isAlive()) {
            System.out.println("You were defeated!");
        } else{// will this print if I win??
            //!monster.isAlive()
            System.out.println("Player wins! The monster is defeated.");
        }
    }


    public void startFight() { // need to check status for this also
        boolean battleHasEnded = false;  // Declare battleEnded at the start of the method
        boolean playerGoesFirst = player.getPlayerAttributes().getSpeed() > monster.getMonsterAttributes().getSpeed(); // move inside the if statement
        // if I introduce buffs or de-buffs for speed

        while (player.isAlive() && monster.isAlive() && !battleHasEnded) {
            playerFailedToRun = false;  // Reset at the beginning of each player turn

            if (playerGoesFirst) {   // Player goes first if their speed is higher
                battleHasEnded = playerTurn();  // Check if the player ends the battle
                if (!battleHasEnded && player.isAlive() && monster.isAlive()) { // monster's turn still goes if dead...
                    monsterTurn();  // Monster's turn if the fight continues
                }
            } else {
                monsterTurn();  // Monster's turn first
                if (!player.isAlive()) {
                    break;  // End the battle if the player is dead
                }
                battleHasEnded = playerTurn();  // Player's turn after monster
            }

            // Reduce cooldowns if the fight continues
            if (!battleHasEnded) {
                reduceCooldown();
            }
        }

        // Check for win/loss conditions once the battle is over
        determineOutcome(battleHasEnded, player, monster);
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
        int skillIndex = (s.nextInt() - 1);  // Get skill choice by converting user input to 0-based index
        s.nextLine();  // clear buffer

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
