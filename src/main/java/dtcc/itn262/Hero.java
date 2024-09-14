package dtcc.itn262;
import java.util.Random;
public class Hero {
    private int strength;
    private int stamina;
    private String hero;

    public Hero(String name) {

        hero = !name.isEmpty() ? name : "Hero";
        strength = randSkill();
        stamina = randSkill();
        System.out.println("Hero: " + hero + "\nStrength: " + strength + "\nStamina: " + stamina);
    }

    private int randSkill() {
        Random rand = new Random();
        return rand.nextInt(10) + 8;
    }

    public int getStrength() {
        return strength;
    }

    public String getHero() {
        return hero;
    }

    public int getStamina() {
        return stamina;
    }
}
