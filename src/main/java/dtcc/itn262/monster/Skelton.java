package dtcc.itn262.monster;

import java.util.Random;

public class Skelton extends Monster {
    private int strength;
    private int stamina;
    private String skelton;

    public Skelton(String name) {
        super(name);

        skelton = !name.isEmpty() ? name : "Skelton";
        strength = randSkill();
        stamina = randSkill();
        System.out.println("Skelton: " + skelton + "\nStrength: " + strength + "\nStamina: " + stamina);
    }

    private int randSkill() {
        Random rand = new Random();
        return rand.nextInt(10) + 8;
    }

    public int getStrength() {
        return strength;
    }

    public String getSkelton() {
        return skelton;
    }

    public int getStamina() {
        return stamina;
    }
}
