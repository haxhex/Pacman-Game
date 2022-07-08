package ir.ac.kntu.engine.ui;

import ir.ac.kntu.gameplay.scene.MenuView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public abstract class View extends Pane {
    public abstract void init();

    /**
     * add visual elements to the scene
     * @param element object to display
     */
    public void addToScene(Node element) {
        getChildren().add(element);
    }

    /**
     * change the scene wallpaper
     * @param path image path for wallpaper
     */
    public void setBackgroundScene(String path) {
        Image image2 = new Image(MenuView.class.getResourceAsStream(path));
        BackgroundSize backgroundSize = new BackgroundSize(getPrefHeight(),getPrefWidth(),true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        setBackground(background);
    }

    public void setHeightScene(double height) {
        setPrefHeight(height);
    }

    public void setWidthScene(double width) {
        setPrefWidth(width);
    }

    public double getHeightScene() {
        return getPrefHeight();
    }

    public double getWidthScene() {
        return getPrefWidth();
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> eventHandler) {
        setOnKeyPressed(eventHandler);
    }
}
