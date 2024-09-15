package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public class PlayerCombatLogic {


    public void attack(Player player, Monster target) {
        int damage = player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense();
        if (damage > 0) {
            target.getMonsterAttributes().setHealth(target.getMonsterAttributes().getHealth() - damage);
            System.out.println(player.getHero() + " attacks " + target.getEnemy() + " for " + damage + " damage.");
        }
    }


    public void defend(Player player) {
        for (int turns = 0; turns < 3; turns++) {
            player.getPlayerAttributes().setDefense(player.getPlayerAttributes().getDefense() + 5); // +5 defense for 3 turns
            System.out.println(player.getHero() + " defends and gains 5 defense.");
        }
    }

}