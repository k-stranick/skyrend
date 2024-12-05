/*
package dtcc.itn262.json;

import com.google.gson.*;
import dtcc.itn262.items.Item;
import dtcc.itn262.items.armor.Armor;
import dtcc.itn262.items.usableitems.AethericStim;
import dtcc.itn262.items.usableitems.HealthStim;
import dtcc.itn262.items.usableitems.SystemRestore;
import dtcc.itn262.items.weapons.Weapon;

import java.lang.reflect.Type;

*/
/**
 * Notes on custom deserilization class
 * https://www.baeldung.com/gson-deserialization-guide
 * *
 *//*


public class JsonCustomDeserializer implements JsonDeserializer<Item> {
	@Override
	public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		String itemType = jsonObject.get("type").getAsString();
		String name = jsonObject.get("name").getAsString();
		String description = jsonObject.get("description").getAsString();
		switch (itemType) {
			case "Weapon":
				int damage = jsonObject.get("damage").getAsInt();
				return new Weapon(name, damage, itemType, description);
			case "Armor":
				int defenseBoost = jsonObject.get("defense boost").getAsInt();
				return new Armor(name, defenseBoost, description);
			case "HealingItems":
				int value = jsonObject.get("value").getAsInt();
				// Choose concrete subclass based on item name
				if (name.equalsIgnoreCase("Health Stim")) {
					return new HealthStim(value);
				} else if (name.equalsIgnoreCase("Aetheric Stim")) {
					return new AethericStim(value);
				} else if (name.equalsIgnoreCase("System Restore")) {
					return new SystemRestore(value);
				} else {
					throw new JsonParseException("Unknown UsableItem: " + name);
				}
			default:
				throw new JsonParseException("Unknown item type: " + itemType);
		}
	}
}
*/
