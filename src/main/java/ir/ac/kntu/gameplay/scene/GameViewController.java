package ir.ac.kntu.gameplay.scene;

import ir.ac.kntu.engine.core.*;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.ui.*;
import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.gameplay.events.EventWinAllLevel;
import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.gameplay.Score;
import ir.ac.kntu.gameplay.events.EventChangeLevel;
import ir.ac.kntu.engine.controller.GeneralController;
import ir.ac.kntu.engine.controller.KeyboardController;
import ir.ac.kntu.engine.sound.SoundManager;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class GameViewController implements SceneController {
    private final GameManager gameManager;
    private LevelGenerator levelGenerator;
    private final View gameView;
    private int currentLvl;
    private boolean endLevel;
    private final int nbLevel;

    private static Score score = new Score();;
    private static int sessionBestScore;

    LabelUI scoreUI = new LabelUI("Score: " + GameModel.getInstance().getPacmanModel().getScore(),350,-10);
    LabelUI vieUI = new LabelUI("Remaining life: " +  GameModel.getInstance().getPacmanModel().getPV(),50,-10);
    LabelUI bestScore ;

    /**
     * Class used to manage the game view
     * @param nLevel Total number of levels
     * @param currentLevel Current level
     */
    public GameViewController(int nLevel, int currentLevel) throws FileNotFoundException {
        this.nbLevel = nLevel;
        this.endLevel = false;
        this.currentLvl = currentLevel;
        levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentLevel + ".txt");
        gameManager = new GameManager(levelGenerator.getMap());
        GameModel.getInstance().setLevelGenerator(levelGenerator);
        gameView = new GameView();
        GameHelper.setGameManager(gameManager);
        setBestScore();
    }

    @Override
    public void init() {
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        GeneralController keyboardController;
            keyboardController = new GeneralController(new ArrayList<>(Arrays.asList(keyboard1)));
        SceneManager.getInstance().getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());
        Map map = levelGenerator.getMap();
        gameView.setKeyPressed(keyboardController.getEventHandler());
        gameView.setWidthScene(map.getWidth() * map.getCellWid());
        gameView.setHeightScene(map.getHeight() * map.getCellHeight());

        ArrayList<Entity> sortedList = new ArrayList<>();

        for (List<Entity>[] ent : map.getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        sortedList.add(e);
                    }
                }
            }
        }

        sortedList.sort(comparator);
        for (Entity e: sortedList){
            gameView.addToScene(e.getGraphicsComponent().getCurrentImage());
        }
        initUI();
    }

    @Override
    public void update(Map map) throws FileNotFoundException {
        for (int i = 0; i < gameView.getChildren().size(); i++) {
            if (gameView.getChildren().get(i) == null)
                gameView.getChildren().remove(gameView.getChildren().get(i));
        }
        updateUI();

        if (!isCoinExist() && !endLevel) {
            endLevel = true;
            SoundManager.getInstance().stopAllSound();
            ++currentLvl;
            if (currentLvl > nbLevel) {
                displayMainTitle("Completed levels");
                EventManager.getEventManager().addEvent(new EventWinAllLevel(null, this, 20));
            } else {
                displayMainTitle("Level change");
                EventManager.getEventManager().addEvent(new EventChangeLevel(null, this, 20));
            }
        }
    }

    /**
     * Lets you know if there are any gums left in the level
     * @return Lets you know if there are any gums left in the level
     */
    private boolean isCoinExist() {
        for (List<Entity>[] ent : gameManager.getMap().getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        if (e.getName().equals(EntityType.Coin.name))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Initializes the UI part of the score and the life
     */
    public void initUI(){
        scoreUI.setColor(Color.WHITE);
        scoreUI.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),25);

        vieUI.setColor(Color.RED);
        vieUI.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),25);

        bestScore.setColor(Color.WHITE);
        bestScore.changeFont(getClass().getResourceAsStream("/Font/Font4.ttf"),22);

        gameView.addToScene(scoreUI.getLabel());
        gameView.addToScene(vieUI.getLabel());
        gameView.addToScene(bestScore.getLabel());
    }

    /**
     * Updates the display of score and life
     */
    public void updateUI() throws FileNotFoundException {
        scoreUI.update("Score: " + GameModel.getInstance().getPacmanModel().getScore());
        vieUI.update("Lives: " + GameModel.getInstance().getPacmanModel().getPV());
    }

    @Override
    public View getView() {
        return gameView;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public View getGameView() {
        return gameView;
    }

    /**
     * Displays a text in the middle of the scene
     * @param name text content
     */
    public void displayMainTitle(String name) {
        LabelUI labelChangeLvl = new LabelUI(name, gameView.getWidthScene() * 0.1, gameView.getHeightScene() * 0.5);
        labelChangeLvl.changeFont(getClass().getResourceAsStream("/Font/Font4.TTF"),20);
        labelChangeLvl.setColor(Color.YELLOW);
        gameView.addToScene(labelChangeLvl.getLabel());
    }

    /**
     * Function called when all levels have been passed.
     * Reinitialize the pacman parameters and puts the current scene in the main menu
     */
    public void getBackMenuWin() throws FileNotFoundException {
        GameModel.getInstance().resetPacMan();
        SceneManager.getInstance().setSceneView(new MenuController());
        SoundManager.getInstance().stopAllSound();
        GameHelper.setGameManager(null);
    }

    /**
     * Set up the new level
     */
    public void setNewLevel() throws FileNotFoundException {
        this.levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentLvl + ".txt");
        GameModel.getInstance().setLevelGenerator(levelGenerator);
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public void setEndLevel(boolean endLevel) {
        this.endLevel = endLevel;
    }

    public static Score getScore() {
        return score;
    }

    public static void setSessionBestScore(int sessionBestScore) {
        GameViewController.sessionBestScore = sessionBestScore;
    }

    public void setBestScore(){
        bestScore =new LabelUI("bestScore: " + score.getScoreFile(),180,470);
        if (sessionBestScore > Integer.parseInt(score.getScoreFile())){
            bestScore.update("bestScore: " + sessionBestScore);
        }
    }
}
