package dtcc.itn262.combat.effects;

import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.monster.MonsterAttributes;
import dtcc.itn262.utilities.gamecore.Constants;

public class DefenseBuff<T> implements BuffAndDeBuff<T> {
    private int duration;
    private int defenseIncrease;

    public DefenseBuff(int duration, int defenseIncrease) {  //TODO
        this.duration = duration;
        this.defenseIncrease = defenseIncrease;
    }

    @Override
    public void apply(T target) {
        if (target instanceof PlayerAttributes player) {
			player.setDefense(player.getDefense() + defenseIncrease);
        } else if (target instanceof MonsterAttributes monster) {
			monster.setDefense(monster.getDefense() + defenseIncrease);
        }
    }

    @Override
    public void revert(T target) {
        if (target instanceof PlayerAttributes player) {
			player.setDefense(player.getDefense() - defenseIncrease);
        } else if (target instanceof MonsterAttributes monster) {
			monster.setDefense(monster.getDefense() - defenseIncrease);
        }
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
