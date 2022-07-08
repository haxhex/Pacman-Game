package ir.ac.kntu.gameplay.events.eat;

import ir.ac.kntu.engine.core.Timer;
import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.sound.SoundManager;
import ir.ac.kntu.gameplay.al_movement.NothingAl;

import java.io.FileNotFoundException;

/**
 * Event managing the action of eating a coin
 */
public class EventEatCoin extends Event {
    private PacmanModel pacmanModel;
    private Map map;
    private int h = 80;
    private Timer timer = new Timer();

    public EventEatCoin(PacmanModel pacmanModel, Entity entity, Map map) throws InterruptedException {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
    }
    @Override
    public void handle() throws FileNotFoundException {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;
        for (int i =1; i<= 5; i++)
        timer.setTime(i * h);
int s = 0;
        pacmanModel.addScore(10);
        if (entity != null) {
            if (entity.getGraphicsComponent().name.equalsIgnoreCase("/Image/object/stopCoin.png")) {
                System.out.println("stop ghosts");
                LevelGenerator.ghosts.get(0).setControllerComponent(new NothingAl());
                LevelGenerator.ghosts.get(1).setControllerComponent(new NothingAl());
                LevelGenerator.ghosts.get(2).setControllerComponent(new NothingAl());
                LevelGenerator.ghosts.get(3).setControllerComponent(new NothingAl());
                LevelGenerator.ghosts.get(4).setControllerComponent(new NothingAl());
                LevelGenerator.ghosts.get(5).setControllerComponent(new NothingAl());


                s = GameModel.getInstance().getPacmanModel().getScore();

            }
            if (GameModel.getInstance().getPacmanModel().getScore() > s + (timer.getTime())) {
                System.out.println("run ghosts");
                LevelGenerator.ghosts.get(0).setControllerComponent(LevelGenerator.CopyGhosts.get(0).getControllerComponent());
                LevelGenerator.ghosts.get(1).setControllerComponent(LevelGenerator.CopyGhosts.get(1).getControllerComponent());
                LevelGenerator.ghosts.get(2).setControllerComponent(LevelGenerator.CopyGhosts.get(2).getControllerComponent());

                LevelGenerator.ghosts.get(3).setControllerComponent(LevelGenerator.CopyGhosts.get(3).getControllerComponent());
                LevelGenerator.ghosts.get(4).setControllerComponent(LevelGenerator.CopyGhosts.get(4).getControllerComponent());
                LevelGenerator.ghosts.get(5).setControllerComponent(LevelGenerator.CopyGhosts.get(5).getControllerComponent());
            }

        }
        assert entity != null;
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        map.deleteEntity(position, entity);
        SoundManager.getInstance().addSound("eatCoin.wav", "eatCoin", false, 0.2f, 500L);
    }
}
