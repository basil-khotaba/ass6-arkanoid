/**
 * Author: Basil Khotaba.
 * Velocity class
 */
public class Velocity {
    private Point point;
    private double dx;
    private double dy;

    /**************************************************
     * @param dx is the velocity in the x-axis dirextion
     * @param dy is the velocity in the y-axis dirextion
     * The constructor
     *************************************************/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**************************************************
     * @param p is a point
     * @return new point after adding the dx and dy
     *************************************************/
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        this.point = new Point(p.getX(), p.getY());
        return new Point(point.getX() + dx, point.getY() + dy);

    }

    /************************************************
     * @return the dy velocity in the y-axis direction
     *************************************************/
    public double getDy() {
        return dy;
    }

    /************************************************
     * @return the dx velocity in the y-axis direction
     *************************************************/
    public double getDx() {
        return dx;
    }

    /********************************
     * @return the point that we get
     ********************************/
    public Point getPoint() {
        return point;
    }


    /**************************************************
     * @param angle the angle of the vector dx,dy velocity
     * @param speed of the ball vector
     * @return the velocity given ange and speed
     *************************************************/
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // sin and cos functions require radians to get the right result
        double dx = Math.sin(Math.toRadians(angle)) * speed; // i convert to radian
        double dy = -1 * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

}

