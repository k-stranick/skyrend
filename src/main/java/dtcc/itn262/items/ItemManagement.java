package dtcc.itn262.items;

import dtcc.itn262.items.armor.*;
import dtcc.itn262.items.usableitems.UsableItems;
import dtcc.itn262.items.weapons.*;

import java.util.Random;

public class ItemManagement {
	// Arrays for predefined items
	private final UsableItems[] usableItems = {
			new UsableItems("Health Stim", "Health potion", 40),
			new UsableItems("Aetheric Stim", "Mana potion",40),
			new UsableItems("Full Health Stim", "Health potion", 80),
			new UsableItems("Full Aetheric Stim", "Mana potion", 80),
			new UsableItems("System Restore", "Full restore", 100)
	};

	private final Weapon[] predefinedWeapons = {
			new GhostReaver(),
			new Aetherblade(),
			new GhostCodeGauntlets(),
			new PhantomBlade(),
			new NeonDagger(),
			new NightClub(),
			new PlasmaSaber()
	};

	private final Armor[] predefinedArmor = {
			new AetherReaverSuit(),
			new PhantomCloak(),
			new DreamWeaveCloak(),
			new GhostCodeArmor(),
			new NeonArmor(),
			new NeonShroud()
	};

	private final Random random = new Random();

	// Generic method to pick a random item from an array
	private <T> T pickRandomItem(T[] items) {
		return items[random.nextInt(items.length)];
	}

	// Generate a random item (Weapon, Armor, or UsableItems)
	public Item generateRandomItem() {
		int itemType = random.nextInt(3); // 0 for weapon, 1 for armor, 2 for usable items
		return switch (itemType) {
			case 0 -> pickRandomItem(predefinedWeapons);
			case 1 -> pickRandomItem(predefinedArmor);
			case 2 -> pickRandomItem(usableItems);
			default -> throw new IllegalStateException("Unexpected value: " + itemType);
		};
	}

	// Access specific item lists
	public UsableItems[] getUsableItems() {
		return usableItems;
	}

	public Weapon[] getWeapons() {
		return predefinedWeapons;
	}

	public Armor[] getArmor() {
		return predefinedArmor;
	}
}

