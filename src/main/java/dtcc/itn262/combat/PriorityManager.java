package dtcc.itn262.combat;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;
import java.util.PriorityQueue;

public class PriorityManager {
	private final PriorityQueue<TurnOrder> turnOrderQueue;

	public PriorityManager() {
		turnOrderQueue = new PriorityQueue<>(TurnOrder.speedComparator());
	}

	public void addTurn(Object entity, int speed, int cooldownModifier, boolean isHero, String name) {
		int priority = calculatePriority(entity);
		turnOrderQueue.add(new TurnOrder(entity, priority, isHero, name));
	}

	public TurnOrder getNextTurn() {
		return turnOrderQueue.poll();
	}

	public void refreshTurnOrder(Object entity, int newPriority) {
		turnOrderQueue.removeIf(turnOrder -> turnOrder.getEntity().equals(entity));
		boolean isPlayer = entity instanceof Player;
		String name = isPlayer ? ((Player) entity).getHeroName() : ((Monster) entity).getMonster();
		turnOrderQueue.add(new TurnOrder(entity, newPriority, isPlayer, name));
	}

	public boolean hasTurns() {
		return !turnOrderQueue.isEmpty();
	}

	private int calculatePriority(Object entity) {
		if (entity instanceof Player player) {
			return player.getPlayerAttributes().getSpeed(); // Speed directly determines priority
		} else if (entity instanceof Monster monster) {
			return monster.getMonsterAttributes().getSpeed();
		}
		return 0; // Default priority
	}

}
