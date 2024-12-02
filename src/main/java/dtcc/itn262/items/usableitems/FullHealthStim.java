package dtcc.itn262.items.usableitems;

import dtcc.itn262.character.Player;

public class FullHealthStim extends UsableItems {
	public FullHealthStim(int value) {
		super("Full Stim", "Health potion", value);
	}

	@Override
	public void apply(Player player) {
		player.restoreHealth(getValue());
		System.out.println("You restored " + getValue() + " health");
	}
}
