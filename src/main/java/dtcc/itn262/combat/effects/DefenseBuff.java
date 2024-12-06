package dtcc.itn262.combat.effects;

import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.monster.MonsterAttributes;

/**
 * Represents a defense buff that can be applied to players or monsters.
 *
 * @param <T> the type of the target (PlayerAttributes or MonsterAttributes)
 */
public class DefenseBuff<T> implements BuffAndDeBuff<T> {
    private int duration;
    private final int defenseIncrease;

    /**
     * Constructs a DefenseBuff with the specified duration and defense increase.
     *
     * @param duration the duration of the buff
     * @param defenseIncrease the amount by which defense is increased
     */
    public DefenseBuff(int duration, int defenseIncrease) {
        this.duration = duration;
        this.defenseIncrease = defenseIncrease;
    }

    /**
     * Applies the defense buff to the target.
     *
     * @param target the target to which the buff is applied
     */
    @Override
    public void apply(T target) {
        if (target instanceof PlayerAttributes player) {
            player.setDefense(player.getBaseDefense() + defenseIncrease);
        } else if (target instanceof MonsterAttributes monster) {
            monster.setDefense(monster.getDefense() + defenseIncrease);
        }
    }

    /**
     * Reverts the defense buff from the target.
     *
     * @param target the target from which the buff is reverted
     */
    @Override
    public void revert(T target) {
        if (target instanceof PlayerAttributes player) {
            player.setDefense(player.getBaseDefense());
        } else if (target instanceof MonsterAttributes monster) {
            monster.setDefense(monster.getDefense() - defenseIncrease);
        }
    }

    /**
     * Decreases the duration of the buff by one.
     */
    @Override
    public void decreaseDuration() {
        duration--;
    }

    /**
     * Checks if the buff has expired.
     *
     * @return true if the buff has expired, false otherwise
     */
    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
}