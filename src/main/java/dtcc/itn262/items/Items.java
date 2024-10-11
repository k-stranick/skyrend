package dtcc.itn262.items;


import dtcc.itn262.character.Player;

public class Items {
	private final String name;
	private final int value;
	private final String type;


	public Items(String name, String type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public int getValue() {
		return value;
	}


	// Method to apply the item's effect to the player
	public void apply(Player player) {
		switch (type) {
			case "Health Potion":
				player.restoreHealth(value);  // Calls the method to restore health
				break;
			case "Mana Potion":
				player.restoreMana(value);  // Calls the method to restore mana
				break;
			// Add other item types here or try and utilize a factory pattern
			default:
				System.out.println("Unknown item type");
				break;
		}
	}
}
