package dtcc.itn262.combat;

import dtcc.itn262.character.Player;

import java.util.PriorityQueue;

public class PriorityManager {
	private final PriorityQueue<TurnOrder> turnOrderQueue;

	public PriorityManager() {
		turnOrderQueue = new PriorityQueue<>(TurnOrder.speedComparator());
	}

	public void addTurn(Object entity, int speed, int cooldownModifier, boolean isHero, String name) {
		int priority = (speed * 2) - cooldownModifier; // Weighted formula
		turnOrderQueue.add(new TurnOrder(entity, priority, isHero, name));
	}

	public TurnOrder getNextTurn() {
		return turnOrderQueue.poll();
	}

	public void refreshTurnOrder(Object entity, int newPriority) {
		turnOrderQueue.removeIf(turnOrder -> turnOrder.getEntity().equals(entity));
		turnOrderQueue.add(new TurnOrder(entity,newPriority,entity instanceof Player, entity.toString()));
	}

	public boolean hasTurns() {
		return !turnOrderQueue.isEmpty();
	}
}
