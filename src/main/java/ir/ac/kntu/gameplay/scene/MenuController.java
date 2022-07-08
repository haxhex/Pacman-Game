package ir.ac.kntu.gameplay.scene;

import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.ui.SceneController;
import ir.ac.kntu.engine.ui.View;

/**
 * Class used to manage menu views
 */
public class MenuController implements SceneController {
    private View view;

    public MenuController(){
    }

    @Override
    public void init() {
        view = new MenuView(500,500);
    }

    @Override
    public void update(Map map) {

    }

    public View getView() {
        return view;
    }
}
