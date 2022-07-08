package ir.ac.kntu.engine.ui;

import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.sound.SoundManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;

/**
 * Eat to change scene around the course
 */
public class SceneManager {
    private Stage stage;
    private SceneController sceneController;
    private static SceneManager instance;

    private SceneManager(Stage stage) {
        this.stage = stage;
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                SoundManager.getInstance().stopAllSound();
            }
        });
    }

    public void setIcon(String path){
        this.stage.getIcons().add(new Image(SceneManager.class.getResourceAsStream(path)));
    }

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
        return instance;
    }

    public static SceneManager getInstance() {
        return instance;
    }

    /**
     * change the window title
     * @param title
     */
    public void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Displays the internship
     */
    public void show() {
        stage.show();
    }

    /**
     *
     * @param root
     */
    public void setRoot(Parent root) {
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(root);
            stage.setScene(scene);
        }
        scene.setRoot(root);
    }

    /**
     *
     * @param map card or pacman plays
     */
    public void update(Map map) throws FileNotFoundException {
        sceneController.update(map);
    }

    /**
     * change scene by transmitting the scene controller
     * @param sceneController controller the scene
     */
    public void setSceneView(SceneController sceneController) {
        this.sceneController = sceneController;
        this.sceneController.init();
        setRoot(this.sceneController.getView());
    }

    public Stage getStage() {
        return stage;
    }

    public void exit(){
        Platform.exit();
    }
}
