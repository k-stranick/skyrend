package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.generic.Monster;

public class PhantomCode implements MonsterSkill {
	private static final int MANA_COST = 33;

	/**
	 * @return skillName the name of the skill
	 */
	@Override
	public String getSkillName() {
		return "Phantom Code";
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
		double damageMultiplier = 3;
		double damage = (monster.getMonsterAttributes().getMagic() * damageMultiplier) - target.getPlayerAttributes().getMagicDefense();
		if (monster.getMonsterAttributes().getMana() < getManaCost()) {
			System.out.println("Not enough mana to use " + getSkillName());

		} else if (monster.getMonsterAttributes().getMana() >= MANA_COST && damage > 0) {
			monster.getMonsterAttributes().setMana(monster.getMonsterAttributes().getMana() - MANA_COST);
			target.getPlayerAttributes().setHealth((int) Math.round(target.getPlayerAttributes().getHealth() - damage));
			System.out.println(monster.getMonster() + " uses " + getSkillName() + " on " + target.getHero() + " for " + damage + " damage.");
		} else {
			System.out.println(monster.getMonster() + " uses " + getSkillName() + " on " + target.getHero() + " but the attack is ineffective.");
		}
	}
}
