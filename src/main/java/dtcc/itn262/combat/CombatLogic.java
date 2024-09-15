package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;

import java.util.Scanner;

public class CombatLogic {
    private Scanner s = new Scanner(System.in);
    private Player player;
    private Monster monster;
    private boolean playerTurn = true;
    private boolean playerWon = false;
    private boolean monsterWon = false;
    private boolean monsterTurn = false;
    private boolean combatOver = false;
    private int turnCounter = 0;
    private PlayerAttributes playerAttributes;
    private MonsterAttributes monsterAttributes;
    private PlayerActions playerActions = new PlayerActions();
    private MonsterActions monsterActions = new MonsterActions();

    public CombatLogic(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        this.playerAttributes = player.getPlayerAttributes();
        this.monsterAttributes = monster.getMonsterAttributes();
    }

    public void startFight() {
        System.out.println("The fight begins!");

        while (playerAttributes.getHealth() > 0 && monsterAttributes.getHealth() > 0) {
            playerTurn();
 /*           if (monsterAttributes.getHealth() > 0) {
                monsterTurn();
            }*/
        }

        if (playerAttributes.getHealth() > 0) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Monster wins!\nGame Over!");
            System.exit(0); // exit the game
        }
    }

    private void monsterTurn() {

    }
// BATTLE LOGIC IS BROKEN AND NEEDS TO BE FIXED
    private void playerTurn() {
        System.out.println("Choose an action: ");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Skill");
        System.out.println("4. Show Enemy Stats");
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                playerActions.attack(player, monster);
                break;
            case 2:
                playerActions.defend(player);
                break;
            case 3:
                playerActions.showSkills();  // Show available skills
                System.out.println("Choose a skill: ");
                int skillIndex = s.nextInt();  // Get skill choice
                playerActions.useSkill(player, monster, 0);  // Use skill
                break;
            case 4:
                playerActions.showEnemyStats(monster);
                break;

            default:
                System.out.println("Invalid choice.");
        }

    }

}
