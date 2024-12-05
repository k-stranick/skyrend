/*
package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;
// maybe interfaces are too much

public class Heal implements IMonsterSkill {
	private static final int MANA_COST = 33;
	private int currentCooldown = 0;

	@Override
	public String getSkillName() {
		return "Heal";
	}

	@Override
	public int getManaCost() {
		return MANA_COST;
	}

	*/
/**
	 * @param monster
	 * @param target
	 *//*

	@Override
	public void useSkill(Monster monster, Player target) {

	}
	public boolean isHealing() {
		return true;
	}

	*/
/**
	 * @param monster
	 *//*


	public void useSkill(Monster monster) {
		double healMultiplier = 3;
		double heal = monster.getMonsterAttributes().getMagic() * healMultiplier;
		if (monster.getMonsterAttributes().getMana() < getManaCost()) {
			System.out.println("Not enough mana to use " + getSkillName());
		} else if (monster.getMonsterAttributes().getMana()  >= MANA_COST) {
			monster.getMonsterAttributes().setMana(monster.getMonsterAttributes().getMana() - MANA_COST);
			monster.getMonsterAttributes().setActiveHealth((int) Math.round(monster.getMonsterAttributes().getActiveHealth() + heal));
			System.out.println(monster.getMonster() + " uses " + getSkillName() + " for " + heal + " health.");
		}
	}

}
*/
