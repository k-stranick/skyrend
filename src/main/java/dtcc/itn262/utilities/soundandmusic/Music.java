package dtcc.itn262.utilities.soundandmusic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Music {
	private static final Map<String, Clip> soundClips = new HashMap<>();
	private static Clip backgroundMusicClip;
	// Preload a sound file
	public static void preloadSound(String name, String filePath) {
		Clip clip = loadClip(filePath);
		if (clip != null) {
			soundClips.put(name, clip);
		}
	}

	// Load a sound file
	private static Clip loadClip(String filePath) {
		try {
			File soundFile = new File(filePath);
			if (!soundFile.exists()) {
				throw new IOException("File not found: " + filePath);
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			return clip;
		} catch (Exception e) {
			System.err.println("Error loading sound (" + filePath + "): " + e.getMessage());
			return null;
		}
	}

	// Play a preloaded sound
	public static void playSound(String name) {
		Clip clip = soundClips.get(name);
		if (clip != null) {
			clip.setFramePosition(0); // Rewind the clip
			clip.start();
		}
	}

	// Play background music
	public static void playBackgroundMusic(String filePath) {
		backgroundMusicClip = loadClip(filePath);

		if (backgroundMusicClip != null) {
			FloatControl gainControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels (adjust as needed)
			backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop indefinitely
			backgroundMusicClip.start();
		}
	}

	// Stop background music
	public static void stopBackgroundMusic() {
		if (backgroundMusicClip != null) {
			backgroundMusicClip.stop();
		}
	}

	// Stop all sounds
	public static void stopAllSounds() {
		soundClips.values().forEach(Clip::stop);
		if (backgroundMusicClip != null) {
			backgroundMusicClip.stop();
		}
	}
}
