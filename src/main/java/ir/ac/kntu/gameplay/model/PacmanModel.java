package ir.ac.kntu.gameplay.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class containing pacman stats information
 */
public class PacmanModel {

    private final int P = 10000;
    private int score, pl;
    private boolean isRed;
    private boolean isDead;
    private int p;
    private ArrayList<String> events = new ArrayList<>();


    public PacmanModel() throws FileNotFoundException {
        score = 0;
        pl = 3;
        isRed = false;
        isDead = false;
        p = P;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    public void incrementPL(){
        pl++;
        String s = "remaining life : " + pl;
        events.add(s);
        System.out.println(s);
    }

    public void decrementPL() throws FileNotFoundException {
        if(checkNull()) return;
        pl--;
        String s = "remaining life : " + pl;
        events.add(s);
        System.out.println(s);

    }

    public int getPV(){
        return pl;
    }
    public int getScore(){
        return score;
    }

    /**
     * function to detect the death of the pacman, triggering the death event
     * @return
     */

    /**
     * Add score
     * check if the score is high enough to get a life
     * @param value
     */
    public void addScore(int value) throws FileNotFoundException {
        score += value;
        if (this.score >= this.p){
            incrementPL();
            events.add("Life increment");
            this.p += P;
        }
        String s = "Score : " + score;
        System.out.println(s);
        events.add(s);
    }
    public void writeFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:\\Users\\ASUS\\Desktop\\events.txt");
        for (String event : events) printWriter.println(event);
        printWriter.flush();
        printWriter.close();

    }
    public boolean checkNull() throws FileNotFoundException {
        if (pl == 0)
            writeFile();
        return pl <= 0 ;
    }

    /**
     * activating pacman's super mode, making him invisible
     * @param powPac
     */
    public void setRed(boolean powPac) {
        isRed = powPac;
    }

    public boolean isRed() {
        return isRed;
    }

    public boolean isDead() {

        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
