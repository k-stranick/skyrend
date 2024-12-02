package dtcc.itn262.items.usableitems;


import dtcc.itn262.character.Player;
import dtcc.itn262.items.Item;

public class UsableItems extends Item {
	private final int value;
	private final int value2;


	public UsableItems(String name, String type, int value) {
		super(name, type);
		this.value = value;
		this.value2 = 0;
	}


	public UsableItems(String name, String type, int value, int value2) {
		super(name, type);
		this.value = value;
		this.value2 = value2;
	}




	public int getValue() {
		return value;
	}

	public int getValue2() {
		return value2;
	}

	// Method to apply the item's effect to the player
	public void apply(Player player) {
		switch (getType()) {
			case "Health Potion", "Super Health Potion":
				player.restoreHealth(value);  // Calls the method to restore health
				break;
			case "Mana Potion", "Super Mana Potion":
				player.restoreMana(value);  // Calls the method to restore mana
				break;
			case "Elixir":
				player.restoreHealth(value);  // Calls the method to restore health
				player.restoreMana(value);  // Calls the method to restore mana
				break;
			// Add other item types here or try and use a factory pattern
			default:
				System.out.println("Unknown item type");
				break;
		}
	}
	@Override
	public String toString() {
		return "Item: " + getName();
	}
}