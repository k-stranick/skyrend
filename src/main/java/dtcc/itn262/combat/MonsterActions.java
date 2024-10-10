package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.combat.effects.MonsterDefenseBuff;
import dtcc.itn262.utilities.gamecore.Constants;
import dtcc.itn262.monster.generic.Monster;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.skills.monsterskills.MonsterSkill;
import dtcc.itn262.skills.monsterskills.QuantumDistortion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterActions {
	private final List<MonsterSkill> monsterSkills = new ArrayList<>();
	CombatLogic combatLogic;


	public MonsterActions(CombatLogic combatLogic) { // adding skills to the player
		monsterSkills.add(new QuantumDistortion());
		this.combatLogic = combatLogic;
	}

	// Method to add skills to the actions
	public void addSkill(MonsterSkill skill) {
		monsterSkills.add(skill);
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
		BuffAndDeBuff<MonsterAttributes> defenseBuff = new MonsterDefenseBuff(buffDuration, Constants.DEFENSE_BUFF);
		defenseBuff.apply(monster.getMonsterAttributes());
		System.out.println(monster.getMonster() + " defends and gains " + Constants.DEFENSE_BUFF + " defense for " + buffDuration + " turns.");
		combatLogic.activeMonsterBuffs.add(defenseBuff);
	}


	protected void useSkill(Monster target, Player player) {
		try {
			List<MonsterSkill> monsterSkills = getMonsterSkills();
			if (!monsterSkills.isEmpty()) {
				// Generate a random index from the available skills
				Random rand = new Random();
				int skillIndex = rand.nextInt(monsterSkills.size());  // Random index between 0 and size - 1
				MonsterSkill skill = monsterSkills.get(skillIndex);// Retrieve the randomly selected skill and use it
				System.out.println("Monster uses skill: " + skill.getSkillName());
				skill.useSkill(target, player);  // Use the skill on the player
			} else {
				System.out.println("The monster has no skills available.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while using the skill: " + e.getMessage());
		}
	}

	// Method to get all the monster's skills
	private List<MonsterSkill> getMonsterSkills() {
		return monsterSkills;
	}

}
