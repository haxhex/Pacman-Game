package ir.ac.kntu.engine.al;

import ir.ac.kntu.engine.controller.ControllerComp;
import ir.ac.kntu.engine.core.Entity;

import java.io.FileNotFoundException;

/**
 * Interface valid for all Al
 */
public interface AL extends ControllerComp {
    public void update(Entity entity) throws FileNotFoundException;
}
