package ir.ac.kntu.gameplay.builder.ghosts;

import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.al_movement.RandomAL;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.gameplay.py.GhostPhy;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.core.builder.EntityBuilder;
import ir.ac.kntu.engine.graphic.Graphics;
import ir.ac.kntu.engine.phy.BoxCollider;
import ir.ac.kntu.engine.phy.Position;

/**
 * Builder corresponding to the zebra ghost
 */
public class ZebraGhostBuilder extends EntityBuilder {
    private final Map map;

    public ZebraGhostBuilder(Map map){
        this.map = map;
    }

    @Override
    public void buildPosition(Position position) {
        // AL setting
        position.setX(position.getX());
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
        entity.setControllerComponent(new RandomAL());
    }

    @Override
    public void buildPhysComp(double length, double width) {

        Position position1 = new Position(entity.getPosition().getX() , entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + length, entity.getPosition().getY() + width);

        entity.setPhysicsComponent(new GhostPhy(1, new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double length, double width) {
        Graphics graphics = new Graphics(2);
        graphics.setImage("/Image/ghost/GhostGreen.png");
        graphics.setHeight(width);
        graphics.setWidth(length);
        entity.setGraphicsComponent(graphics);
    }
}
