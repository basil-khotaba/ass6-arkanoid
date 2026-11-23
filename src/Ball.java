



import biuoop.DrawSurface;

import java.awt.Color;


/**
 * Author: Basil Khotaba.
 * the ball class
 */
public class Ball implements Sprite {

    private Point center;
    private double r;
    private Color color;
    private Velocity v;
    private GameEnvironment environment;



    /**********************************************
     * @param center is the center of the ball
     * @param r is the radios of the ball
     * @param color is the color of the ball
     *  The first constructor
     **********************************************/
    public Ball(Point center, int r , java.awt.Color color) {
        this.center = new Point(getX(), getY());
        this.r = r;
        this.color = color;
    }
    /**************************************************
     * @param x is the x coordinates center of the ball
     * @param y is the y coordinates center of the ball
     * @param r is the radios of the ball
     * @param color1 is the color of the ball.
     *  The second constructor
     *************************************************/
    public Ball(double x, double y, int r, Color color1) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color1;
    }


    /**********************************************
     * @return the x coordinates of the ball center
     **********************************************/
    public double getX() {
        return this.center.getX();
    }

    /**********************************************
     * @return the y coordinates of the ball center
     **********************************************/
    public double getY() {
        return this.center.getY();
    }

    /*******************************************
     * @return the size of the ball (the radios)
     *******************************************/
    public int getSize() {
        return (int) this.r;
    }


    /********************************
     * @return the color of the ball
     ********************************/
    public java.awt.Color getColor() {
        return this.color;
    }


    /*******************************************************
     * @param surface draw the ball on the given DrawSurface
     *******************************************************/
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.getSize());
    }


    /*******************************************************
     * @param v1 the new velocity to set
     *******************************************************/
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }


    /*******************************************************
     * @param dx the new velocity to set
     * @param dy the new velocity to set
     *******************************************************/
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**********************************
     * @return the velocity of the ball
     **********************************/
    public Velocity getVelocity() {
        return this.v;
    }

    /*********************************************
     * setting the ball's Game environment.
     * @param gameEnvironment : the game environment.
     ********************************************/

    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**************************************************************************
     * compute the ball trajectory (the trajectory is "how the ball will move
     *  without any obstacles" -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur).
     * Checked that ball is moving on this trajectory will hit anything.
     * If no, then i moved the ball to the end of the trajectory.
     * Otherwise (there is a hit):
     *  moving the ball to "almost" the hit point,and just slightly before it.
     * notify the hit object (using its hit() method) that a collision occurred.
     * update the velocity to the new velocity returned by the hit() method. .
     **************************************************************************/
    public void moveOneStep() {
        Point p = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, p);
        CollisionInfo col = this.environment.getClosestCollision(trajectory);
        if (col != null) {
            Point collisionPoint = col.collisionPoint();
            double epsilon = 0.000001;

            Line upLine = col.collisionObject().getCollisionRectangle().getUpLine();
            Line lowLine = col.collisionObject().getCollisionRectangle().getLowLine();
            Line leftLine = col.collisionObject().getCollisionRectangle().getLeftLine();
            Line rightLine = col.collisionObject().getCollisionRectangle().getRightLine();
            double collX = collisionPoint.getX();
            double collY = collisionPoint.getY();

            if (upLine.ifPointOnLine(collisionPoint)) {
                this.center = new Point(collX, collY - epsilon);
            }
            if (lowLine.ifPointOnLine(collisionPoint)) {
                this.center = new Point(collX, collY + epsilon);
            }
            if (rightLine.ifPointOnLine(collisionPoint)) {
                this.center = new Point(collX + epsilon, collY);
            }
            if (leftLine.ifPointOnLine(collisionPoint)) {
                this.center = new Point(collX - epsilon, collY);
            }
            //updating the velocity.
            this.v = col.collisionObject().hit(this, collisionPoint, this.v);
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }


    /*****************************************
     * notifying the ball that time has passed.
     *****************************************/

    public void timePassed() {
        moveOneStep();
    }

    /***********************************
     * @param g the game
     * add ing the ball into the game
     **********************************/

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**********************************
     * @param game the game
     * removing the ball from the game
     **********************************/
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /*******************************************
     * @param ballColor the color of the balls
     * we will set the balls color
     *******************************************/
    public void setColor(Color ballColor) {
        this.color = ballColor;
    }
}