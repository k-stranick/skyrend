package dtcc.itn262.items.usableitems;

import dtcc.itn262.character.Player;

public class FullAethericStim extends UsableItems {
	public FullAethericStim(int value) {
		super("Full Aetheric Stim", "Aether potion", value);
	}

	@Override
	public void apply(Player player) {
		player.restoreHealth(getValue());
		System.out.println("You restored " + getValue() + " aether!");
	}
}
