package ir.ac.kntu.engine.ui;


import ir.ac.kntu.engine.core.Map;
import javafx.scene.Parent;

import java.io.FileNotFoundException;

public interface SceneController {
    /**
     * Initialize scene
     */
    void init();

    /**
     * Function called at each loop of the game. Updates the scene
     * @param map current map to update
     */
    void update(Map map) throws FileNotFoundException;
    Parent getView();
}
