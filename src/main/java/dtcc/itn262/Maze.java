package dtcc.itn262;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Maze {
    boolean keyFound = false;
    private int location;  // your position for the scene
    private List<String> scenes = new ArrayList<>();
    private Stack<String> moves = new Stack<>();

    public Maze() {
        location = 0;
        loadScenes();
        scene();
    }

    private void scene() {
        System.out.println(scenes.get(location)); // looking through the array list and grabbing the scene
    }

    public void search() {
        Random rand = new Random();
        int intSearch = rand.nextInt(10);
        if (intSearch > 7) {

            keyFound = true;
            System.out.println("You found the key!");
            moves.push("keyFound");

        } else {
            System.out.println("You did not find the key.");
            moves.push("no key found");
        }
    }

    public void move(String input) {
        try {
            char value = input.toLowerCase().charAt(0);  // character is a byte that represents a letter

            switch (value) {
                case 'n':
                    if (location == scenes.size() - 1) {
                        if (!keyFound) {
                            System.out.println("You need to find the key first!");
                        } else {
                            System.out.println("You have escaped the maze!");
                            moves.push("escaped");

                        }
                        return;
                    }
                    moves.push("north");
                    location++;   //location += 1
                    break;

                case 's':
                    if (location == 0) {
                        System.out.println("You can't go back to the bar. it's closed");
                        return;
                    }
                    moves.push("south");
                    location--;
                    break;
                case 'l':
                    search();
                    break;
                case 'm':
                    System.out.println("You have moved" + moves.size());
                    moves.forEach(i -> {
                        System.out.println(i);
                    });
                    break;
                case 'p':
                    moves.pop();
                    break;
                case 't':
                    System.out.println(moves.peek());
                    break;
                case 'r':
                    location = 0;
                    moves.clear();
                    keyFound = false;
                    System.out.println("You have reset the game");
                    break;
                default:
                    System.out.println("What did you mean? Invalid move");
                    break;
            }


        } catch (Exception ex) {
            // you in business store it into an Error Log File
            System.out.println(ex.getMessage());
        }
    }

    private void loadScenes() {
        String file = "src/maze.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; // grab each line  in the text file
            while ((line = br.readLine()) != null) {
                scenes.add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


