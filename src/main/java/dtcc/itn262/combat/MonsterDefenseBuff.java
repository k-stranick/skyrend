package dtcc.itn262.combat;

import dtcc.itn262.monster.MonsterAttributes;

public class MonsterDefenseBuff implements BuffAndDeBuff<MonsterAttributes> {
	private int duration;
	private final int defenseIncrease;

	public MonsterDefenseBuff(int duration, int defenseIncrease) {
		this.duration = duration;
		this.defenseIncrease = defenseIncrease;
	}

	@Override
	public void apply(MonsterAttributes target) {
		target.setDefense(target.getDefense() + defenseIncrease);
	}

	@Override
	public void revert(MonsterAttributes target) {
		target.setDefense(target.getDefense() - defenseIncrease);
	}

	@Override
	public void decreaseDuration() {
		duration--;
	}

	@Override
	public boolean isExpired() {
		return duration <= 0;
	}
}
