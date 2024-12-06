package dtcc.itn262.items.weapons;

import dtcc.itn262.items.Item;

public class Weapon extends Item {
	private int damage;
	boolean isEquipped = false;

	public Weapon(String name, int damage, String type, String description, double dropRate) {
		super(name, type, description, dropRate);
		this.damage = damage;
		this.isEquipped = false;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean equipped) {
		isEquipped = equipped;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public Weapon clone() {
		return (Weapon) super.clone();
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " (Damage: " + damage + ")";
	}
}