// GameSaver.java
package dtcc.itn262.SaveLoad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtcc.itn262.character.Player;
import dtcc.itn262.utilities.display.TextDisplayUtility;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GameSaver {
	//private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(Player.class, new Player.PlayerSerializer())
			.registerTypeAdapter(Player.class, new Player.PlayerDeserializer())
			.setPrettyPrinting()
			.create();


	public static void saveGame(GameState gameState, String filePath) {
		System.out.println("Saving player: " + gameState.getPlayer().getHeroName());
		System.out.println("Current health: " + gameState.getPlayer().getPlayerAttributes().getHealth());
		System.out.println("Weapons: " + gameState.getPlayer().getPlayerWeaponList());
		System.out.println("Armor: " + gameState.getPlayer().getPlayerArmorList());
		System.out.println("Items: " + gameState.getPlayer().getPlayerItemsList());

		try (FileWriter writer = new FileWriter(filePath)) {
			GSON.toJson(gameState, writer);
			System.out.println("Game saved successfully!");
		} catch (IOException e) {
			System.err.println("Failed to save game: " + e.getMessage());
		}
	}

/*	public static GameState loadGame(String filePath) {
		try (FileReader reader = new FileReader(filePath)) {
			return GSON.fromJson(reader, GameState.class);
		} catch (IOException e) {
			System.err.println("Failed to load game: " + e.getMessage());
			return null;
		}
	}*/
public static GameState loadGame(String filePath) {
	try (FileReader reader = new FileReader(filePath)) {
		GameState gameState = GSON.fromJson(reader, GameState.class);
		gameState.updatePlayerEquippedWeaponStatus();
		displayGameState(gameState.getPlayer());
		return gameState;
	} catch (IOException e) {
		System.err.println("Failed to load game: " + e.getMessage());
		return null;
	}
}
	private static void displayGameState(Player player) {
		System.out.println("Game Loaded Successfully!");
		System.out.println(player);
		TextDisplayUtility.displayInventory(player);
	}

}