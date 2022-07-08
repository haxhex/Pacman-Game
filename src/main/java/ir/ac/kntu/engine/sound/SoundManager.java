package ir.ac.kntu.engine.sound;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class used to manage sound (Singleton)
 */
public class SoundManager {
    private static SoundManager instance;
    // List containing all current sounds
    private ConcurrentLinkedQueue<Sound> currentSounds = new ConcurrentLinkedQueue<>();

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Function adding sound to common sounds
     * @param soundName
     * @param name
     * @param isLoop
     * @param volume
     * @param start
     */
    public void addSound(String soundName, String name, boolean isLoop, float volume, Long start) {
        Sound sound = new Sound(soundName, name, start);
        sound.setVolume(volume);
        sound.setLoop(isLoop);
        sound.start();
        currentSounds.add(sound);
    }

    /**
     * Function to stop a specific sound
     * @param name
     */
    public void stopASound(String name) {
        System.out.println(currentSounds.size());
        for (Sound sound : currentSounds) {
            if (sound.getSoundName().equals(name)) {
                sound.stopSound();
            }
        }
    }

    /**
     * Function to remove the sound from the list of current sounds
     * @param name
     */
    public void removeSound(String name) {
        currentSounds.removeIf(sound -> sound.getSoundName().equals(name));
    }

    /**
     * Function allowing to stop all current sounds
     */
    public void stopAllSound() {
        for (Sound sound : currentSounds) {
            sound.stopSound();
        }
    }

    public ConcurrentLinkedQueue<Sound> getCurrentSounds() {
        return currentSounds;
    }
}
