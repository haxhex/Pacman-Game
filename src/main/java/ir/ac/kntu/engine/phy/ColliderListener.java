package ir.ac.kntu.engine.phy;

import ir.ac.kntu.engine.core.Entity;

import java.io.FileNotFoundException;

/**
 * Interface defining the classes used to deal with collisions
 */
public interface ColliderListener {
    /**
     * method triggered when two entity collisions
     * @param entityOwned
     * @param entity
     */
    public void onCollision(Entity entityOwned, Entity entity) throws InterruptedException, FileNotFoundException;
}
