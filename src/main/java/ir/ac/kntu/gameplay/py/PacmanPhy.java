package ir.ac.kntu.gameplay.py;

import ir.ac.kntu.engine.core.GameHelper;
import ir.ac.kntu.engine.core.builder.EntityBuilder;
import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.builder.object.CoinBuilder;
import ir.ac.kntu.gameplay.events.eat.EventEatGift;
import ir.ac.kntu.gameplay.events.eat.EventEatGhost;
import ir.ac.kntu.gameplay.events.eat.EventEatCoin;
import ir.ac.kntu.gameplay.events.eat.EventEatSpecialCoin;
import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.gameplay.events.dead.EventPacmanDie;
import ir.ac.kntu.gameplay.model.PacmanModel;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.EventManager;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Collider;
import ir.ac.kntu.engine.phy.PhyComp;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Pacman physical component class
 */
public class PacmanPhy extends PhyComp {
    private final PacmanModel pacmanModel;
    private final Map map;
    private final HashMap<Entity, Boolean> ghostsEat;
    private boolean changeGhostEat;
    private Random random = new Random();
    public static ArrayList<Entity> ateCoins = new ArrayList<>();


    public PacmanPhy(double speed, Collider collider, PacmanModel pacmanModel, Map map) {
        super(speed);
        this.collider = collider;
        this.pacmanModel = pacmanModel;
        this.map = map;
        ghostsEat = new HashMap<>();
        changeGhostEat = false;
    }

    /**
     * collide with the pacman's Collider box
     * @param entityOwned
     * @param entity
     */
    @Override
    public void onCollision(Entity entityOwned, Entity entity) throws InterruptedException, FileNotFoundException {
        if(entity.getName().equals(EntityType.GHOST.name)){
            updateGhostCollision(entityOwned, entity);
        } else if(entity.getName().equals(EntityType.WALL.name)){
            updatePositionEntityPosition(entityOwned, entity);
        } else if(entity.getName().equals(EntityType.Coin.name)){
            EventManager.getEventManager().addEvent(new EventEatCoin(pacmanModel, entity, map));
            ateCoins.add(new Entity(entity));
        } else if(entity.getName().equals(EntityType.GIFT.name)){
            EventManager.getEventManager().addEvent(new EventEatGift(pacmanModel, entity, map));
            for (int i =0 ; i < 5; i++) {
                int c = random.nextInt(LevelGenerator.coins.size());
                EventManager.getEventManager().addEvent(new EventEatCoin(pacmanModel, LevelGenerator.coins.get(c), map));
            }
        } else if(entity.getName().equals(EntityType.RED.name)){
            EventManager.getEventManager().addEvent(new EventEatSpecialCoin(pacmanModel, entity, entityOwned, map));
           for (int i = 0; i < 5; i++){
                int c = random.nextInt(ateCoins.size());
               ateCoins.get(c).getGraphicsComponent().setImage("/Image/object/normalCoin.png" );
               GameModel.getInstance().resetCoin(ateCoins.get(c));
               map.addEntity((int) (ateCoins.get(c).getPosition().getX() / LevelGenerator.getLength()), (int) (ateCoins.get(c).getPosition().getY() / LevelGenerator.getWidth()), ateCoins.get(c));
               EntityBuilder builder = new CoinBuilder();
              LevelGenerator.directory.constructEntity(builder, new Position(ateCoins.get(c).getPosition().getX(), ateCoins.get(c).getPosition().getY()));
               LevelGenerator.setMatrix((int) (ateCoins.get(c).getPosition().getX() / LevelGenerator.getLength()), (int) (ateCoins.get(c).getPosition().getY() / LevelGenerator.getWidth()), builder.getEntity());
               System.out.println(map.getPositionEntity(ateCoins.get(c)));
               GameModel.getInstance().getMap().addEntity((int) (ateCoins.get(c).getPosition().getX() / LevelGenerator.getLength()), (int) (ateCoins.get(c).getPosition().getY() / LevelGenerator.getWidth()), ateCoins.get(c));
               ateCoins.remove(c);
           }
        }
    }

