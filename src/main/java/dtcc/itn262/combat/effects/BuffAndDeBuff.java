package dtcc.itn262.combat.effects;

/**
 * Interface representing a buff or debuff effect that can be applied to a target.
 *
 * @param <T> the type of the target to which the effect is applied
 */
public interface BuffAndDeBuff<T> {

    /**
     * Applies the effect to the specified target.
     *
     * @param target the target to which the effect is applied
     */
    void apply(T target);

    /**
     * Reverts the effect from the specified target.
     *
     * @param target the target from which the effect is reverted
     */
    void revert(T target);

    /**
     * Decreases the duration of the effect.
     */
    void decreaseDuration();

    /**
     * Checks if the effect has expired.
     *
     * @return true if the effect has expired, false otherwise
     */
    boolean isExpired();
}