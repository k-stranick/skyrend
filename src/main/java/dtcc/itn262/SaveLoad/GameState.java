package dtcc.itn262.SaveLoad;

import dtcc.itn262.character.Player;

public class GameState {
	private Player player;      // Player's current state
	private boolean isBossDefeated; // Example persistent state

	public GameState(Player player, boolean isBossDefeated) {
		this.player = player;
		this.isBossDefeated = isBossDefeated;
	}

	// Getters and setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isBossDefeated() {
		return isBossDefeated;
	}

	public void setBossDefeated(boolean bossDefeated) {
		isBossDefeated = bossDefeated;
	}
}