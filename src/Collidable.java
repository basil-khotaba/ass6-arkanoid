

/**
 * Author: Basil Khotaba.
 * collidable class
 */
public interface Collidable {
    /******************************************************
     * @return the shape of the collidable object
     ******************************************************/
    Rectangle getCollisionRectangle();

    /**************************************************************************************************************
     *  Notify the object that we collided with it at collisionPoint with
     *  a given velocity.
     *  The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint  the place of occurring collision
     * @param currentVelocity the current velocity of the object
     * @param hit ball hit
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *****************************************************************************************************************/
    Velocity hit(Ball hit, Point collisionPoint, Velocity currentVelocity);
}
