package ir.ac.kntu.engine.phy;

/**
 * Class corresponding to a box allowing to detect collisions
 * associated with an entity
 */
public class BoxCollider implements Collider {
    private final Position pos1;
    private final Position pos2;
    private final Position centerPos;


    public BoxCollider(Position position1, Position position2) {
        this.pos1 = position1;
        this.pos2 = position2;
        double centerX = position1.getX() + (position2.getX()-position1.getX())/2;
        double centerY = position1.getY() + (position2.getY()-position1.getY())/2;
        this.centerPos = new Position(centerX, centerY);
    }

    @Override
    public void update(Position position_) {
        Position position = new Position(position_.getX()+(pos2.getX()- pos1.getX())/2, position_.getY()+(pos2.getY()- pos1.getY())/2);
        if (position.getX()> centerPos.getX()){
            pos1.setX(pos1.getX()+(position.getX()- centerPos.getX()));
            pos2.setX(pos2.getX()+(position.getX()- centerPos.getX()));
            centerPos.setX(position.getX());
        }
        else if (position.getX()< centerPos.getX()){
            pos1.setX(pos1.getX()-(centerPos.getX()-position.getX()));
            pos2.setX(pos2.getX()-(centerPos.getX()-position.getX()));
            centerPos.setX(position.getX());
        }
        if (position.getY()> centerPos.getY()){
            pos1.setY(pos1.getY()+(position.getY()- centerPos.getY()));
            pos2.setY(pos2.getY()+(position.getY()- centerPos.getY()));
            centerPos.setY(position.getY());
        }
        else if (position.getY()< centerPos.getY()){
            pos1.setY(pos1.getY()-(centerPos.getY()-position.getY()));
            pos2.setY(pos2.getY()-(centerPos.getY()-position.getY()));
            centerPos.setY(position.getY());
        }
    }

    @Override
    public boolean hit(Collider collider) {
        return DetectHit.hit(this, collider);
    }

    @Override
    public boolean exit(Position p1, Position p2){
        return (this.pos1.getX() < p1.getX() ||
                this.pos1.getY() < p1.getY() ||
                this.pos2.getX() > p2.getX() ||
                this.pos2.getY() > p2.getY());
    }

    public Position getPos1() {
        return pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    public void move(Position position) {

    }
}
