package ir.ac.kntu.engine.core;

import java.io.FileNotFoundException;

/**
 * Interface representing a component of the entity
 */
public interface Component {
    /**
     * Function updating the attributes of all components
     * @param entity
     */
    public void update(Entity entity) throws FileNotFoundException;
}
