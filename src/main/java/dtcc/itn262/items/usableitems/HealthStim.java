package dtcc.itn262.items.usableitems;

import dtcc.itn262.character.Player;

public class HealthStim extends UsableItems{
	public HealthStim(int value) {
		super("Health Stim", "Health potion", value);
	}

	@Override
	public void apply(Player player) {
		player.restoreHealth(getValue());
		System.out.println("You restored " + getValue() + " health!");
	}
}
