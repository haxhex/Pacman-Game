package ir.ac.kntu.gameplay.py;

import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.phy.Collider;
import ir.ac.kntu.engine.phy.PhyComp;
import ir.ac.kntu.engine.phy.Position;

/**
 * class of physical components
 */
public class GhostPhy extends PhyComp {
    public GhostPhy(double speed, Collider collider) {
        super(speed);
        this.collider = collider;
    }

    /**
     * Collision detection in the ghost collider box
     * @param entityOwned
     * @param entity
     */
    @Override
    public void onCollision(Entity entityOwned, Entity entity){
        if (entityOwned.getPositioning() == null)
            return;

        if(entity.getName().equals(EntityType.WALL.name)){
            updatePositionEntityPosition(entityOwned, entity);
        }
    }

    /**
     * Box collider of ghosts released
     * @param ownedEntity
     */
    @Override
    public void onExit(Entity ownedEntity){
        moveBack(ownedEntity);
    }

    /**
     * update of the positions of the ghosts according to the displacement chosen by the AI
     * @param ownedPositioning
     * @param entity
     */
    private void updatePositionEntityPosition(Entity ownedPositioning, Entity entity){
        if(ownedPositioning.getPositioning() != null){
            double x = ownedPositioning.getPosition().getX(), y = ownedPositioning.getPosition().getY();
            double newX = x, newY = y;
            if(ownedPositioning.getPositioning().equals(Displacement.RIGHT.orientation)){
                newX = x - (x+ownedPositioning.getGraphicsComponent().getWidth() - entity.getPosition().getX());
            } else if (ownedPositioning.getPositioning().equals(Displacement.LEFT.orientation)) {
                newX = x + (entity.getPosition().getX() - x+ownedPositioning.getGraphicsComponent().getWidth());
            } else if (ownedPositioning.getPositioning().equals(Displacement.UP.orientation)) {
                newY = y + (entity.getPosition().getY() - y+ownedPositioning.getGraphicsComponent().getHeight());
            } else if (ownedPositioning.getPositioning().equals(Displacement.DOWN.orientation)) {
                newY = y - (y+ownedPositioning.getGraphicsComponent().getHeight() - entity.getPosition().getY());
            }
            ownedPositioning.setPosition(new Position(newX, newY));
            ownedPositioning.setPositioning(Displacement.NOTHING.orientation);
        }

    }

    /**
     * Half turn for ghosts
     * @param positioning
     */
    private void moveBack(Entity positioning){
        if(positioning.getPositioning() == null) return;
        positioning.setPositioning((positioning.getPositioning() + 180.0) % 360);
        positioning.getPhysicsComponent().update(positioning);
        positioning.setPositioning((positioning.getPositioning() - 180.0) % 360);
    }

    /**
     * movement of the ghosts ahead of them
     * @param entityOwned
     */
    private void moveForward(Entity entityOwned){
        if(entityOwned.getPositioning() == null) return;
        entityOwned.getPhysicsComponent().update(entityOwned);
    }
}
