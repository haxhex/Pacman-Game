package ir.ac.kntu.gameplay.events.dead;

import ir.ac.kntu.gameplay.Score;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.EventManager;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.gameplay.scene.GameViewController;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;

/**
 * Event that triggers the death animation and sets a new high score if the record is broken
 */
public class EventPacmanDie extends Event {
    private final PacmanModel pacmanModel;
    private final Map map;
    private final Entity entityOwned;
    private final Score score = GameViewController.getScore();

    public EventPacmanDie(PacmanModel pacmanModel, Entity entity, Entity entityOwned, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.entityOwned = entityOwned;
    }

    @Override
    public void handle() throws FileNotFoundException {
        if(Integer.parseInt(score.getScoreFile() ) < pacmanModel.getScore()){
            GameViewController.setSessionBestScore(pacmanModel.getScore());
            score.setScoreFile(pacmanModel.getScore()+"");
        }
        entityOwned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("dead");
        SoundManager.getInstance().stopAllSound();
        SoundManager.getInstance().addSound("dead.wav", "dead", false, 0.2f, 400L);
        EventManager.getEventManager().addEvent(new EventPowerfulPacDie(entityOwned, entity, 200));
    }
}
