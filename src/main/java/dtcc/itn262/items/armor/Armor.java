package dtcc.itn262.items.armor;

public class Armor {
	private String armor;
	private int defenseBoost;
	private String description;

	public Armor(String armor, int defenseBoost, String description) {
		this.armor = armor;
		this.defenseBoost = defenseBoost;
		this.description = description;
	}

	public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}

	public int getDefenseBoost() {
		return defenseBoost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDefense(int defense) {
		this.defenseBoost = defense;
	}

	@Override
	public String toString() {
		return armor + ": " + description + " (Defense Boost: " + defenseBoost + ")";
	}
}
