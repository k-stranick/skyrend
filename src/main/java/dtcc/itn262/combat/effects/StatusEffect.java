package dtcc.itn262.combat.effects;

import dtcc.itn262.character.Player;

/**
 * Represents a status effect, such as ongoing damage.
 */
public class StatusEffect {
	private final String name;
	private final int damagePerTurn;
	private int remainingTurns;

	public StatusEffect(String name, int damagePerTurn, int duration) {
		this.name = name;
		this.damagePerTurn = damagePerTurn;
		this.remainingTurns = duration;
	}

	/**
	 * Applies the effect on each turn.
	 *
	 * @param player The hero that is affected.
	 */
	public void applyEffect(Player player) {
		if (remainingTurns > 0) {
			System.out.println(name + " deals " + damagePerTurn + " damage to " + player.getHero());
			player.takeDamage(damagePerTurn);
			remainingTurns--;
		} else {
			System.out.println(name + " has worn off.");
		}
	}

	public boolean isEffectActive() {
		return remainingTurns > 0;
	}

	public String getName() {
		return name;
	}
}

