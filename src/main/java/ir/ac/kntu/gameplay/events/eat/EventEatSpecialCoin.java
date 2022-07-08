package ir.ac.kntu.gameplay.events.eat;

import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.EventManager;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.sound.SoundManager;
import ir.ac.kntu.gameplay.py.PacmanPhy;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Event managing the action to eat a Christmas tree
 */
public class EventEatSpecialCoin extends Event {
    private final PacmanModel pacmanModel;
    private final Entity ownedEntity;
    private final Map map;

    public EventEatSpecialCoin(PacmanModel pacmanModel, Entity entity, Entity ownedEntity, Map map) throws InterruptedException {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.ownedEntity = ownedEntity;
    }

    @Override
    public void handle() throws FileNotFoundException {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;

        map.deleteEntity(position, entity);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        Random random = new Random();
        try {
            for (int i = 0; i < 5; i++){
                int c = random.nextInt(LevelGenerator.coins.size() - 1);
                if (LevelGenerator.coins.get(c) == null) {
                    map.addEntity((int) PacmanPhy.ateCoins.get(c).getPosition().getX() / map.getHeight(), (int) LevelGenerator.copyCoins.get(c).getPosition().getY() / map.getHeight(), LevelGenerator.coins.get(c));
                    pacmanModel.addScore(-10);
                }
            }

            System.out.println("Add Five Coins Randomly!");
        } catch (IndexOutOfBoundsException | FileNotFoundException e){
            System.out.println("Fail To Add Five Coins!");
        }

        pacmanModel.addScore(100);
        if(!pacmanModel.isRed()){
            pacmanModel.setRed(true);
            SoundManager.getInstance().stopAllSound();
            SoundManager.getInstance().addSound("isRed.wav", "isRed", false, 0.8f, 0L);
            EventManager.getEventManager().addEvent(new EventEndPower(pacmanModel, ownedEntity, 660));



        }
    }
}
