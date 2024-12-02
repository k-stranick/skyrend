package dtcc.itn262.items.armor;

import dtcc.itn262.items.Item;

public class Armor extends Item {
	private int defenseBoost;

	public Armor(String armor, int defenseBoost, String description) {
		super(armor, description);
		this.defenseBoost = defenseBoost;

	}


	public int getDefenseBoost() {
		return defenseBoost;
	}


	public void setDefense(int defense) {
		this.defenseBoost = defense;
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " (Defense Boost: " + defenseBoost + ")";
	}
}
