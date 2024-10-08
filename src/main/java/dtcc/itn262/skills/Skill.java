package dtcc.itn262.skills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public interface Skill<T> {

    String getSkillName();

    int getManaCost();

    boolean isOnCooldown();

    void reduceCooldown(); // I do not think this should be in the interface should be in turn logic??

    void setCooldown();

    void useSkill(T target); // player targeting monster with skills

    int getCurrentCooldown();

}
