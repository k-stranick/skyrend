package dtcc.itn262.gameutilities;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;

import java.util.Scanner;

public class Game {

    public Game() {
        startGame();
    }


// TODO: WHY DOES THIS MAKE ME HIT ENTER TWICE
    private void startGame() {
        Scanner input = new Scanner(System.in);

        System.out.println("Debug: Prompting for player name");  // Debug statement

// Prompt for the player's name first
        System.out.print("Enter your hero's name: ");
        String playerName = input.nextLine().trim();  // Read and trim player input
        System.out.println("Debug: Player name entered: " + playerName);  // Debug statement

        // Now create the player with the provided name
        Player player = Player.createPlayer(playerName);
        System.out.println("Welcome, " + player.getHero() + "!");

        // Display player stats after the name has been entered
        System.out.println(player);  // This will invoke player.toString() and print the stats

        Maze m = new Maze();
        String value = "";
        boolean cont = true;

        do {
            System.out.print("? ");
            value = input.nextLine().trim();
            if (!value.equalsIgnoreCase("exit")) {
                m.move(value);
            } else {
                cont = false;
            }
        } while (cont);
    }
}
