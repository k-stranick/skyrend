package dtcc.itn262.items.weapons;

public class Weapon {
	private String weapon;
	private int damage;
	private String type;
	private String description;

	public Weapon(String name, int damage, String type, String description) {
		this.weapon = name;
		this.damage = damage;
		this.type = type;
		this.description = description;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return weapon + ": " + description + " (Damage: " + damage + ")";
	}
}