package dtcc.itn262.items.usableitems;

import dtcc.itn262.character.Player;

public class SystemRestore extends UsableItems {
	public SystemRestore(int value) {
		super("System Restore", "Full Restoration potion", value);
	}

	@Override
	public void apply(Player player) {
		player.restoreHealth(getValue());
		player.restoreMana(getValue());
		System.out.println("You restored " + getValue() + " health and aether!");
	}
}
