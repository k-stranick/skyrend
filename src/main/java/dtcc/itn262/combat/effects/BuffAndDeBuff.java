package dtcc.itn262.combat.effects;

public interface BuffAndDeBuff<T> {
    void apply(T target);

    void revert(T target);

    void decreaseDuration();

    boolean isExpired();

}
