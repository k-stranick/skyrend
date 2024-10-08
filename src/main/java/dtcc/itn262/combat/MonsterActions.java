package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.gameutilities.Constants;
import dtcc.itn262.monster.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.MonsterSkill;
import dtcc.itn262.skills.TestMonsterSkill;
import java.util.ArrayList;
import java.util.Random;

public class MonsterActions {
	protected ArrayList<MonsterSkill> monsterSkills = new ArrayList<>();
	CombatLogic combatLogic;
	Random rand = new Random();


	public MonsterActions(CombatLogic combatLogic) { // adding skills to the player
		monsterSkills.add(new TestMonsterSkill());

		this.combatLogic = combatLogic;
	}

	protected void attack(Monster monster, Player target) {
		int damage = (monster.getMonsterAttributes().getStrength() - target.getPlayerAttributes().getDefense());
		if (damage > 0) {
			target.getPlayerAttributes().setHealth(target.getPlayerAttributes().getHealth() - damage);
			System.out.println(monster.getEnemy() + " attacks " + target.getHero() + " for " + damage + " damage.");
		} else {
			System.out.println(monster.getEnemy() + " attacks " + target.getHero() + " but the attack is ineffective.");
		}
	}


	protected void defend(Monster monster) {
		int buffDuration = 2;
		BuffAndDeBuff<MonsterAttributes> defenseBuff = new MonsterDefenseBuff(buffDuration, Constants.DEFENSE_BUFF);
		defenseBuff.apply(monster.getMonsterAttributes());
		System.out.println(monster.getEnemy() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration + " turns.");
		combatLogic.activeMonsterBuffs.add(defenseBuff);
	}


	protected void useSkill(Monster target, Player player, int skillIndex) {
		try {
			if (skillIndex >= 0 && skillIndex < monsterSkills.size()) {
				MonsterSkill skill = monsterSkills.get(skillIndex);
				skill.useSkill(target, player);
			} else {
				System.out.println("Invalid skill index.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while using the skill: " + e.getMessage());
		}
	}



	protected MonsterSkill getSkill(int index) {
		try {
			if (index >= 0 && index < monsterSkills.size()) {
				return monsterSkills.get(index);
			} else {
				throw new IndexOutOfBoundsException("Invalid skill index: " + index);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	protected boolean run(Monster monster) {
		int luck = monster.getMonsterAttributes().getLuck();  // Use monster's luck to influence run success
		int chanceToRun = rand.nextInt(100);  // Generate a random number between 0 and 99
		if ((chanceToRun + luck) > 70) {  // Add monster's luck to the chance and compare
			return true;
		} else {
			System.out.println(monster.getEnemy() +" failed to run away!");
			return false;
		}
	}
}
