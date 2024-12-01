// GameSaver.java
package dtcc.itn262.SaveLoad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GameSaver {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void saveGame(GameState gameState, String filePath) {
		try (FileWriter writer = new FileWriter(filePath)) {
			GSON.toJson(gameState, writer);
			System.out.println("Game saved successfully!");
		} catch (IOException e) {
			System.err.println("Failed to save game: " + e.getMessage());
		}
	}

	public static GameState loadGame(String filePath) {
		try (FileReader reader = new FileReader(filePath)) {
			return GSON.fromJson(reader, GameState.class);
		} catch (IOException e) {
			System.err.println("Failed to load game: " + e.getMessage());
			return null;
		}
	}
}