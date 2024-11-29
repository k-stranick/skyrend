package dtcc.itn262.combat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TurnOrder  {

    private final Object entity;
    private final int priority;
	private final List<String> statusEffects; // Track buffs/debuffs

    public TurnOrder(Object entity, int priority) {
        this.entity = entity;
        this.priority = priority;
		this.statusEffects = new ArrayList<>();
    }

    public Object getEntity() {
        return entity;
    }

    public int getPriority() {
        return priority;
    }

    public static Comparator<TurnOrder> speedComparator() {
        return Comparator.comparingInt(TurnOrder::getPriority).reversed();
    }
}



