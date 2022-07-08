package ir.ac.kntu.engine.phy;

class DetectHit {

    private static boolean hit(BoxCollider collider1, BoxCollider collider2){
        return (collider1.getPos1().getX() < collider2.getPos2().getX() &&
                collider1.getPos2().getX() > collider2.getPos1().getX() &&
                collider1.getPos1().getY() < collider2.getPos2().getY() &&
                collider1.getPos2().getY() > collider2.getPos1().getY());
    }

    static boolean hit(BoxCollider boxCollider, Collider collider){
        if(collider instanceof BoxCollider)
            return hit(boxCollider, (BoxCollider)collider);
        return false;
    }
}
