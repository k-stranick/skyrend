package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.generic.Monster;

public class DataDrain implements MonsterSkill {
	private static final int MANA_COST = 33;
	private static final double HEALTH_ABSORB_PERCENTAGE = 0.2; // 20% of the player's health
	private static final double MANA_ABSORB_PERCENTAGE = 0.1; // 10% of the player's mana

	/**
	 * @return skillName the name of the skill
	 */
	@Override
	public String getSkillName() {
		return "Data Drain";
	}

	/**
	 * @return manaCost the cost of the skill
	 */
	@Override
	public int getManaCost() {
		return MANA_COST;
	}

	/**
	 * @param monster
	 * @param target
	 */
	@Override
	public void useSkill(Monster monster, Player target) {
		if (monster.getMonsterAttributes().getMana() < getManaCost()) {
			System.out.println("Not enough mana to use " + getSkillName());
			return;
		}

		monster.getMonsterAttributes().setMana(monster.getMonsterAttributes().getMana() - MANA_COST);

		double healthToAbsorb = target.getPlayerAttributes().getHealth() * HEALTH_ABSORB_PERCENTAGE;
		double manaToAbsorb = target.getPlayerAttributes().getMana() * MANA_ABSORB_PERCENTAGE;

		target.getPlayerAttributes().setHealth((int) (target.getPlayerAttributes().getHealth() - healthToAbsorb));
		target.getPlayerAttributes().setMana((int) (target.getPlayerAttributes().getMana() - manaToAbsorb));

		monster.getMonsterAttributes().setHealth((int) (monster.getMonsterAttributes().getHealth() + healthToAbsorb));
		monster.getMonsterAttributes().setMana((int) (monster.getMonsterAttributes().getMana() + manaToAbsorb));

		System.out.println(monster.getMonster() + " uses " + getSkillName() + " on " + target.getHero() + " and absorbs " + healthToAbsorb + " health and " + manaToAbsorb + " mana.");
	}
}