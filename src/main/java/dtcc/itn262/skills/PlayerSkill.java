package dtcc.itn262.skills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;


// interface Skill defines what a skill MUST do but does not specify how to do them
public interface PlayerSkill {
    String getSkillName();

    int getManaCost();

    boolean isOnCooldown();

    void reduceCooldown(); // I do not think this should be in the interface should be in turn logic??

    void setCooldown();

    void useSkill(Player player, Monster target); // player targeting monster with skills

    int getCurrentCooldown();
}
