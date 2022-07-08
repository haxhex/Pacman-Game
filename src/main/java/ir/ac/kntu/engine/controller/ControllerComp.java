package ir.ac.kntu.engine.controller;

import ir.ac.kntu.engine.core.Component;
import ir.ac.kntu.engine.core.Entity;

import java.io.FileNotFoundException;

/**
 * Controller interface, allows the movements of a character by keyboard input
 */
public interface ControllerComp extends Component {
    public void update(Entity entity) throws FileNotFoundException;
}
