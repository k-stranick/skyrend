package dtcc.itn262.character;

import dtcc.itn262.monster.Monster;

public class LevelingSystem {

	public static int getXpForNextLevel(int currentLevel) {
		// Using a quadratic formula for XP requirements
		return (int) (50 * Math.pow(currentLevel, 2) + 100);
	}

	// Final Fantasy 8 style exp scaling but i need to figure out how to scale enemies to player level as well
	// https://gamefaqs.gamespot.com/boards/197343-final-fantasy-viii/63959027
	// https://forums.qhimm.com/index.php?topic=16898.0
	public static int calculateExpAwarded(Player player, Monster monster) {
		int X = monster.getMonsterAttributes().getExperience(); // Base EXP from monster
		int M = monster.getMonsterAttributes().getLevel();      // Monster level
		int P = player.getPlayerAttributes().getLevel();        // Player level

		// Avoid division by zero
		if (P == 0) {
			P = 1;
		}

		// Calculate scaling factor
		double scalingFactor = 5.0 * (((double) (M - P) / P) + 4.0);

		// Calculate final EXP awarded
		int expAwarded = (int) (X * scalingFactor);

		// Ensure EXP is not negative
		return Math.max(expAwarded, 0);
	}

}