package dtcc.itn262.items.weapons;

public class Aetherblade implements IWeapon {
	private String name = "Aetherblade";
	private int damage = 40;
	private String type = "Sword";
	private String description = "A sword forged with pure Aether energy, capable of slicing through both matter and code.";

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
