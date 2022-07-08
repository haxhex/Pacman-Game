package ir.ac.kntu.gameplay.model;

import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;

import java.io.FileNotFoundException;

/**
 * Class storing game status information
 */
public class GameModel {
    private static GameModel gameModel;
    private LevelGenerator levelGenerator;
    private PacmanModel pacmanModel;

    private GameModel(LevelGenerator levelGenerator) throws FileNotFoundException {
        gameModel = this;
        this.levelGenerator = levelGenerator;
        pacmanModel = new PacmanModel();
    }

    public static GameModel getInstance() throws FileNotFoundException {
        if(gameModel == null)
            gameModel = new GameModel(null);
        return gameModel;
    }

    /**
     * to reset the game, reset the positions of each entity
     */
    public void resetGame(){
        for(Entity e : levelGenerator.getInitPositionEntities().keySet()){
            resetEntity(e);
            if(e.getName().equals("pacman"))
                e.setPositioning(Displacement.NOTHING.orientation);
        }
    }

    /**
     * re-placement of the initial position in the scene of an entity
     * @param entity
     */
    public void resetEntity(Entity entity) {
        Position actualPosition = levelGenerator.getMap().getPositionEntity(entity);
        Position initPosition = levelGenerator.getInitPositionEntities().get(entity);
        levelGenerator.getMap().swap((int)actualPosition.getX(), (int)actualPosition.getY(), (int)initPosition.getX(), (int)initPosition.getY(), entity);
        double newX = initPosition.getX()*levelGenerator.getMap().getCellWid();
        double newY = initPosition.getY()*levelGenerator.getMap().getCellHeight();
        entity.setPosition(new Position(newX, newY));
        entity.getPhysicsComponent().update(entity);
    }
    public void resetCoin(Entity entity) {
        levelGenerator.getMap().addEntity((int) (entity.getPosition().getX() / LevelGenerator.getLength()), (int) (entity.getPosition().getY() / LevelGenerator.getWidth()), entity);
        Position actualPosition = levelGenerator.getMap().getPositionEntity(entity);
        levelGenerator.getInitPositionEntities().put(entity, new Position(entity.getPosition().getX() / LevelGenerator.getLength(), entity.getPosition().getY() / LevelGenerator.getWidth()));
        Position initPosition = levelGenerator.getInitPositionEntities().get(entity);
        levelGenerator.getMap().swap((int)actualPosition.getX(), (int)actualPosition.getY(), (int)initPosition.getX(), (int)initPosition.getY(), entity);
        double newX = actualPosition.getX()*levelGenerator.getMap().getCellWid();
        double newY = actualPosition.getY()*levelGenerator.getMap().getCellHeight();
        entity.setPosition(new Position(newX, newY));
        entity.getPhysicsComponent().update(entity);
    }


    public void resetPacMan() throws FileNotFoundException {
        pacmanModel = new PacmanModel();
    }

    public PacmanModel getPacmanModel() {
        return pacmanModel;
    }

    public Map getMap(){
        if(levelGenerator != null)
            return levelGenerator.getMap();
        return null;
    }

    public void setLevelGenerator(LevelGenerator levelGenerator){
        this.levelGenerator = levelGenerator;
        pacmanModel.setRed(false);
    }


}
