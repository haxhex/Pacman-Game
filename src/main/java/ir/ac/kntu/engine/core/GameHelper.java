package ir.ac.kntu.engine.core;

import java.io.IOException;


import ir.ac.kntu.engine.ui.SceneController;
import ir.ac.kntu.engine.ui.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class for the main game loop
 */
public class GameHelper extends Application {

    private static String pathIcon;
    private static double time;
    private static float timeMul = 1f;
    private static String title = "";
    private static AnimationTimer animationTimer;
    private static GameManager gameManager;
    private static SceneController sceneController;



    /**
     * Initialization function at launch
     * @param stage scene currently loaded
     */
    @Override
    public void start(Stage stage) throws InterruptedException {
        SceneManager.getInstance(stage);
        SceneManager.getInstance().setIcon(pathIcon);
        SceneManager.getInstance().setSceneView(sceneController);
        SceneManager.getInstance().setTitle(title);

        final long startNanoTime = System.nanoTime();

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
                time = (currentNanoTime - startNanoTime) * 10e-10 * timeMul;
                Timer.getInstance().setTime(time);
                if (gameManager != null) {
                    try {
                        gameManager.update();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        };



        SceneManager.getInstance().show();
        animationTimer.start();

    }

    /**
     * After initialization, launch of the game
     */
    public static void startGame(){
        launch();


    }

    public static void setAnimationTimer(AnimationTimer animationTimer) {
        GameHelper.animationTimer = animationTimer;
    }

    public static void setTimeAnimation(float timeModification){
        timeMul = timeModification;
    }

    public static void setTitle(String title){
        GameHelper.title = title;
    }

    public static void stopGame(){
        animationTimer.stop();
    }

    public static void startAnimationTimer() {
        animationTimer.start();
    }

    public static void setGameManager(GameManager gameManager){
        GameHelper.gameManager = gameManager;

    }

    public static void setSceneController(SceneController sceneController) {
        GameHelper.sceneController = sceneController;
    }

    public static void setPathIcon(String pathIcon) {
        GameHelper.pathIcon = pathIcon;

    }
}