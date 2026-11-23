/**
 * Author: Basil Khotaba.
 * CollisionInfo class
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /***********************************************************************
     * returning the information about the collision.
     * @param collisionPoint the place coordinates that the collosion occurs
     * @param collidable  the collidable
     **********************************************************************/

    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /*********************************************************
     * @return the place coordinates that the collosion occurs.
     *********************************************************/

    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /*************************
     * @return the collidable.
     ************************/
    public Collidable collisionObject() {
        return this.collidable;
    }
}