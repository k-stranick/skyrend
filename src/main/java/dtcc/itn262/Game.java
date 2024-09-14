package dtcc.itn262;

import java.util.Scanner;

public class Game {
    public Game(String playerName) {
        Hero hero = new Hero(playerName);
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
