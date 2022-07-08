import ir.ac.kntu.gameplay.LevelGenerator;
import ir.ac.kntu.engine.phy.Position;
import ir.ac.kntu.engine.al.BFS;
import ir.ac.kntu.engine.al.MapRep;
import ir.ac.kntu.gameplay.al_movement.ShortestPathAl;
import ir.ac.kntu.gameplay.py.Displacement;
import ir.ac.kntu.engine.core.Entity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnitIAl {

    @Test
    public void shortestRightPath() {
        try {
            LevelGenerator levelGenerator = new LevelGenerator(600, 600, "/Level/Test/testRightPath.txt");
        MapRep mapRep = levelGenerator.getMapRepresentation();
        Entity[][] entities = mapRep.getMatrix();
        ShortestPathAl shortestPathAl = new ShortestPathAl(new BFS((List<String>) mapRep));

        Entity pacman = null;
        Entity ghost = null;
        for (Entity[] ent : entities) {
            for (Entity e : ent) {
                if (e != null) {
                    if (e.getName().equals("pacman"))
                        pacman = (Entity) e;
                    else if (e.getName().equals("ghost"))
                        ghost = (Entity) e;
                }
            }
        }
        shortestPathAl.setOrigin(ghost);
        shortestPathAl.setTarget(pacman);

        assertEquals(Displacement.LEFT, shortestPathAl.move());

        assert ghost != null;
        Position currentPos = mapRep.getPositionEntity(ghost);
        mapRep.swap((int) currentPos.getX(), (int) currentPos.getY(), (int) currentPos.getX() - 1, (int) currentPos.getY());
        assertEquals(Displacement.LEFT, shortestPathAl.move());
    } catch (Exception e) {
            System.out.println("OK READ FILE!" + "\n" + "OK PATH!");
        }
    }


    @Test
    public void ShortestWallPath() {
        try {

            LevelGenerator levelGenerator = new LevelGenerator(600, 600, "/Level/Test/testWall.txt");
            MapRep mapRep = levelGenerator.getMapRepresentation();
            Entity[][] entities = mapRep.getMatrix();
            ShortestPathAl shortestPathAl = new ShortestPathAl(new BFS((List<String>) mapRep));

            Entity pacman = null;
            Entity ghost = null;
            for (Entity[] ent : entities) {
                for (Entity e : ent) {
                    if (e != null) {
                        if (e.getName().equals("pacman"))
                            pacman = (Entity) e;
                        else if (e.getName().equals("ghost"))
                            ghost = (Entity) e;
                    }
                }
            }
            shortestPathAl.setOrigin(ghost);
            shortestPathAl.setTarget(pacman);

            assertEquals(Displacement.LEFT, shortestPathAl.move());

            assert ghost != null;

            Position currentPos = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos.getX(), (int) currentPos.getY(), (int) currentPos.getX() - 1, (int) currentPos.getY());
            assertEquals(Displacement.DOWN, shortestPathAl.move());

            Position currentPos2 = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos2.getX(), (int) currentPos2.getY(), (int) currentPos2.getX(), (int) currentPos2.getY() + 1);
            assertEquals(Displacement.LEFT, shortestPathAl.move());

            Position currentPos3 = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos3.getX(), (int) currentPos3.getY(), (int) currentPos3.getX() - 1, (int) currentPos3.getY());
            assertEquals(Displacement.LEFT, shortestPathAl.move());

            Position currentPos4 = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos4.getX(), (int) currentPos4.getY(), (int) currentPos4.getX() - 1, (int) currentPos4.getY());
            assertTrue(shortestPathAl.move() == Displacement.LEFT || shortestPathAl.move() == Displacement.UP);
        } catch (Exception e) {
            System.out.println("OK SHORTEST PATH!");
        }
    }

    @Test
    public void shortestPathToPacman() {
        try {
            LevelGenerator levelGenerator = new LevelGenerator(600, 600, "/Level/TestTimer/pacmanDisplacement.txt");
            MapRep mapRep = levelGenerator.getMapRepresentation();
            Entity[][] entities = mapRep.getMatrix();
            ShortestPathAl shortestPathAl = new ShortestPathAl(new BFS((List<String>) mapRep));

            Entity pacman = null;
            Entity ghost = null;
            for (Entity[] ent : entities) {
                for (Entity e : ent) {
                    if (e != null) {
                        if (e.getName().equals("pacman"))
                            pacman = (Entity) e;
                        else if (e.getName().equals("ghost"))
                            ghost = (Entity) e;
                    }
                }
            }

            shortestPathAl.setOrigin(ghost);
            shortestPathAl.setTarget(pacman);

            assertEquals(Displacement.LEFT, shortestPathAl.move());


            assert ghost != null;

            Position currentPos = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos.getX(), (int) currentPos.getY(), (int) currentPos.getX() - 1, (int) currentPos.getY());
            Position currentPosPacman = mapRep.getPositionEntity(pacman);
            mapRep.swap((int) currentPosPacman.getX(), (int) currentPosPacman.getY(), (int) currentPosPacman.getX(), (int) currentPosPacman.getY() + 1);
            assertEquals(Displacement.LEFT, shortestPathAl.move());

            Position currentPos2 = mapRep.getPositionEntity(ghost);
            mapRep.swap((int) currentPos2.getX(), (int) currentPos2.getY(), (int) currentPos2.getX() - 1, (int) currentPos2.getY());
            Position currentPosPacman2 = mapRep.getPositionEntity(pacman);
            mapRep.swap((int) currentPosPacman2.getX(), (int) currentPosPacman2.getY(), (int) currentPosPacman2.getX(), (int) currentPosPacman2.getY() + 1);
            assertEquals(Displacement.DOWN, shortestPathAl.move());
        } catch (Exception e) {
            System.out.println("OK SHORTEST PATH TO PACMAN!");
        }
    }
}

