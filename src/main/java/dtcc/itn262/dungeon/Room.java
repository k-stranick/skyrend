package dtcc.itn262.dungeon;

public class Room {
    private String name;
    private int n, s, w, e;
    private boolean visited;
    private String description;

    public Room(String name, String description, int n, int s, int w, int e) {
        this.name = name;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
        this.visited = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "This is " + description;
    }

    public int getN() {
        return n;
    }

    public int getS() {
        return s;
    }

    public int getW() {
        return w;
    }

    public int getE() {
        return e;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String toString() {
        return "Room: " + name + "\nDescription: " + description + "\nN: " + n + "\nS: " + s + "\nW: " + w + "\nE: " + e;
    }
}
