package ir.ac.kntu.gameplay.events.eat;

import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;

/**
 * Event handling the eating a ghost action
 */
public class EventEatGhost extends Event {
    private PacmanModel pacmanModel;

    public EventEatGhost(PacmanModel pacmanModel, Entity entity) throws InterruptedException {
        super(entity);
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() throws FileNotFoundException {
        pacmanModel.addScore(150);
        SoundManager.getInstance().addSound("eatGhost.wav", "eatGhost", false, 0.2f, 0L);
    }
}
