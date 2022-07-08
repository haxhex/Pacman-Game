package ir.ac.kntu.engine.phy;

import ir.ac.kntu.engine.core.Entity;

/**
 * Interface defining the classes detecting
 */
public interface ExitListener {
    /**
     * method triggered when an entity leaves a defined area
     * @param ownedEntity
     */
    public void onExit(Entity ownedEntity);
}
