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
    private Scanner s = new Scanner(System.in);
    private Player player;
    private Monster monster;
    private PlayerAttributes playerAttributes;
    private MonsterAttributes monsterAttributes;
    private PlayerActions playerActions = new PlayerActions();
    private MonsterActions monsterActions = new MonsterActions();
    private ArrayList<PlayerSkill> cooldownList = new ArrayList<>();  // this is a generic stack that holds PlayerSkill objects

    public CombatLogic(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        this.playerAttributes = player.getPlayerAttributes();
        this.monsterAttributes = monster.getMonsterAttributes();
    }

    public void startFight() {
        boolean playerGoesFirst = player.getPlayerAttributes().getSpeed() > monster.getMonsterAttributes().getSpeed();
        do {
            if (playerGoesFirst) {
                playerTurn();

            } else {
                monsterTurn();
            }
            reduceCooldown();  // should this be here it?
            playerGoesFirst = !playerGoesFirst;

        } while (playerAttributes.getHealth() > 0 && monsterAttributes.getHealth() > 0);

        if (playerAttributes.getHealth() > 0) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Monster wins!\nGame Over!");
            System.exit(0); // exit the game
        }
    }

    private void monsterTurn() {
        System.out.println("Monster attacks!"); // placeholder
    }

    // BATTLE LOGIC IS BROKEN AND NEEDS TO BE FIXED
    private void playerTurn() {
        displayBattleMenu();
        int choice = s.nextInt();  // returns null if improper choice is entered need to fix this
        s.nextLine();  // clear buffer
        try {
            switch (choice) {
                case 1:
                    handleAttack();
                    break;
                case 2:
                    handleDefense();
                    break;
                case 3:
                    handleSkillUsage();
                    break;
                case 4:
                    handleEnemyScan(); // Show enemy stats
                    break;
                default:
                    System.out.println("Invalid choice.");
                    playerTurn();// loop back to battle-menu
            }

        } catch (IndexOutOfBoundsException e) {
            //TODO: ADD LOGGER HERE
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("An Error Occured: " + e.getMessage());

        }
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
        ArrayList<PlayerSkill> tempList = new ArrayList<>();
            for (PlayerSkill currentSkill : cooldownList) {
                currentSkill.reduceCooldown();
                if (currentSkill.getCurrentCooldown() == 0) {
                    System.out.println(currentSkill.getSkillName() + " is off cooldown.");
                } else {
                    tempList.add(currentSkill);  // Add skill back to temp stack if it's still on cooldown
                }
            }
            cooldownList = tempList;  // replace cooldown stack with updated temp stack

    }


    private void displayBattleMenu() {
        DisplayUtility.printSeparator(20);
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Skill");
        System.out.println("4. Scan Enemy");
    }

}
