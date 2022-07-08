package ir.ac.kntu.gameplay.scene;

import ir.ac.kntu.engine.ui.*;

/**
 * View corresponding to the controls
 */
public class ViewControl extends View {

    public ViewControl(double height, double width) {
        setHeightScene(height);
        setWidthScene(width);
        init();
    }

    @Override
    public void init() {
        setStyle("-fx-background-color: #000000;");
        ButtonUI retour = new ButtonUI("Back",0,getPrefHeight() * 0.95);
        retour.changeFont(getClass().getResourceAsStream("/Font/Font3.TTF"),11);
        retour.setColor(Color.WHITE);

        retour.setAction(new SceneHandler() {
            @Override
            public void handle() {
                SceneManager.getInstance().setRoot(new MenuView(getHeightScene(), getWidthScene()));
            }
        });

        LabelUI labelTitre = new LabelUI("Control", getWidthScene() * 0.4, 9);
        labelTitre.changeFont(getClass().getResourceAsStream("/Font/Font3.TTF"),15);
        labelTitre.setColor(Color.WHITE);

        LabelUI labelJ1 = new LabelUI("Player :\n - Go up\n - Go left\n - Go right\n - Go down\n", 10, getHeightScene() * 0.2);
        labelJ1.changeFont(getClass().getResourceAsStream("/Font/Font3.TTF"),11);
        labelJ1.setColor(Color.WHITE);

        addToScene(retour.getButton());
        addToScene(labelTitre.getLabel());
        addToScene(labelJ1.getLabel());
    }
}
