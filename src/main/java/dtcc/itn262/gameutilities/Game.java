package dtcc.itn262.gameutilities;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;

import java.util.Scanner;

public class Game {

    public Game() {
        startGame();
    }


    private void startGame() {
        Scanner input = new Scanner(System.in);

        // Prompt for the player's name first
        System.out.print("Enter your hero's name: ");
        String playerName = input.nextLine().trim();  // Read and trim player input

        // create the player with the provided name
        Player player = Player.createPlayer(playerName, 0, 0);
        System.out.println("Welcome, " + player.getHero() + "!");

        // Display player stats after the name has been entered
        System.out.println(player);  // This will invoke player.toString() and print the stats

        Maze m = new Maze();
        String value;
        boolean cont = true;

        do {
            System.out.print("? ");
            value = input.nextLine().trim();  // Read and trim player input
            if (value.equalsIgnoreCase("exit")) {
                cont = false;  // Exit the game loop
            } else if (value.equalsIgnoreCase("map")) {
                // Command to show the map
                m.displayMap();  // Show the current map and player's position
            } else if (value.equalsIgnoreCase("progress")) {
                // command to show current progress to escape
                m.showProgress();
            }else {
                // Handle movement commands
                m.move(value);  // Move player based on input
            }
        } while (cont);

    }
}
