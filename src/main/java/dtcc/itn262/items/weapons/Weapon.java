package dtcc.itn262.items.weapons;

import dtcc.itn262.items.Item;

public class Weapon extends Item {
	private int damage;


	public Weapon(String name, int damage, String type, String description) {
		super(name, type, description);
		this.damage = damage;
	}

/*	public Weapon(String name, int damage, String type, String description) {
		this.weapon = name;
		this.damage = damage;
		this.type = type;
		this.description = description;
	}*/


	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


	@Override
	public String getDescription() {
		return super.getDescription() + " (Damage: " + damage + ")";
	}
}