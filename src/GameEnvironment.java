import java.util.ArrayList;
import java.util.List;

/**
 * Author: Basil Khotaba.
 * GameEnvironment class
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /*****************************************
     * initialize the list of the collidables.
     *****************************************/

    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /********************************************************************
     * Adding a new collidable.
     * @param c a collidable that we want to add to our list collidables.
     ********************************************************************/
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /************************************************
     * @param c the collidable that we want to remove
     **************************************************/
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /******************************************************
     * @return , returrning the list of collidable objects.
     *******************************************************/
    public List<Collidable> collidablesColliction() {
        return this.collidables;
    }

    /*************************************************************************
     *  Assume an object moving from line.start() to line.end().
     *  If this object will not collide with any of the collidables
     *  in this collection, return null. Else, return the information
     *  about the closest collision that is going to occur.
     * @param trajectory : the path of the ball.
     * @return the closest collision and the  object that it will collide with.
     **************************************************************************/
    public CollisionInfo getClosestCollision(Line trajectory) {

        Point closestPoint = null;
        Collidable collidingObject = null;
        Point[] collisionPointsArray = new Point[collidables.size()];
        double baseDistance = 0;
        int fPoint; // the first Point
        Rectangle shape;
        //getting the closest intersection point to the start of trajectory foreach collidable object.

        for (int i = 0; i < collidables.size(); i++) {
            Collidable isColliding = (Collidable) collidables.get(i);
            shape = isColliding.getCollisionRectangle();
            collisionPointsArray[i] = trajectory.closestIntersectionToStartOfLine(shape);
        }

        // getting the closest point to the start of the trajectory line.
        for (fPoint = 0; fPoint < collidables.size(); fPoint++) {
            if (collisionPointsArray[fPoint] != null) {
                // getting a temporary closest point to the start of the line using the "distance" function.
                closestPoint = collisionPointsArray[fPoint];
                baseDistance = trajectory.start().distance(closestPoint);
                collidingObject = (Collidable) collidables.get(fPoint);
                break;
            }
        }

        for (int i = fPoint + 1; i < collidables.size(); i++) {
            // if here is no point , well continue
            if (collisionPointsArray[i] == null) {
                continue;
            }

            // comparing the points to get the closest one using the "distance" function.
            double distanceToCompare = trajectory.start().distance(collisionPointsArray[i]);
            if (distanceToCompare < baseDistance) {
                closestPoint = collisionPointsArray[i];
                collidingObject = (Collidable) collidables.get(i);
                baseDistance = distanceToCompare;
            }
        }
        //there is not any intersection points.
        if (closestPoint == null) {
            return null;
        }
        // else we will return the closest point and colliding object.
        return new CollisionInfo(closestPoint, collidingObject);
    }



}