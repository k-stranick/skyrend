package dtcc.itn262.weapons;

public class Weapons {
// static means you can call a method directly from class without creating an object
    private String weapon;
    private int damage;
    private String type;
    private String description;

    public Weapons(String weapon, int damage, String type, String description) {
        this.weapon = weapon;
        this.damage = damage;
        this.type = type;
        this.description = description;
    }

    public String getWeapon() {
        return weapon;
    }
    public int getDamage() {
        return damage;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String toString() {
        return "Weapon: " + weapon + "\nDamage: " + damage + "\nType: " + type + "\nDescription: " + description;
    }


}
