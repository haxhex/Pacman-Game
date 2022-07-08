package ir.ac.kntu.engine.sound;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Class allowing to observe a sound in progress (know when it finishes)
 */
public class AudioListener implements LineListener{
    private boolean done = false;
    @Override public synchronized void update(LineEvent event) {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
            done = true;
            notifyAll();
        }
    }

    /**
     * Function waiting for the end of the sound
     * @throws InterruptedException
     */
    public synchronized void waitUntilDone() throws InterruptedException {
        while (!done) { wait(); }
    }
}
