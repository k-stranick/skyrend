/*

package dtcc.itn262.items.usableitems;

import dtcc.itn262.json.JsonSubtype;
import dtcc.itn262.json.JsonType;
import dtcc.itn262.character.Player;
import dtcc.itn262.items.Item;

@JsonType(
		property = "type",
		subtypes = {
				@JsonSubtype(value = FullHealthStim.class, name = "Full Stim", clazz = null),
				@JsonSubtype(value = AethericStim.class, name = "Aetheric Stim"),
				@JsonSubtype(value = HealthStim.class, name = "Health Stim")
		}
)
import Player;
import Item;

public abstract class HealingItems extends Item {
	private final int value;

	public HealingItems(String name, String type, int value, double dropRate) {
		super(name, type, dropRate);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	// Abstract method to define unique behavior for each item
	public abstract void apply(Player player);

	@Override
	public String getDescription() {
		return super.getDescription() + " (Restoration Value: " + value + ")";
	}
}
*/
