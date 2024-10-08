package dtcc.itn262.combat;

import dtcc.itn262.character.PlayerAttributes;
import dtcc.itn262.gameutilities.Constants;

public class DefenseBuff implements BuffAndDeBuff<PlayerAttributes> {
    private int duration;
    // final int defenseIncrease;

    public DefenseBuff(int duration, int defenseIncrease) {  //TODO
        this.duration = duration;
        //this.defenseIncrease = defenseIncrease;
    }

    @Override
    public void apply(PlayerAttributes target) {
        target.setDefense(target.getDefense() + Constants.DEFENSE_BUFF);
    }

    @Override
    public void revert(PlayerAttributes target) {
        target.setDefense(target.getDefense() - Constants.DEFENSE_BUFF);
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
