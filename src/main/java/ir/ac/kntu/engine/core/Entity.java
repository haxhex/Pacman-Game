package ir.ac.kntu.engine.core;

import ir.ac.kntu.engine.controller.ControllerComp;
import ir.ac.kntu.engine.graphic.Graphics;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.phy.PhyComp;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Game Object Class
 */
public class Entity {
    private Position position;
    private String name;
    private Double positioning;
    private ControllerComp controllerComp;
    private PhyComp phyComp;
    private Graphics graphics;

    public Entity() {
    }
    public Entity(Entity entity) {
        this.position = entity.getPosition();
        this.name = entity.name;
        this.positioning = entity.positioning;
        this.controllerComp = entity.controllerComp;
        this.phyComp = entity.phyComp;
        this.graphics = entity.graphics;
    }

    public static ArrayList<Entity> copyArray (ArrayList<Entity> entities){
        ArrayList<Entity> copyEntities = new ArrayList<>();
        for (Entity entity : entities) copyEntities.add(new Entity(entity));
        return copyEntities;

    }


    /**
     * entity movement by Input Controller
     */
    public void move() throws FileNotFoundException {
        if(controllerComp != null)
            controllerComp.update(this);
        if (phyComp != null)
            phyComp.update(this);
    }

    /**
     * Function called at each frame
     * update of the display of the entity
     */
    public void update(){
        if (graphics != null)
            graphics.update(this);
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setControllerComponent(ControllerComp controllerComp) {
        this.controllerComp = controllerComp;
    }

    public void setPhysicsComponent(PhyComp phyComp) {
        this.phyComp = phyComp;
    }

    public void setGraphicsComponent(Graphics graphics) {
        this.graphics = graphics;
    }

    public void setPositioning(Double positioning){
        this.positioning = positioning;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public PhyComp getPhysicsComponent(){
        return phyComp;
    }

    public Double getPositioning(){
        return positioning;
    }

    public Graphics getGraphicsComponent() {
        return graphics;
    }

    public ControllerComp getControllerComponent() {
        return controllerComp;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", positioning=" + positioning +
                ", controllerComp=" + controllerComp +
                ", phyComp=" + phyComp +
                ", graphics=" + graphics +
                '}' +"\n";
    }
}

