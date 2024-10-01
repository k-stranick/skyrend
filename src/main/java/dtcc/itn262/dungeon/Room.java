package dtcc.itn262.dungeon;

public class Room {
    private final String name;
    private final int n;
    private final int s;
    private final int w;
    private final int e;
    private boolean visited;
    private final String description;
    private final boolean isSpecial;

    public Room(String name, String description, int n, int s, int w, int e, boolean isSpecial) {
        this.name = name;
        this.description = description;
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
        this.visited = false;
        this.isSpecial = isSpecial;  // Set if the room has a fight or story dialogue

    }
public boolean isSpecial() {
        return isSpecial;
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
