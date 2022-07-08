package ir.ac.kntu.gameplay.al_movement;

import ir.ac.kntu.gameplay.model.GameModel;
import ir.ac.kntu.gameplay.py.GhostPhy;
import ir.ac.kntu.engine.phy.BoxCollider;
import ir.ac.kntu.engine.phy.PhyComp;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.al.AL;
import ir.ac.kntu.engine.al.BFS;
import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.gameplay.py.Displacement;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.TimerTask;

/**
 * Class defining the shortest path AL
 */
public class SmartShortestPathAl extends TimerTask implements AL {
    private BFS pathFinder;
    private Entity origin;
    private Entity anticipTarget;
    private Entity target;
    private Displacement lastDisplacement;
    private final int nbAnticipate;
    private List<Entity> lastEntities;

    public SmartShortestPathAl(){
        lastDisplacement = Displacement.NOTHING;
        nbAnticipate = 5;
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

    public void setPathFinder(BFS pathFinder) {
        this.pathFinder = pathFinder;
    }

    /**
     * Function used to define the next position of the entity passed as a parameter
     * @param entity
     */
    @Override
    public void update(Entity entity) throws FileNotFoundException {

        if(pathFinder == null) return;
        if(! canChangeDirection()) return;

        Position position_origin = pathFinder.getMap().getPositionEntity(origin);
        List<Position> listPositions = getAnticipPosition();
        if(listPositions.size() == 0){
            entity.setPositioning(lastDisplacement.orientation);
            return;
        }

        Position nextPosition = (listPositions.size() == 1) ?
                listPositions.get(0) : listPositions.get(listPositions.size()-2);
        Displacement result;
        if(nextPosition.getX() != position_origin.getX()){
            if(nextPosition.getX() > position_origin.getX())
                result = Displacement.RIGHT;
            else if(nextPosition.getX() < position_origin.getX())
                result = Displacement.LEFT;
            else
                result = Displacement.NOTHING;
        } else {
            if(nextPosition.getY() > position_origin.getY())
                result = Displacement.DOWN;
            else if(nextPosition.getY() < position_origin.getY())
                result = Displacement.UP;
            else
                result = Displacement.NOTHING;
        }

        lastDisplacement = result;
        if(result != Displacement.NOTHING)
            entity.setPositioning(result.orientation);
    }

    /**
     * function to detect if the change of direction is possible
     * @return
     */
    private boolean canChangeDirection(){
        return (origin.getPosition().getX()%pathFinder.getMap().getMap().getCellWid() == 0)
                && (origin.getPosition().getY()%pathFinder.getMap().getMap().getCellHeight() == 0);
    }

    private List<Position> getAnticipPosition() throws FileNotFoundException {
        anticipTarget = new Entity();
        anticipTarget.setPositioning(target.getPositioning());
        anticipTarget.setPosition(new Position(target.getPosition().getX(), target.getPosition().getY()));
        anticipTarget.setControllerComponent(null);
        anticipTarget.setGraphicsComponent(null);

        Position position1 = new Position(target.getPosition().getX(), target.getPosition().getY());
        Position position2 = new Position(target.getPosition().getX() + GameModel.getInstance().getMap().getCellWid(), target.getPosition().getY() + GameModel.getInstance().getMap().getCellHeight());
        PhyComp phyComp = new GhostPhy(1, new BoxCollider(position1, position2));
        anticipTarget.setPhysicsComponent(phyComp);
        for(int i = nbAnticipate; i > 0; i--)
            anticipTarget.getPhysicsComponent().update(anticipTarget);

        int x = (int) ((anticipTarget.getPosition().getX() + GameModel.getInstance().getMap().getCellWid()/2) / GameModel.getInstance().getMap().getCellWid());
        int y = (int) ((anticipTarget.getPosition().getY() + GameModel.getInstance().getMap().getCellHeight()/2) / GameModel.getInstance().getMap().getCellHeight());
        GameModel.getInstance().getMap().addEntity(x, y, anticipTarget);
        List<Position> listPositions = pathFinder.pathFinding(origin, anticipTarget);
        GameModel.getInstance().getMap().deleteEntity(new Position(x, y), anticipTarget);

        return listPositions;
    }
}
