package dtcc.itn262.items.weapons;

public class GhostReaver implements IWeapon {
	private String name = "Ghost Reaver";
	private int damage = 35;
	private String type = "Axe";
	private String description = "An axe that channels the Ghost Code, phasing in and out of reality to bypass defenses.";

	@Override
	public String getWeapon() {
		return name;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setWeapon(String weapon) {
		this.name = weapon;
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name + ": " + description + " (Damage: " + damage + ")";
	}
}
