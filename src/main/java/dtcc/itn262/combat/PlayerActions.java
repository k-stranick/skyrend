package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.gameutilities.Constants;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.skills.PlayerSkill;
import dtcc.itn262.skills.TestPlayerSkill;
import dtcc.itn262.skills.TestSkillPlayerTwo;

import java.util.ArrayList;
import java.util.Random;

public class PlayerActions {
	protected ArrayList<PlayerSkill> skills = new ArrayList<>();
	CombatLogic combatLogic;
	Random rand = new Random();


	public PlayerActions(CombatLogic combatLogic) { // adding skills to the player
		skills.add(new TestPlayerSkill());
		skills.add(new TestSkillPlayerTwo());
        this.combatLogic = combatLogic;
	}

	protected void attack(Player player, Monster target) {
		int damage = (player.getPlayerAttributes().getStrength() - target.getMonsterAttributes().getDefense());
		if (damage > 0) {
			target.getMonsterAttributes().setHealth(target.getMonsterAttributes().getHealth() - damage);
			System.out.println(player.getHero() + " attacks " + target.getEnemy() + " for " + damage + " damage.");
		} else {
			System.out.println(player.getHero() + " attacks " + target.getEnemy() + " but the attack is ineffective.");
		}
	}


	protected void defend(Player player) {
		int buffDuration = 2;
		BuffAndDeBuff<PlayerAttributes> defenseBuff = new DefenseBuff(buffDuration, Constants.DEFENSE_BUFF);
		defenseBuff.apply(player.getPlayerAttributes());
        System.out.println(player.getHero() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration + " turns.");
        combatLogic.activeBuffs.add(defenseBuff);
	}


	protected void useSkill(Player player, Monster target, int skillIndex) {
		if (skillIndex >= 0 && skillIndex < skills.size()) {
			PlayerSkill skill = skills.get(skillIndex);
			if (!skill.isOnCooldown()) {
				skill.useSkill(player, target);

			} else {
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
		} else {
			throw new IndexOutOfBoundsException("Invalid skill index: " + index);
		}
	}


	protected boolean run(Player player) {
		int luck = player.getPlayerAttributes().getLuck();  // Use player's luck to influence run success
		int chanceToRun = rand.nextInt(100);  // Generate a random number between 0 and 99
		if ((chanceToRun + luck) > 50) {  // Add player's luck to the chance and compare
			return true;
		} else {
			System.out.println("You failed to run away!");
			return false;
		}
	}
}
