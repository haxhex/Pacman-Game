package ir.ac.kntu.gameplay.py;

import ir.ac.kntu.engine.phy.Collider;
import ir.ac.kntu.engine.phy.PhyComp;

/**
 * class of objects physically present in the game
 */
public class ObjectPhy extends PhyComp {
    public ObjectPhy(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
