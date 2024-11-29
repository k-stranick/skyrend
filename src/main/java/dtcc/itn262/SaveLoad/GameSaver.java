package dtcc.itn262.SaveLoad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {
	public static void saveGame(GameState gameState, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(gameState, writer);
			System.out.println("Game saved successfully!");
		} catch (IOException e) {
			System.err.println("Failed to save game: " + e.getMessage());
		}
	}
}

