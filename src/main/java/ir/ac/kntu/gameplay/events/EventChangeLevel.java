package ir.ac.kntu.gameplay.events;

import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.gameplay.scene.GameViewController;

import java.io.FileNotFoundException;

/**
 * Event allowing to change level at the end of a level
 */
public class EventChangeLevel extends Event {
    private final GameViewController controller;

    public EventChangeLevel(Entity entity, GameViewController controller, int time) {
        super(entity, time);
        this.controller = controller;
    }

    @Override
    public void handle() throws FileNotFoundException {
        controller.getGameView().getChildren().clear();
        controller.setNewLevel();
        controller.setEndLevel(false);
        controller.getGameManager().setMap(controller.getLevelGenerator().getMap());
        controller.init();
    }
}
