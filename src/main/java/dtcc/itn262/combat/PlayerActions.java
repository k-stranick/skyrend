package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.PlayerSkill;
import java.util.ArrayList;
import java.util.Stack;

public class PlayerActions {
    private ArrayList<PlayerSkill> skills = new ArrayList<>();
    private Stack<PlayerSkill> skillStack = new Stack<>();  // this is a generic stack that holds PlayerSkill objects

    protected void attack(Player player, Monster target) {
        int damage = player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense();
        if (damage > 0) {
            target.getMonsterAttributes().setHealth(target.getMonsterAttributes().getHealth() - damage);
            System.out.println(player.getHero() + " attacks " + target.getEnemy() + " for " + damage + " damage.");
        }
    }


    protected void defend(Player player) {
        for (int turns = 0; turns < 1; turns++) {
            player.getPlayerAttributes().setDefense(player.getPlayerAttributes().getDefense() + 5); // +5 defense for 1 turn
            System.out.println(player.getHero() + " defends and gains 5 defense.");
        }
    }


    protected void useSkill(Player player, Monster target, int skillIndex) {
        if (skillStack.isEmpty()) {
            System.out.println("No skills available.");
        } else if (skillIndex >= 0 && skillIndex < skills.size()) {
            PlayerSkill skill = skills.get(skillIndex);
            skillStack.push(skill);

            if (!skill.isOnCooldown()) {
                skill.useSkill(player, target);
                skill.setCooldown();
                System.out.println(player.getHero() + " uses " + skill.getSkillName() + " on " + target.getEnemy());
            } else {
                System.out.println("Skill is on cooldown.");
            }

        }else {
            System.out.println("Invalid skill index.");
        }
    }
    public void showEnemyStats(Monster target){
        System.out.println("Enemy: " + target.getEnemy() +
                "\nHealth: " + target.getMonsterAttributes().getHealth() +
                "\nDefense: " + target.getMonsterAttributes().getDefense() +
                "\nMagic: " + target.getMonsterAttributes().getMagic() +
                "\nMagic Defense: " + target.getMonsterAttributes().getMagicDefense() +
                "\nSpeed: " + target.getMonsterAttributes().getSpeed() +
                "\nLuck: " + target.getMonsterAttributes().getLuck());
    }
    public void showSkills() {
        for (int i = 0; i < skills.size(); i++) {
            System.out.println(i + ". " + skills.get(i).getSkillName());
        }
    }
}
