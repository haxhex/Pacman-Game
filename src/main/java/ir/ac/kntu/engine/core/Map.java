package ir.ac.kntu.engine.core;

import ir.ac.kntu.engine.phy.Position;

import java.util.List;

public class Map {
    private List<Entity>[][] matrix;
    private double cellHeight, cellWid;
    private Position limitTopLeft, limitBottomRight;

    /**
     * Class used to manage the game map
     * @param limitTopLeft Limit of play at top left position
     * @param limitBottomRight Limit of play at the bottom right position
     */
    public Map(Position limitTopLeft, Position limitBottomRight){
        this.limitTopLeft = limitTopLeft;
        this.limitBottomRight = limitBottomRight;
    }

    public List<Entity> getEntity(int x, int y){
        return matrix[y][x];
    }

    public void addEntity(int x, int y, Entity entity){
        matrix[y][x].add(entity);
    }

    public void deleteEntity(Position position, Entity entity){
        if(position == null) return;
        matrix[(int)position.getY()][(int)position.getX()].remove(entity);
    }

    public void swap(int srcX, int srcY, int dstX, int dstY, Entity entity){
        int idx = matrix[srcY][srcX].indexOf(entity);
        if(idx != -1)
            matrix[dstY][dstX].add(matrix[srcY][srcX].remove(idx));
    }

    public Position getPositionEntity(Entity entity){
        if(entity == null) return null;

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++){
                for(Entity e : matrix[y][x]){
                    if(entity.equals(e))
                        return new Position(x, y);
                }
            }
        }
        return null;
    }

    public int getWidth(){
        return matrix[0].length;
    }

    public int getHeight(){
        return matrix.length;
    }

    public double getCellHeight() {
        return cellHeight;
    }

    public double getCellWid() {
        return cellWid;
    }

    public Position getLimitTopLeft() {
        return limitTopLeft;
    }

    public Position getLimitBottomRight() {
        return limitBottomRight;
    }

    public List<Entity>[][] getMatrix(){
        return matrix;
    }


    public void setMatrix(List<Entity>[][] matrix){
        this.matrix = matrix;
        this.cellWid = (limitBottomRight.getX() - limitTopLeft.getX()) / getWidth();
        this.cellHeight = (limitBottomRight.getY() - limitTopLeft.getY()) / getHeight();
    }

    public void setLimitTopLeft(Position limitTopLeft) {
        this.limitTopLeft = limitTopLeft;
    }

    public void setLimitBottomRight(Position limitBottomRight) {
        this.limitBottomRight = limitBottomRight;
    }

}
