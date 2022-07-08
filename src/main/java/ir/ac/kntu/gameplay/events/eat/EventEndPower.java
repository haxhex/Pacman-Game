package ir.ac.kntu.gameplay.events.eat;

import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.sound.SoundManager;

/**
 * Event red pacman in the pacman state
 */
public class EventEndPower extends Event {
    private PacmanModel pacmanModel;

    public EventEndPower(PacmanModel pacmanModel, Entity entity, int time) {
        super(entity, time);
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        SoundManager.getInstance().stopAllSound();
        pacmanModel.setRed(false);
    }
}
