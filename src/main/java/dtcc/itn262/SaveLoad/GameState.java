package dtcc.itn262.SaveLoad;

import dtcc.itn262.character.Player;
import dtcc.itn262.maze.Maze;

import java.util.List;
import java.util.Set;

public class GameState {
	private Player player;      // Player's current state
	//private boolean isBossDefeated; // TODO this should change and I should keep track of all bosses defeated
	private Set<String> uniqueVisitedRooms; // this should allow me to make sure I do not trigger the same event twice
	private List<String> moveHistory;


	public GameState(Player player,Set<String> uniqueVisitedRooms,List<String> moveHistory) {
		this.player = player;
		this.uniqueVisitedRooms = uniqueVisitedRooms;
		this.moveHistory = moveHistory;
		//this.isBossDefeated = isBossDefeated;
	}

	// Getters and setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Set<String> getUniqueVisitedRooms() {
		return uniqueVisitedRooms;
	}

	public List<String> getMoveHistory() {
		return moveHistory;
	}

	public static void updatePlayerEquippedWeaponStatus(Player player) {
		// Update the equipped status of the player's weapons
		player.getPlayerWeaponList().forEach(weapon -> {
			weapon.setEquipped(player.getEquippedWeapon() != null && player.getEquippedWeapon().getName().equals(weapon.getName()));
		});
	}
/*	public boolean isBossDefeated() {
		return isBossDefeated;
	}

	public void setBossDefeated(boolean bossDefeated) {
		isBossDefeated = bossDefeated;
	}*/
}