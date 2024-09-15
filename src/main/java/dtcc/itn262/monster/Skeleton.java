package dtcc.itn262.monster;

import java.util.Random;

public class Skeleton extends Monster {
    private int strength;
    private int stamina;
    private String skeleton;

    public Skeleton(String name) {
        super(name);

        skeleton = !name.isEmpty() ? name : "Skeleton";
        strength = randSkill();
        stamina = randSkill();
        System.out.println("Skeleton: " + skeleton + "\nStrength: " + strength + "\nStamina: " + stamina);
    }

    private int randSkill() {
        Random rand = new Random();
        return rand.nextInt(10) + 8;
    }

    public int getStrength() {
        return strength;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public int getStamina() {
        return stamina;
    }
}
