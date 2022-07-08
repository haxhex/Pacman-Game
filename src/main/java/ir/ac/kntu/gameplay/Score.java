package ir.ac.kntu.gameplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.prefs.Preferences;

/**
 * Class to manage the best score
 */
public class Score {

    private final Preferences preferences = Preferences.userRoot().node("/Score/score.txt");

    /**
     * get the best score
     * @return score
     */
    public String getScoreFile(){
        return preferences.get("Score", "0");
    }

    /**
     * writes the best score locally.
     * @param score
     */
    public void setScoreFile(String score) throws FileNotFoundException {
        preferences.put("Score",score);

    }
}
