package dtcc.itn262.skills;

import dtcc.itn262.character.PlayerAttributes;

public class Skills {
    private String skillName;
    private int attackDamage;
    private int manaCost;
    private int cooldown;
    private int currentCooldown;

    public Skills(int attackDamage, int manaCost, int cooldown) {
        this.attackDamage = attackDamage;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.currentCooldown = 0; // initial state of cooldown
    }

    public boolean isOnCooldown() {
        return currentCooldown > 0;
    }

    public void reduceCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    public void useSkill(PlayerAttributes playerAttributes) {
        if (playerAttributes.getMana() > manaCost && !isOnCooldown()) {
            playerAttributes.setMana(playerAttributes.getMana() - manaCost);
        }
    }


}
