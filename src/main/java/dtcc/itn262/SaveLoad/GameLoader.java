package dtcc.itn262.SaveLoad;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class GameLoader {
	public static GameState loadGame(String filePath) {
		Gson gson = new Gson();
		try (FileReader reader = new FileReader(filePath)) {
			return gson.fromJson(reader, GameState.class);
		} catch (IOException e) {
			System.err.println("Failed to load game: " + e.getMessage());
			return null;
		}
	}
}