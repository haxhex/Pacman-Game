package ir.ac.kntu.gameplay.controller;

import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.engine.controller.KeyboardController;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class defining the inputs for the  player
 */
public class PacmanController extends KeyboardController {
    private Displacement nextMove;
    private Displacement move;
    private final Map map;
    private int lastPl;

    public PacmanController(Map map) throws FileNotFoundException {
        this.map = map;
        lastPl = GameModel.getInstance().getPacmanModel().getPV();
        nextMove = Displacement.NOTHING;
        move = Displacement.NOTHING;
        createHandler();
    }

    /**
     * function collecting player input to define the next move
     */
    private void createHandler(){
        createHandler(KeyEvent -> {
            switch (KeyEvent) {
                case UP:
                    nextMove = Displacement.UP;
                    break;
                case LEFT:
                    nextMove = Displacement.LEFT;
                    break;
                case DOWN:
                    nextMove = Displacement.DOWN;
                    break;
                case RIGHT:
                    nextMove = Displacement.RIGHT;
                    break;
                default:
                    //nextMove = Displacement.NOTHING;
                    break;
            }
        });
    }

    /**
     * Function updating orientation
     * @param entity
     */
    @Override
    public void update(Entity entity) throws FileNotFoundException {
        if(!GameModel.getInstance().getPacmanModel().isDead()){
            updateMove(entity);
            updateGraphics(entity);

            entity.setPositioning(move.orientation);
        }
    }

    /**
     * Function choosing the right animation
     * @param entity
     */
    private void updateGraphics(Entity entity) throws FileNotFoundException {
        if(move != Displacement.NOTHING)
            if (GameModel.getInstance().getPacmanModel().isRed())
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString()+ EntityType.RED.name);
            else
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());
    }

    /**
     * Function checking if the direction entered is valid for the movement of the entity
     * @param entity
     */
    private void updateMove(Entity entity) throws FileNotFoundException {
        if(lastPl != GameModel.getInstance().getPacmanModel().getPV() || GameModel.getInstance().getPacmanModel().isDead()){
            move = Displacement.NOTHING;
            nextMove = Displacement.NOTHING;
            //entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());

            lastPl = GameModel.getInstance().getPacmanModel().getPV();
        }

        if(nextMove != Displacement.NOTHING){
            Position position = map.getPositionEntity(entity);
            int x = (int)position.getX(), y = (int)position.getY();
            List<Entity> entities = new ArrayList<>();

            if((entity.getPositioning() == null)
                    || ((entity.getPosition().getX()%map.getCellWid() <= entity.getPhysicsComponent().getSpeed()))
                    && (entity.getPosition().getY()%map.getCellHeight() <= entity.getPhysicsComponent().getSpeed())) {
                switch (nextMove) {
                    case UP -> entities = map.getEntity(x, y - 1);
                    case DOWN -> entities = map.getEntity(x, y + 1);
                    case RIGHT -> entities = map.getEntity(x + 1, y);
                    case LEFT -> entities = map.getEntity(x - 1, y);
                }

                if(canCross(entities)) {
                    double new_x = entity.getPosition().getX() - (entity.getPosition().getX()%map.getCellWid());
                    double new_y = entity.getPosition().getY() - (entity.getPosition().getY()%map.getCellHeight());
                    Position new_position = new Position(new_x, new_y);
                    entity.setPosition(new_position);
                    entity.getPhysicsComponent().getCollider().update(new_position);
                    move = nextMove;
                    nextMove = Displacement.NOTHING;
                }
            }
        }
    }

    /**
     * function allowing to know if in the list of entity passed in parameter
     * @param entities
     * @return
     */
    private boolean canCross(List<Entity> entities){
        for(Entity entity : entities){
            if(EntityType.WALL.name.equals(entity.getName()))
                return false;
        }
        return true;
    }
}

