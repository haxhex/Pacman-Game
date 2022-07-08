package ir.ac.kntu.gameplay.scene;

import ir.ac.kntu.engine.ui.*;

/**
 * View corresponding to the help
 */
public class OptionsView extends View {

    public OptionsView(double height, double width) {
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

        LabelUI labelTitre = new LabelUI("Guide", getWidthScene() * 0.4, 9);
        labelTitre.changeFont(getClass().getResourceAsStream("/Font/Font3.TTF"),15);
        labelTitre.setColor(Color.WHITE);

        LabelUI labelExplication = new LabelUI("Welcome to HELIX PACMAN,\nyour goal get as many points as possible\navoiding ghosts.\nGood luck !", 10, getHeightScene() * 0.2);
        labelExplication.changeFont(getClass().getResourceAsStream("/Font/Font3.TTF"),11);
        labelExplication.setColor(Color.WHITE);

        addToScene(retour.getButton());
        addToScene(labelTitre.getLabel());
        addToScene(labelExplication.getLabel());
    }
}
