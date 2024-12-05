package dtcc.itn262.items.armor;

import dtcc.itn262.items.Item;

public class Armor extends Item {
	private int defenseBoost;

	public Armor(String armor, int defenseBoost, String description, double dropRate) {
		super(armor, description, dropRate);
		this.defenseBoost = defenseBoost;

	}


	public int getDefenseBoost() {
		return defenseBoost;
	}


	public void setDefense(int defense) {
		this.defenseBoost = defense;
	}

	@Override
	public Armor clone() {
		return (Armor) super.clone();
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " (Defense Boost: " + defenseBoost + ")";
	}
}
