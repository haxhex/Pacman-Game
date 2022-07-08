package ir.ac.kntu.gameplay.al_movement;

import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.al.AL;
import ir.ac.kntu.engine.al.BFS;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.gameplay.py.Displacement;

import java.util.List;
import java.util.TimerTask;

/**
 * Class defining the shortest path AL
 */
public class RandomShortestPathAl extends TimerTask implements AL {
    private BFS bfs;
    private Entity origin;
    private Entity target;
    private Double lastPos;

    public RandomShortestPathAl(){
        lastPos = null;
    }

    @Override
    public void run() {

    }

    public void setOrigin(Entity origin) {
        this.origin = origin;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setBfs(BFS bfs) {
        this.bfs = bfs;
    }

    /**
     * Function used to define the next position of the entity passed as a parameter
     * @param entity
     */
    @Override
    public void update(Entity entity){

        if(bfs == null) return;
        if(! canChangeDirection()) return;

        Position originPosition = bfs.getMap().getPositionEntity(origin);
        List<Position> listPositions = bfs.pathFinding(origin, target);
        if(listPositions.size() == 0){
            entity.setPositioning(lastPos);
            return;
        }

        Position nextPosition = (listPositions.size() == 1) ?
                listPositions.get(0) : listPositions.get(listPositions.size()-2);
        Displacement result;
        if(nextPosition.getX() != originPosition.getX()){
            if(nextPosition.getX() > originPosition.getX())
                result = Displacement.RIGHT;
            else if(nextPosition.getX() < originPosition.getX())
                result = Displacement.LEFT;
            else
                result = Displacement.NOTHING;
        } else {
            if(nextPosition.getY() > originPosition.getY())
                result = Displacement.DOWN;
            else if(nextPosition.getY() < originPosition.getY())
                result = Displacement.UP;
            else
                result = Displacement.NOTHING;
        }

        int randomChoice = (int) (Math.random() * 2);
        if(result == Displacement.NOTHING) return;

        double orientation = result.orientation;
        if(randomChoice == 1)
            orientation = (orientation+180.0) % 360;

        lastPos = orientation;
        entity.setPositioning(orientation);
    }

    /**
     * function to detect if the change of direction is possible
     * @return
     */
    private boolean canChangeDirection(){
        return (origin.getPosition().getX()% bfs.getMap().getMap().getCellWid() == 0)
                && (origin.getPosition().getY()% bfs.getMap().getMap().getCellHeight() == 0);
    }
}
