package ir.ac.kntu.gameplay.events.dead;

import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.GameHelper;
import ir.ac.kntu.engine.sound.SoundManager;
import ir.ac.kntu.gameplay.scene.MenuController;
import ir.ac.kntu.engine.ui.SceneManager;

import java.io.FileNotFoundException;

/**
 * Event rebooting the game after death
 */
public class EventDeadAnim extends Event {
    private final Entity entityOwned;
    protected EventDeadAnim(Entity entityOwned, Entity entity, int time) {
        super(entity, time);
        this.entityOwned = entityOwned;
    }

    @Override
    public void handle() throws FileNotFoundException {
        GameModel.getInstance().resetPacMan();
        System.out.println("Game Over!");
        SceneManager.getInstance().setSceneView(new MenuController());
        SoundManager.getInstance().stopAllSound();
        GameHelper.setGameManager(null);
    }
}
