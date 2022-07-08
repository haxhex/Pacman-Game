package ir.ac.kntu.engine.core;


import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.ui.SceneManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Global game control class
 * Control the scene, the event manager, the entities, the positions of the entities
 */
public class GameManager {
    private Map map;
    private final EventManager eventManager;
    private final List<Entity> entities;
    private final List<Position> entitiesPosition;

    public GameManager(Map map){
        this.map = map;
        eventManager = EventManager.getEventManager();
        this.entities = new ArrayList<>();
        this.entitiesPosition = new ArrayList<>();
    }

    /**
     * call function at each frame
     */
    public void update() throws IOException, InterruptedException {
        updateEvents();
        updateListEntities();
        updateMovesAndListener();
        updateListEntities();
        updateEntities();
        SceneManager.getInstance().update(map);
    }

    /**
     * update the list with all entities present in game
     */
    private void updateListEntities(){
        entities.clear();
        entitiesPosition.clear();
        List<Entity>[][] matrix = map.getMatrix();

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null && matrix[y][x].size() != 0) {
                    for(Entity e : matrix[y][x]){
                        entitiesPosition.add(new Position(x, y));
                        entities.add(e);
                    }
                }
            }
        }
    }

    /**
     * call of the event manager by frame
     */
    private void updateEvents() throws IOException {
        eventManager.manage();
    }

    /**
     * update of the components of each entity
     */
    private void updateMovesAndListener() throws InterruptedException, FileNotFoundException {
        for(int i = 0; i < entities.size()-1; i++){
            Entity entity1 = entities.get(i);
            if(entity1.getPhysicsComponent() != null && entity1.getPhysicsComponent().getCollider() != null){
                entity1.move();
                if(entity1.getPhysicsComponent().getCollider().exit(map.getLimitTopLeft(), map.getLimitBottomRight())){
                    entity1.getPhysicsComponent().onExit(entity1);
                }

                for (Entity entity2 : entities) {
                    if ( (!entity1.equals(entity2)) && entity2.getPhysicsComponent() != null && entity2.getPhysicsComponent().getCollider() != null) {
                        if (entity2.getPhysicsComponent().getCollider().hit(entity1.getPhysicsComponent().getCollider())) {
                            entity1.getPhysicsComponent().onCollision(entity1, entity2);
                            entity2.getPhysicsComponent().onCollision(entity2, entity1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Manage the call of the update functions of each entity in the scene
     */
    private void updateEntities(){
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i) != null){
                Position pos = entitiesPosition.get(i);
                Position distPos = entities.get(i).getPosition();
                entities.get(i).update();

                int dst_x = (int) ((distPos.getX() + map.getCellWid()/2) / map.getCellWid());
                int dst_y = (int) ((distPos.getY() + map.getCellHeight()/2) / map.getCellHeight());
                map.swap((int) pos.getX(), (int) pos.getY(), dst_x, dst_y, entities.get(i));
            }
        }
    }

    public Map getMap(){
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
