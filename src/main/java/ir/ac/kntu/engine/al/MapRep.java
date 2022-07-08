package ir.ac.kntu.engine.al;

import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Map;
import ir.ac.kntu.engine.phy.Position;

import java.util.Arrays;

/**
 * Matrix containing the level and all associated information
 */
public class MapRep {

    private final Map map;
    private final int[][] matrixDistance;
    private Entity[][] matrix;

    public MapRep(Map map){
        this.map = map;
        matrixDistance = new int[map.getHeight()][map.getWidth()];
    }

    /**
     *  return the position of an entity in the map
     * @param entity
     * @return
     */
    public Position getPositionEntity(Entity entity){
        return map.getPositionEntity(entity);
    }

    public void setDistance(int x, int y, int dist){
        matrixDistance[y][x] = dist;
    }

    public int getDistance(int x, int y){
        return matrixDistance[y][x];
    }

    public void resetDistance(){
        for (int[] ints : matrixDistance) {
            Arrays.fill(ints, -1);
        }
    }

    public int getWidth(){
        return matrixDistance.length;
    }

    public int getHeight(){
        return matrixDistance[0].length;
    }

    public int[][] getMatrixDistance(){
        return matrixDistance;
    }

    public Map getMap(){
        return map;
    }

    public void showDistance(){
        for(int y = 0; y < matrixDistance.length; y++){
            for(int x = 0; x < matrixDistance[y].length; x++) {
                System.out.print(matrixDistance[y][x] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Entity[][] getMatrix() {

        return matrix;
    }

    public void setMatrix(Entity[][] matrix) {
        this.matrix = matrix;
    }

    public void swap(int x, int y, int i, int y1) {

    }
}
