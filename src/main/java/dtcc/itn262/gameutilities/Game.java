package dtcc.itn262.gameutilities;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
private final List<String> scenes = new ArrayList<>();
    public Game() {
        startGame();
    }


    private void startGame() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter your hero's name: "); // Prompt for the player's name first
            String playerName = input.nextLine().trim();  // Read and trim player input
            Player player = Player.createPlayer(playerName, 0, 0);  // create the player with the provided name
            System.out.println("Welcome, " + player.getHero() + "!");
            System.out.println(player); //This will invoke player.toString() and print the stats after the name has been entered
            Maze m = Maze.getInstance(player); // ensures I am using the same instance of Maze by using a Singleton pattern
            String value;
            boolean cont = true;
            loadScenes();
            do {
                System.out.print("?: "); // Prompt for the player's input
                value = input.nextLine().trim(); // Read and trim player input
                handleInput(value, m);

                // Check for win or lose conditions
                if (Validation.checkWinCondition(m)) {
                    System.out.println("Congratulations! You've won the game!");
                    cont = false;  // End the game loop
                } else if (!player.isAlive()) {
                    System.out.println("Game Over! You have lost the game.");
                    cont = false;  // End the game loop
                }
            } while (cont);
        }
        System.out.println("Thank You for playing!");
        System.exit(0);

    }

    private void handleInput(String value, Maze m) { // TODO TEST MADE THIS RETURN VOID
        Command command = parseCommand(value);
        if (command == null) {
            System.out.println("Invalid command. Please try again.");
            return; // Return true if the command is invalid and continue the loop
        }
        switch (command) {
            case EXIT:
                System.out.println("Thank you for playing!");
                return;
            case MAP:
                m.displayMap();
                break;
            case PROGRESS:
                DisplayUtility.showProgress(m.getUniqueVisitedRooms());
                break;
            case HELP:
                System.out.println("Commands: map, progress, help, exit, north, south, east, west");
                break;
            case HISTORY:
                DisplayUtility.showMoveHistory(m.getMoveHistory());
                break;
            case EAST, NORTH, SOUTH, WEST:
                m.move(value);
                break;
            default:
                System.out.println("Invalid command. Please try again.");
        }
    }


    private void loadScenes() {
        String file = "src/maze.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; // grab each line in the text file
            while ((line = br.readLine()) != null) {
                scenes.add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Command parseCommand(String value) {
        try {
            return Command.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;  // Return null if the command is invalid
        }
    }

}
