package dtcc.itn262.skills.monsterskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.monster.generic.Monster;

public class GlitchSwipe implements MonsterSkill {
	private int damage;

	public GlitchSwipe(int damage) {
		this.damage = damage;
	}

	@Override
	public void apply(MonsterAttributes target) {
		target.setHealth(target.getHealth() - damage);
	}

	@Override
	public void decreaseDuration() {
		// Do nothing
	}

	@Override
	public boolean isExpired() {
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public String getSkillName() {
		return "";
	}

	/**
	 * @return
	 */
	@Override
	public int getManaCost() {
		return 0;
	}

	/**
	 * @param monster
	 * @param target
	 */
	@Override
	public void useSkill(Monster monster, Player target) {

	}
}
