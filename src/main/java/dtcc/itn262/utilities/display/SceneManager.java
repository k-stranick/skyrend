package dtcc.itn262.utilities.display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SceneManager {
	private static SceneManager instance;
	private final List<String> scenes = new ArrayList<>();

	public SceneManager() {
		loadScenes();
	}


	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}


	private void loadScenes() {
		String file = "src/resources/maze.txt";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("maze.txt");

		if (is == null) {
			throw new RuntimeException("file" + file + " not found.");
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			String line; // grab each line in the text file
			StringBuilder sceneBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {  // empty lines separate scenes
					scenes.add(sceneBuilder.toString());
					sceneBuilder.setLength(0);  // Reset the builder for the next scene
				} else {
					sceneBuilder.append(line).append("\n");
				}
			}
			// Add the last scene if the file doesn't end with an empty line
			if (!sceneBuilder.isEmpty()) {
				scenes.add(sceneBuilder.toString());
			}
		} catch (IOException e) {
			System.out.println("Error loading scenes: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}


	// Method to display a scene based on its index
	public void displayScene(int sceneIndex) {
		if (sceneIndex >= 0 && sceneIndex < scenes.size()) {
			System.out.println(scenes.get(sceneIndex));
		}
	}

}
