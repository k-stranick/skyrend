package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.PlayerSkill;
import dtcc.itn262.skills.TestPlayerSkill;
import dtcc.itn262.skills.TestSkillPlayerTwo;
import java.util.ArrayList;
import java.util.Random;

public class PlayerActions {
    protected ArrayList<PlayerSkill> skills = new ArrayList<>();

    public PlayerActions() { // adding skills to the player
        skills.add(new TestPlayerSkill());
        skills.add(new TestSkillPlayerTwo());
    }

    protected void attack(Player player, Monster target) {
        int damage = player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense();
        if (damage > 0) {
            target.getMonsterAttributes().setHealth(target.getMonsterAttributes().getHealth() - damage);
            System.out.println(player.getHero() + " attacks " + target.getEnemy() + " for " + damage + " damage.");
        } else {
            System.out.println(player.getHero() + " attacks " + target.getEnemy() + " but the attack is ineffective.");
        }
    }


    protected void defend(Player player) {
        for (int turns = 0; turns < 1; turns++) {
            player.getPlayerAttributes().setDefense(player.getPlayerAttributes().getDefense() + 5); // +5 defense for 1 turn
            System.out.println(player.getHero() + " defends and gains 5 defense.");
        }
    }


    protected void useSkill(Player player, Monster target, int skillIndex) {
        if (skillIndex >= 0 && skillIndex < skills.size()) {
            PlayerSkill skill = skills.get(skillIndex);
            if (!skill.isOnCooldown()) {
                skill.useSkill(player, target);

            }else {
                System.out.println("Cannot use " + skill.getSkillName() + " for " + skill.getCurrentCooldown() + " more turns.");
            }
        } else {
            System.out.println("Invalid skill index.");
        }
    }


    protected void showEnemyStats(Monster target) {
        System.out.println("Enemy: " + target.getEnemy() +
                "\nHealth: " + target.getMonsterAttributes().getHealth() +
                "\nDefense: " + target.getMonsterAttributes().getDefense() +
                "\nMagic: " + target.getMonsterAttributes().getMagic() +
                "\nMagic Defense: " + target.getMonsterAttributes().getMagicDefense() +
                "\nSpeed: " + target.getMonsterAttributes().getSpeed() +
                "\nLuck: " + target.getMonsterAttributes().getLuck());
    }


    protected void showSkills() {  // need to return a skill list in human-readable format
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getSkillName());
        }
    }


    protected PlayerSkill getSkill(int index) {
        if (index >= 0 && index < skills.size()) {
            return skills.get(index);
        }else {
            throw new IndexOutOfBoundsException("Invalid skill index: " + index);
        }
    }

    protected void run() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if(chance<50){
            System.out.println("You failed to run away!");
        }
        System.out.println("You ran away!");
    }
}
