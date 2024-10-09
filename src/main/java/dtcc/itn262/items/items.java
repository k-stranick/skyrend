package dtcc.itn262.items;

public class items {
    private final String name;
    private final int value;
    private final int weight;

    public items(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
