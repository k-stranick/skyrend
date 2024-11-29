package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

import java.util.Random;

public class Zantetsuken implements IMonsterSkill {
	private static final int MANA_COST = 50; // Mana cost for using Zantetsuken
	private static final double BASE_ONE_HIT_KILL_CHANCE = 0.15; // Base 15% chance of instant kill
	private static final int BASE_DAMAGE = 160; // Base damage if one-hit kill doesn't occur

	@Override
	public String getSkillName() {
		return "Zantetsuken";
	}

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
			System.out.println(monster.getMonster() + " does not have enough mana to use " + getSkillName());
			return;
		}

		monster.getMonsterAttributes().setMana(monster.getMonsterAttributes().getMana() - MANA_COST);
		Random rand = new Random();

		// Factor player's luck into the one-hit kill chance (higher luck = lower chance of one-hit kill)
		double playerLuckFactor = target.getPlayerAttributes().getLuck() * 0.01; // 1% reduction in chance per luck point
		double adjustedOneHitKillChance = BASE_ONE_HIT_KILL_CHANCE - playerLuckFactor;

		// Ensure the chance is not negative
		adjustedOneHitKillChance = Math.max(0.01, adjustedOneHitKillChance); // Minimum 1% chance

		double chance = rand.nextDouble();

		// Check if the player gets one-hit killed
		if (chance < adjustedOneHitKillChance) {
			target.getPlayerAttributes().setHealth(0); // Instant kill
			System.out.println(monster.getMonster() + " uses " + getSkillName() + "! It's an instant kill! " + target.getHero() + " has been defeated.");
		} else {
			// If instant kill doesn't happen, deal base damage
			int damage = BASE_DAMAGE - target.getPlayerAttributes().getDefense();
			if (damage > 0) {
				target.getPlayerAttributes().setHealth((target.getPlayerAttributes().getHealth() + target.getPlayerAttributes().getDefense()) - damage);
				System.out.println(monster.getMonster() + " uses " + getSkillName() + " and deals " + damage + " damage to " + target.getHero());
			} else {
				System.out.println(monster.getMonster() + " uses " + getSkillName() + " but the attack was ineffective.");
			}
		}
	}
}
