package ir.ac.kntu.gameplay.builder.object;

import ir.ac.kntu.gameplay.EntityType;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.gameplay.py.ObjectPhy;
import ir.ac.kntu.engine.core.builder.EntityBuilder;
import ir.ac.kntu.engine.graphic.Graphics;
import ir.ac.kntu.engine.phy.BoxCollider;
import ir.ac.kntu.engine.phy.Position;

/**
 * Builder corresponding to coins
 */
public class CoinBuilder extends EntityBuilder {

    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.Coin.name);
    }

    @Override
    public void buildPosition() {
        entity.setPositioning(Displacement.NOTHING.orientation);
    }

    @Override
    public void buildPhysComp(double length, double width) {
        Position position1 = new Position(entity.getPosition().getX() + length /4, entity.getPosition().getY() + width /4);
        Position position2 = new Position(entity.getPosition().getX() + 3* length /4, entity.getPosition().getY() + 3* width /4);

        entity.setPhysicsComponent(new ObjectPhy(new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double width, double height) {
        // Initialization of the graphics component
        Graphics graphics = new Graphics(0);
        graphics.setImage("/Image/object/normalCoin.png");
        graphics.setHeight(height);
        graphics.setWidth(width);
        entity.setGraphicsComponent(graphics);
    }

    @Override
    public void buildContComp() {

    }
}
