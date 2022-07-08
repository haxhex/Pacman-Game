package ir.ac.kntu.engine.al;

import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.phy.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding the shortest path
 */
public class BFS {
    private MapRep map;
    private final List<String> crossEntities;


    public BFS(List<String> crossEntities){
        this.crossEntities = crossEntities;
    }

    public void setMap(MapRep map){
        this.map = map;
    }

    /**
     * Shortest path algorithm through colliders
     * @param origin position initial
     * @param target objective position
     * @return List position du critical path
     */
    public List<Position> pathFinding(Entity origin, Entity target){
        Position position_origin = map.getPositionEntity(origin);
        Position targetPos = map.getPositionEntity(target);

        if(targetPos == null || position_origin == null)
            return  new ArrayList<>();

        map.resetDistance();

        // Compute distance
        int step = 0;
        boolean isFinished = false;
        int width = map.getWidth(), height = map.getHeight();
        int[][] neighbors = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        List<Position> listPositions = new ArrayList<>();

        listPositions.add(position_origin);
        map.setDistance((int) position_origin.getX(), (int) position_origin.getY(), step);
        // search as end position not determined
        while(! isFinished){
            List<Position> newListPositions = new ArrayList<>();
            step++;

            for(Position pos : listPositions){
                for(int[] neighbor : neighbors){
                    int x = (int) pos.getX()+neighbor[0], y = (int) pos.getY()+neighbor[1];
                    if(x < 0 || y < 0 || x >= width || y >= height) continue;

                    if((map.getMap().getEntity(x, y) == null || map.getMap().getEntity(x, y).size() == 0) && map.getDistance(x, y) == -1){
                        newListPositions.add(new Position(x, y));
                        map.setDistance(x, y, step);
                    } else{
                        int dist = -1;
                        for(Entity e : map.getMap().getEntity(x, y)){
                            if((e == null || e.getPhysicsComponent() == null || target.equals(e) || isCross(e)) && map.getDistance(x, y) == -1){
                                dist = step;

                                if(target.equals(e))
                                    isFinished = true;
                            } else if(!isFinished && e != null && !isCross(e)){
                                dist = -1;
                                break;
                            }
                        }
                        if(dist >= 0){
                            newListPositions.add(new Position(x, y));
                            map.setDistance(x, y, dist);
                        }
                    }
                }
            }
            listPositions = newListPositions;
            isFinished = isFinished || listPositions.isEmpty();
        }


        // Compute Path
        int x = (int)targetPos.getX(), y = (int)targetPos.getY();
        listPositions = new ArrayList<>();
        listPositions.add(targetPos);

        if(map.getDistance(x, y) == -1)
            return new ArrayList<>();

        int dist = map.getDistance(x, y);
        while(map.getDistance(x, y) > 0){

            for(int[] neighbor : neighbors){
                int newX = x+neighbor[0], newY = y+neighbor[1];
                if(newX >= 0 && newY >= 0 && newX < map.getWidth() && newY < map.getHeight()){
                    int newDist = map.getDistance(newX, newY);
                    if((dist > newDist) && (newDist != -1)){
                        listPositions.add(new Position(newX, newY));
                        x = newX;
                        y = newY;
                        dist = newDist;
                        break;
                    }
                }
            }
        }

        return listPositions;
    }

    /**
     * check if an entity can be crossed
     * @param entity entity a checker
     * @return if the entity is traversable
     */
    private boolean isCross(Entity entity){
        String entityName = entity.getName();
        for(String name : crossEntities){
            if(entityName.equals(name))
                return true;
        }
        return false;
    }

    public MapRep getMap(){
        return map;
    }
}
