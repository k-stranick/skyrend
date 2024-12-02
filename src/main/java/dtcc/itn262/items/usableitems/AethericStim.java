package dtcc.itn262.items.usableitems;

import dtcc.itn262.character.Player;

public class AethericStim extends UsableItems {
	public AethericStim(int value) {
		super("Aetheric Stim", "Aether potion", value);
	}

	@Override
	public void apply(Player player) {
		player.restoreMana(getValue());
		System.out.println("You restored " + getValue() + " aether!");
	}
}
