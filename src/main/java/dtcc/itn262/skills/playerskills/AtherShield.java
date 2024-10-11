package dtcc.itn262.skills.playerskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.generic.Monster;

public class AtherShield implements PlayerSkill {
	private static final int MANA_COST = 33;
	private int currentCooldown = 0;

	@Override
	public String getSkillName() {
		return "Ather Shield";
	}

	@Override
	public int getManaCost() {
		return MANA_COST;
	}

	@Override
	public boolean isOnCooldown() {
		return currentCooldown > 0;
	}

	@Override
	public void reduceCooldown() {
		if (currentCooldown > 0) {
			currentCooldown--;
		}
	}

	/**
	 *
	 */
	@Override
	public void setCooldown() {

	}

	public void useSkill(Player player, Monster attacker) {
		if (player.getPlayerAttributes().getMana() < MANA_COST) {
			System.out.println("Not enough mana to use " + getSkillName());
			return;
		}

		player.getPlayerAttributes().setMana(player.getPlayerAttributes().getMana() - MANA_COST);
		System.out.println(player.getHero() + " uses " + getSkillName() + " and absorbs incoming damage!");

		// Apply shield effect
		player.setShielded(true);
	}

	/**
	 * @return
	 */
	@Override
	public int getCurrentCooldown() {
		return 0;
	}

	public void absorbAndReflectDamage(Player player, Monster attacker, double damage) {
		double absorbedDamage = damage;
		double reflectedDamage = damage * 0.15;

		player.getPlayerAttributes().setHealth((int) (player.getPlayerAttributes().getHealth() - absorbedDamage));
		attacker.getMonsterAttributes().setHealth((int) (attacker.getMonsterAttributes().getHealth() - reflectedDamage));

		System.out.println(player.getHero() + " absorbs " + absorbedDamage + " damage and reflects " + reflectedDamage + " damage back to " + attacker.getMonster() + "!");
	}
}