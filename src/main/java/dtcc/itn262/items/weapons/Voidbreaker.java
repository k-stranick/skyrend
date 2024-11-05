package dtcc.itn262.items.weapons;

public class Voidbreaker implements IWeapon {
	private String name = "Voidbreaker";
	private int damage = 100;
	private String type = "Sword";
	private String description = "Forged from the essence of the Ghost Code and the heart of the AetherGrid, the Voidbreaker can tear through the fabric of reality itself, annihilating enemies in both the physical and digital realms.";

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