    @Override
    public void onExit(Entity ownedEntity){
        moveBack(ownedEntity);
    }

    /**
     * updates the pacman positions according to the controller
     * @param ownedEntity
     * @param entity
     */
    private void updatePositionEntityPosition(Entity ownedEntity, Entity entity){
        if(ownedEntity.getPositioning() != null){
            double x = ownedEntity.getPosition().getX(), y = ownedEntity.getPosition().getY();
            double newX = x, newY = y;
            if(ownedEntity.getPositioning().equals(Displacement.RIGHT.orientation)){
                newX = x - (x+ownedEntity.getGraphicsComponent().getWidth() - entity.getPosition().getX());
            } else if (ownedEntity.getPositioning().equals(Displacement.LEFT.orientation)) {
                newX = x + (entity.getPosition().getX() - x+ownedEntity.getGraphicsComponent().getWidth());
            } else if (ownedEntity.getPositioning().equals(Displacement.UP.orientation)) {
                newY = y + (entity.getPosition().getY() - y+ownedEntity.getGraphicsComponent().getHeight());
            } else if (ownedEntity.getPositioning().equals(Displacement.DOWN.orientation)) {
                newY = y - (y+ownedEntity.getGraphicsComponent().getHeight() - entity.getPosition().getY());
            }
            ownedEntity.setPosition(new Position(newX, newY));
            ownedEntity.setPositioning(Displacement.NOTHING.orientation);
        }
    }

    /**
     * Check in case of collision with any ghost
     * @param ownedEntity
     * @param entity
     */
    private void updateGhostCollision(Entity ownedEntity, Entity entity) throws InterruptedException, FileNotFoundException {
        if (pacmanModel.isRed() && canEat(entity)){
            EventManager.getEventManager().addEvent(new EventEatGhost(pacmanModel, entity));
            GameModel.getInstance().resetEntity(entity);
            pacmanModel.getEvents().add("Ghost collision eat ghost");

        } else {
            resetGhostEat();
            pacmanModel.decrementPL();
            pacmanModel.getEvents().add("Ghost collision eat by ghost");
            if (pacmanModel.checkNull()){
                if (!pacmanModel.isDead()){
                    EventManager.getEventManager().addEvent(new EventPacmanDie(pacmanModel, entity, ownedEntity, map));
                }
                pacmanModel.setDead(true);
                ownedEntity.setPositioning(Displacement.NOTHING.orientation);
            } else {
                GameModel.getInstance().resetGame();
                SoundManager.getInstance().addSound("die.wav", "die", false, 0.2f, 0L);
            }
        }
    }

    private void resetGhostEat(){
        if(pacmanModel.isRed()) return;
        if(! changeGhostEat) return;

        ghostsEat.replaceAll((e, v) -> true);
        changeGhostEat = false;
    }

    private boolean canEat(Entity entity) {
        changeGhostEat = true;
        if(ghostsEat.containsKey(entity)){
            boolean res = ghostsEat.get(entity);
            ghostsEat.put(entity, false);
            return res;
        } else {
            ghostsEat.put(entity, false);
            return true;
        }
    }

    /**
     * pacman half-turn movement
     * @param positioning
     */
    private void moveBack(Entity positioning){
        if(positioning.getPositioning() == null) return;
        positioning.setPositioning((positioning.getPositioning() + 180.0) % 360);
        positioning.getPhysicsComponent().update(positioning);
        positioning.setPositioning(( positioning.getPositioning() - 180.0) % 360 );
    }

    /**
     * movement straight from the pacman
     * @param ownedEntity
     */
    private void moveForward(Entity ownedEntity){
        if(ownedEntity.getPositioning() == null) return;
        ownedEntity.getPhysicsComponent().update(ownedEntity);
    }
}
