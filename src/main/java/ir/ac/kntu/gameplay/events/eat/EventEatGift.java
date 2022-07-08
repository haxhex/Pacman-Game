package ir.ac.kntu.gameplay.events.eat;

import ir.ac.kntu.engine.core.builder.EntityBuilder;
import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Event managing the action eat a gift
 */
public class EventEatGift extends Event {
    private PacmanModel pacmanModel;
    private final Map map;
    private final int[] scorecard = new int[4];

    public EventEatGift(PacmanModel pacmanModel, Entity entity, Map map) throws InterruptedException {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        scorecard[0] = 1000;
        scorecard[1] = 2000;
        scorecard[2] = 3000;
        scorecard[3] = 4000;


    }

    @Override
    public void handle() throws FileNotFoundException {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;
        int temp = (int) (Math.random() * 4);
        pacmanModel.addScore(scorecard[temp]);
        map.deleteEntity(position, entity);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        entity = new Entity();
        Random random = new Random();
        int h = 0;
        for (int i = 0; i < LevelGenerator.coins.size(); i++){
            if (LevelGenerator.coins.get(i) != null)
                h++;
        }
        EntityBuilder builder;
        //Director director = ne
        String[] coins = new String[5];

        try {
            for (int i = 0; i < 5; i++){
                int c = random.nextInt(h - 1);
                if (LevelGenerator.coins.get(c) != null) {
                    Entity newEntity = LevelGenerator.CopyGhosts.get(c);
                   // newEntity.getGraphicsComponent().getCurrentImage().setImage();
                    map.deleteEntity(newEntity.getPosition(), newEntity);
                }
                coins[i] = ".";
            }
            System.out.println("Remove Five Coins Randomly!");


        }catch (IndexOutOfBoundsException e){
            System.out.println("Remove Five Coins Randomly!");
        }

        SoundManager.getInstance().addSound("eatGift.wav", "eatGift", false, 0.2f, 0L);


    }
}
