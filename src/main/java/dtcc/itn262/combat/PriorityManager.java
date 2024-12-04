
package dtcc.itn262.combat;
/*
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

	public void removeTurn(Monster monster) {
		turnOrderQueue.removeIf(turnOrder -> turnOrder.getEntity().equals(monster));
	}

	public void clearQueue() {
		turnOrderQueue.clear();
	}
}
*/

import java.util.List;
import java.util.PriorityQueue;
import dtcc.itn262.character.Player;
import dtcc.itn262.monster.Monster;

public class PriorityManager {
	private final PriorityQueue<TurnOrder> turnOrderQueue = new PriorityQueue<>(TurnOrder.speedComparator());

	// Singleton-like instance (optional)
	private static PriorityManager instance;

	// Constructor
	public PriorityManager() {
		if (instance != null) {
			throw new IllegalStateException("Only one instance of PriorityManager is allowed!");
		}
		instance = this;
	}

	public static PriorityManager getInstance() {
		if (instance == null) {
			instance = new PriorityManager();
		}
		return instance;
	}

	public void addTurn(Object entity, int priority, int index, boolean isPlayer, String name) {
		if (turnOrderQueue.stream().noneMatch(turnOrder -> turnOrder.getEntity().equals(entity))) {
			turnOrderQueue.add(new TurnOrder(entity, priority, isPlayer, name));
			System.out.printf("Added turn for: %s with priority: %d%n", name, priority);
		} else {
			System.out.printf("Entity %s is already in the queue.%n", name);
		}
	}

	public TurnOrder getNextTurn() {
		if (turnOrderQueue.isEmpty()) {
			System.out.println("Turn order queue is empty!");
		} else {
			System.out.println("Next turn: " + turnOrderQueue.peek().getName());
		}
		return turnOrderQueue.peek();
	}

	public void removeTurn(Object entity) {
		turnOrderQueue.removeIf(turnOrder -> turnOrder.getEntity().equals(entity));
	}

	public void refreshTurnOrder(Object entity, int newPriority) {
		turnOrderQueue.removeIf(turnOrder -> turnOrder.getEntity().equals(entity));
		turnOrderQueue.add(new TurnOrder(entity, newPriority, entity instanceof Player, entity instanceof Player ? ((Player) entity).getHeroName() : ((Monster) entity).getMonster()));
	}

	public void refreshQueueForBuffsAndDebuffs(Object entity) {
		int newPriority = (entity instanceof Player)
				? ((Player) entity).getPlayerAttributes().getSpeed()
				: ((Monster) entity).getMonsterAttributes().getSpeed();

		this.refreshTurnOrder(entity, newPriority);
	}

	public void clearQueue() {
		turnOrderQueue.clear();
	}

	public PriorityQueue<TurnOrder> getCurrentQueue() {
		return turnOrderQueue;
	}

	public void debugQueue() {
		System.out.println("Current Turn Queue:");
		for (TurnOrder turn : turnOrderQueue) {
			System.out.printf("Entity: %s, Speed: %d%n", turn.getName(), turn.getPriority());
		}
	}

	public void processTurn() {
		TurnOrder completedTurn = turnOrderQueue.poll(); // Remove the head of the queue
		if (completedTurn != null) {
			System.out.printf("Processing turn for: %s%n", completedTurn.getName());
			Object entity = completedTurn.getEntity();
			if (entity instanceof Player player && player.isAlive()) {
				handlePlayerTurn(player);
			} else if (entity instanceof Monster monster && monster.isAlive()) {
				handleMonsterTurn(monster);
			} else {
				System.out.printf("Entity %s is no longer valid and will not rejoin the queue.%n", completedTurn.getName());
			}
		} else {
			System.out.println("Turn order queue is empty!");
		}
	}

	private void handlePlayerTurn(Player player) {
		// Implement player turn logic here
		// Re-add player to the queue if they are still alive and have actions to perform
		if (player.isAlive()) {
			addTurn(player, player.getPlayerAttributes().getSpeed(), 0, true, player.getHeroName());
		}
	}

	private void handleMonsterTurn(Monster monster) {
		// Implement monster turn logic here
		// Re-add monster to the queue if they are still alive and have actions to perform
		if (monster.isAlive()) {
			addTurn(monster, monster.getMonsterAttributes().getSpeed(), 0, false, monster.getMonster());
		}
	}
	public void validateQueue() {
		turnOrderQueue.removeIf(turnOrder -> {
			Object entity = turnOrder.getEntity();
			if (entity instanceof Player player) {
				return !player.isAlive();
			} else if (entity instanceof Monster monster) {
				return !monster.isAlive();
			}
			return false; // Default case, keep the entity
		});
	}

}
