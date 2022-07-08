package ir.ac.kntu.gameplay;

import ir.ac.kntu.engine.core.GameHelper;
import ir.ac.kntu.gameplay.scene.MenuController;

/**
 * Github:
 * https://github.com/helixgit2001/Pacman-helia-ghorabani-P2
 * @ author Helia Ghorbani
 * Class used to launch the Helix game
 */
public class Main {

    public static void main(String[] args) {
        GameHelper.setTitle("PACMAN");
        MenuController menuController = new MenuController();
        GameHelper.setSceneController(menuController);
        GameHelper.setPathIcon("/Image/pacman/pacmanRight.png");
        GameHelper.startGame();
    }
}
