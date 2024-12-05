package dtcc.itn262.skills.playerskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public class Heal implements PlayerSkill {
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

	@Override
	public boolean isHealingSkill() {
		return true;
	}

	@Override
	public void setCooldown() {
		currentCooldown = 3;
	}

	/**
	 * @param player
	 * @param target
	 */
	@Override
	public void useSkill(Player player, Monster target) {

	}

	/**
	 * @param player
	 */
	@Override
	public void useSKill(Player player) {
		double healMultiplier = 3;
		double heal = player.getPlayerAttributes().getMagic() * healMultiplier;
		if (player.getPlayerAttributes().getMana() < getManaCost()) {
			System.out.println("Not enough mana to use " + getSkillName());
		} else if (player.getPlayerAttributes().getMana() >= MANA_COST) {
			player.getPlayerAttributes().setMana(player.getPlayerAttributes().getMana() - MANA_COST);
			player.getPlayerAttributes().setHealth((int) Math.round(player.getPlayerAttributes().getHealth() + heal));
			System.out.println(player.getHeroName() + " uses " + getSkillName() +" for " + heal + " health.");
		}
	}

	/**
	 * @param player
	 *
	 */

	/**
	 * @return
	 */
	@Override
	public int getCurrentCooldown() {
		return 0;
	}
}
