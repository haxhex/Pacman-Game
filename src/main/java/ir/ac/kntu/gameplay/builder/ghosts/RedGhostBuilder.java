package ir.ac.kntu.gameplay.builder.ghosts;

import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.gameplay.py.GhostPhy;
import ir.ac.kntu.engine.al.AL;
import ir.ac.kntu.engine.core.builder.EntityBuilder;
import ir.ac.kntu.gameplay.al_movement.ShortestPathAl;
import ir.ac.kntu.engine.graphic.Graphics;
import ir.ac.kntu.engine.phy.BoxCollider;
import ir.ac.kntu.engine.phy.Position;

/**
 * Builder corresponding to the red ghost
 */
public class RedGhostBuilder extends EntityBuilder {
    private AL al;

    public AL getAl() {
        return al;
    }


    public RedGhostBuilder(ShortestPathAl shortestPathAl) {
        al = shortestPathAl;
    }

    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.GHOST.name);
    }

    @Override
    public void buildPosition() {
        entity.setPositioning(Displacement.NOTHING.orientation);
    }

    @Override
    public void buildContComp() {
        // AI setting
        entity.setControllerComponent(al);
    }

    @Override
    public void buildPhysComp(double length, double width) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + length, entity.getPosition().getY() + width);

        entity.setPhysicsComponent(new GhostPhy(1, new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double width, double height) {
        // Initialization of the graphics component
        Graphics graphics = new Graphics(2);
        graphics.setImage("/Image/ghost/GhostRed.png");
        graphics.setHeight(height);
        graphics.setWidth(width);
        entity.setGraphicsComponent(graphics);
    }
}
