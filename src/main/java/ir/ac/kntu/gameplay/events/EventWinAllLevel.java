package ir.ac.kntu.gameplay.events;

import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.gameplay.scene.GameViewController;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;

/**
 * Event allowing to change level at the end of a level
 */
public class EventWinAllLevel extends Event {
    private final GameViewController controller;

    public EventWinAllLevel(Entity entity, GameViewController controller, int time) {
        super(entity, time);
        this.controller = controller;
    }

    @Override
    public void handle() throws FileNotFoundException {
        SoundManager.getInstance().addSound("endLevel.wav", "end", false, 0.2f, 0L);
        controller.getGameView().getChildren().clear();
        controller.setEndLevel(false);
        controller.getBackMenuWin();
    }
}
