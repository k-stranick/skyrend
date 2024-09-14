package dtcc.itn262.monster;

import java.util.Random;

public class Dragon extends Monster {
    private int strength;
    private int stamina;
    private String dragon;

    public Dragon(String name) {
        super(name);

        dragon = !name.isEmpty() ? name : "Dragon";
        strength = randSkill();
        stamina = randSkill();
        System.out.println("Dragon: " + dragon + "\nStrength: " + strength + "\nStamina: " + stamina);
    }

    private int randSkill() {
        Random rand = new Random();
        return rand.nextInt(10) + 8;
    }

    public int getStrength() {
        return strength;
    }

    public String getDragon() {
        return dragon;
    }

    public int getStamina() {
        return stamina;
    }
}
