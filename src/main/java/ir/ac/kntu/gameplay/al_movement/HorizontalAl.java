package ir.ac.kntu.gameplay.al_movement;

import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.engine.al.AL;
import ir.ac.kntu.engine.core.Entity;

import java.io.FileNotFoundException;
import java.util.TimerTask;


/**
 * Class defining the Horizontal Al
 */
public class HorizontalAl extends TimerTask implements AL {

    private final Displacement[] possibleDisplacement;

    public HorizontalAl(){
        possibleDisplacement = new Displacement[]{Displacement.RIGHT, Displacement.LEFT,Displacement.NOTHING};
    }

    @Override
    public void run() {
    }


    /**
     * method defining the next movement of the entity passed as a parameter
     * @param entity
     */
    public void update(Entity entity) throws FileNotFoundException {
        if(!canChangeDirection(entity)) return;

        int choose = (int) (Math.random()*possibleDisplacement.length);
        if(possibleDisplacement[choose] != Displacement.NOTHING)
            entity.setPositioning(possibleDisplacement[choose].orientation);
    }

    /**
     * function to detect if the change of direction is possible
     * @return
     */
    private boolean canChangeDirection(Entity entity) throws FileNotFoundException {
        return (entity.getPosition().getX() % GameModel.getInstance().getMap().getCellWid() == 0)
                && (entity.getPosition().getY() % GameModel.getInstance().getMap().getCellHeight() == 0);
    }
}
