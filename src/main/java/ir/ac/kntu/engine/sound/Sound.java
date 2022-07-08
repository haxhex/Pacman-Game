package ir.ac.kntu.engine.sound;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Class corresponding to a sound played, it is a thread
 */
public class Sound extends Thread {
    private final AudioListener listener = new AudioListener();
    // Attributes for playing the sound
    private Clip clip;
    // Set whether to play the sound loop or not
    private boolean isLoop = false;
    private final String name;
    private final Long start;

    Sound(String soundName, String name, Long start) {
        super();
        this.name = name;
        this.start = start;
        // clip initialization
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Sound/" + soundName))) {
            this.clip = AudioSystem.getClip();
            this.clip.addLineListener(listener);
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function for playing a sound
     */
    private void play() {
        try {
            if (isLoop)
                clip.loop(Integer.MAX_VALUE);
            clip.setMicrosecondPosition(start*1000);
            clip.start();
            listener.waitUntilDone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // We turn off the sound
            clip.close();
            // We remove it from the list
            SoundManager.getInstance().removeSound(this.name);
        }
    }

    /**
     * Function allowing to stop a sound
     */
    public synchronized void stopSound() {
        clip.setMicrosecondPosition(clip.getMicrosecondLength());
        if (isLoop)
            clip.close();
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    /**
     * set the sound volume
     * @param volume
     */
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public String getSoundName() {
        return name;
    }

    @Override
    public void run() {
        this.play();
    }
}
