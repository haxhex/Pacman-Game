package ir.ac.kntu.gameplay.scene;

import ir.ac.kntu.engine.ui.*;

import java.io.FileNotFoundException;

/**
 * View corresponding to the main menu
 */
public class MenuView extends View {
    private ButtonUI gameButtonP;
    private ButtonUI controls;
    private ButtonUI helpButton;
    private ButtonUI quitButton;

    public MenuView(double height, double width) {
        setHeightScene(height);
        setWidthScene(width);
        init();
    }

    @Override
    public void init() {
        setBackgroundScene("/Image/Menu/menu.png");

        gameButtonP = new ButtonUI("Start Game",getPrefWidth() * 0.27,getPrefHeight() * 0.3);
        gameButtonP.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),30);
        gameButtonP.setColor(Color.WHITE);


        controls = new ButtonUI("Control",getPrefWidth() * 0.27,getPrefHeight() *0.5);
        controls.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),30);
        controls.setColor(Color.WHITE);

        helpButton= new ButtonUI("Guide",getPrefWidth() * 0.27,getPrefHeight() * 0.6);
        helpButton.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),30);
        helpButton.setColor(Color.WHITE);

        quitButton= new ButtonUI("Quit",getPrefWidth() * 0.27,getPrefHeight() * 0.7);
        quitButton.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),30);
        quitButton.setColor(Color.WHITE);

        gameButtonP.setAction(new SceneHandler() {
            @Override
            public void handle() throws FileNotFoundException {
                GameViewController gameViewController = new GameViewController(6,1);
                SceneManager.getInstance().setSceneView(gameViewController);
            }
        });


        controls.setAction(new SceneHandler() {
            @Override
            public void handle() {
                SceneManager.getInstance().setRoot(new ViewControl(getHeightScene(), getWidthScene()));
            }
        });

        helpButton.setAction(new SceneHandler() {
            @Override
            public void handle() {
                SceneManager.getInstance().setRoot(new OptionsView(getHeightScene(), getWidthScene()));
            }
        });

        quitButton.setAction(new SceneHandler() {
            @Override
            public void handle() {
                SceneManager.getInstance().exit();
            }
        });

        addToScene(gameButtonP.getButton());
        addToScene(controls.getButton());
        addToScene(helpButton.getButton());
        addToScene(quitButton.getButton());
    }
}
