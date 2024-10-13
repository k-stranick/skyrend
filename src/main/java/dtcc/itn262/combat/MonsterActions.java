package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.effects.DefenseBuff;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.monster.genericmonsters.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.monsterskills.IMonsterSkill;
import dtcc.itn262.skills.monsterskills.QuantumDistortion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterActions {
	private final List<IMonsterSkill> monsterSkills = new ArrayList<>();
	CombatLogic combatLogic;


	public MonsterActions(CombatLogic combatLogic) { // adding skills to the player
		monsterSkills.add(new QuantumDistortion());
		this.combatLogic = combatLogic;
	}


	protected void attack(Monster monster, Player target) {
		int damage = (monster.getMonsterAttributes().getStrength() - target.getPlayerAttributes().getDefense());
		if (damage > 0) {
			target.getPlayerAttributes().setHealth(target.getPlayerAttributes().getHealth() - damage);
			System.out.println(monster.getMonster() + " attacks " + target.getHero() + " for " + damage + " damage.");
		} else {
			System.out.println(monster.getMonster() + " attacks " + target.getHero() + " but the attack is ineffective.");
		}
	}


	protected void defend(Monster monster) {
		int buffDuration = 1;
		DefenseBuff<MonsterAttributes> monsterDefenseBuff = new DefenseBuff<>(buffDuration, Constants.DEFENSE_BUFF);
		monsterDefenseBuff.apply(monster.getMonsterAttributes());
		System.out.println(monster.getMonster() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration + " turns. Defense increased to " + monster.getMonsterAttributes().getDefense());
		combatLogic.activeMonsterBuffs.add(monsterDefenseBuff);
	}


	protected void useSkill(Monster target, Player player) {
		try {
			List<IMonsterSkill> monsterSkills = getMonsterSkills();
			if (!monsterSkills.isEmpty()) {
				// Generate a random index from the available skills
				Random rand = new Random();
				int skillIndex = rand.nextInt(monsterSkills.size());  // Random index between 0 and size - 1
				IMonsterSkill skill = monsterSkills.get(skillIndex);// Retrieve the randomly selected skill and use it
				skill.useSkill(target, player);  // Use the skill on the player
			} else {
				System.out.println("The monster has no skills available.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while using the skill: " + e.getMessage());
		}
	}

	// Method to get all the monster's skills
	private List<IMonsterSkill> getMonsterSkills() {
		return monsterSkills;
	}

}
