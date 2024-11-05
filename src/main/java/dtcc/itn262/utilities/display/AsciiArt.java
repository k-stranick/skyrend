package dtcc.itn262.utilities.display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AsciiArt {
	// Method to display ASCII art
	public static void displayAsciiArt(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
