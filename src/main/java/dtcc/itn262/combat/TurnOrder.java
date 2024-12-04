package dtcc.itn262.combat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TurnOrder  {

    private final Object entity;
    private final int priority;
    private final boolean isPlayer; // Track if entity is player or monster
    private final String name; // Track entity name
	//private final List<String> statusEffects; // Track buffs/debuffs

    public TurnOrder(Object entity, int priority, boolean isPlayer, String name) {
        this.entity = entity;
        this.priority = priority;
		this.isPlayer = isPlayer;
        this.name = name;
		//this.statusEffects = new ArrayList<>();
    }

    public Object getEntity() {
        return entity;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
    public String getName() { // Add getName method
        return name;
    }
    public static Comparator<TurnOrder> speedComparator() {
        return Comparator.comparingInt(TurnOrder::getPriority).reversed();
    }
}



