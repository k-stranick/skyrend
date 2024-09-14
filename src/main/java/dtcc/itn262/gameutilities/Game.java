package dtcc.itn262.gameutilities;

import dtcc.itn262.character.Player;
import dtcc.itn262.dungeon.Maze;

import java.util.Scanner;

public class Game {
    public Game(String playerName) {
        Player player = new Player(playerName);
        System.out.println(player);
        startGame();

    }

    private void startGame() {
        Maze m = new Maze();
        Scanner input = new Scanner(System.in);
        String value = "";
        boolean cont = true;

        do {
            System.out.print("? ");
            value = input.nextLine();
            if (!value.equalsIgnoreCase("exit")) {
                m.move(value);
            } else {
                cont = false;
            }
        } while (cont);
    }
}
