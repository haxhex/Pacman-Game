package ir.ac.kntu.engine.phy;

import ir.ac.kntu.engine.core.Component;
import ir.ac.kntu.engine.core.Entity;

import java.io.FileNotFoundException;

/**
 * Class gathering all the physical properties of an entity
 */
public abstract class PhyComp implements ColliderListener, ExitListener, Component {
    public double speed;
    protected Collider collider;

    public PhyComp(double speed){
        this.speed = speed;
    }

    /**
     * Updates the position of an entity according to its speed and orientation
     * @param entity
     */
    @Override
    //TODO
    public void update(Entity entity){
        if (entity.getPositioning() == null) {
            return;
        }
        double direction =  entity.getPositioning() % 360;
        double radDirection = Math.toRadians((double)direction);

        double cosDir = Math.cos(radDirection);
        double sinDir = Math.sin(radDirection);

        double newPosX = speed * cosDir;
        double newPosY = speed * sinDir;

        double fx = entity.getPosition().getX() + newPosX;
        double fy = entity.getPosition().getY() + newPosY;

        entity.getPosition().setX(fx);
        entity.getPosition().setY(fy);
        collider.update(entity.getPosition());
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public Collider getCollider() {
        return collider;
    }

    public double getSpeed(){
        return speed;
    }

    @Override
    public void onCollision(Entity entityOwned, Entity entity) throws InterruptedException, FileNotFoundException {}

    @Override
    public void onExit(Entity ownedEntity){}
}
