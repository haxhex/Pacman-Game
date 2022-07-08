package ir.ac.kntu.engine.phy;

/**
 * Interface defining the classes allowing to detect collisions and exit of zone
 */
public interface Collider {
    /**
     * Updates the collider's position
     * @param newPosition
     */
    public void update(Position newPosition);

    /**
     * Function allowing to detect if there is a collision with the collider passed in parameter
     * @param collider
     * @return
     */
    public boolean hit(Collider collider);

    /**
     * Function detecting if the collider is outside the rectangle described by the two positions passed as parameters
     * @param p1
     * @param p2
     * @return
     */
    public boolean exit(Position p1, Position p2);
}
