
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
/**
 * Author: Basil Khotaba.
 */
/***********************************************************
 * a Note : this class not as the fist class that.
 * i submit in the second assignment cause there
 * was some problems in the intersection with the
 * verticals lines and some problems because of that
 * i search on the internet on the best way that i can
 * do that to solve these problem else there was some
 * problems to find the intersection point cause we deal
 * with a lines of the rectangle...
 * the change in the intersect function then i write this class
 * one more time and now is good than it was.
 * i surprised from this cause it was working good in
 * class AbstractDrawing ok then ... now better
 ************************************************************/
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color lineColor;


    /********************************************
     * @param start is the start point of a line.
     * @param end is the end point of a line
     * THIS IS THE constructor
     ********************************************/
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;

    }

    /**********************************************
     * @param x1 is the x coordinates of a point 1.
     * @param y1 is the y coordinates of a point 1
     * @param x2 is the x coordinates of a point 2
     * @param y2 is the y coordinates of a point 2
     * @param color is the line color
     * THIS IS THE constructor
     **********************************************/
    public Line(double x1, double y1, double x2, double y2, Color color) {
        // creating the points
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.lineColor = color;

    }


    /**********************************************************
     * @return the length of the line given start and end point.
     ***********************************************************/
    public double length() {
        return start.distance(end);
    }


    /**********************************************
     * @return the middle point coordinates x and y.
     **********************************************/
    public Point middle() {
        //calculating the mid point.
        double x = this.start.getX() + this.end.getX();
        double y = this.start.getY() + this.end.getY();
        return new Point((x / 2), (y / 2));
    }

    /**********************************************
     * @return Returns the start point of the line.
     **********************************************/
    public Point start() {
        return this.start;
    }

    /**********************************************
     * @return Returns the end point of the line.
     **********************************************/
    public Point end() {
        return this.end;
    }

    /******************************************************************************************
     * Orientation of an ordered triplet of points in the plane can be:
     * counterclockwise.
     * clockwise
     * colinear
     * @param a : Point in the plane.
     * @param b : Point in the plane.
     * @param c : Point in the plane.
     * @return 0 if a,b and c are Co-linear
     ********************************************************************************************/
    int orientation(Point a, Point b, Point c) {

        //orientation formula.

        double value = (b.getY() - a.getY()) * (c.getX() - b.getX()) - (b.getX() - a.getX()) * (c.getY() - b.getY());

        if (value > 0) {
            return 1; //clockwise
        }
        if (value == 0) {
            return 0; //colinear
        } else {
            return 2; //counterclockwise
        }
    }

    /****************************************************
     * Given three co-linear points a,b and c.
     * the function checks if point b lies on line ac
     * @param a : starting point of the line.
     * @param b : the point that we check if its on the line.
     * @param c : ending point of the line .
     * @return true if b  on ac line , else false.
     *********************************************************/
    boolean onSegment(Point a, Point b, Point c) {
        //this is done by checking if the coordinates of b ranging from the coordinates of a to c's coordinates.

        if (b.getX() <= Math.max(a.getX(), c.getX()) && b.getX() >= Math.min(a.getX(), c.getX())
                && b.getY() <= Math.max(a.getY(), c.getY()) && b.getY() >= Math.min(a.getY(), c.getY())) {
            return true;
        }
        return false;
    }

    /************************************************************
     * checking if a given point is on the line or not.
     * @param b : the point to check if it lie's on a certain line.
     * @return a boolean stating if the point leis on the line or not.
     ****************************************************************/
    boolean ifPointOnLine(Point b) {
        Point a = this.start;
        Point c = this.end;
        return onSegment(a, b, c);
    }

    /*******************************************************************
     * Check if given two lines is intersect.
     * @param other : line to check intersection with it.
     * @return true if there is an intersection else false
     *****************************************************************/
    public boolean isIntersecting(Line other) {
        Point a = this.start;
        Point b = this.end;
        Point c = other.start;
        Point p4 = other.end;

        //finding the four orientations needed.
        int firstOrientation = orientation(a, b, c);
        int secondOrientation = orientation(a, b, p4);
        int thirdOrientation = orientation(c, p4, a);
        int fourthOrientation = orientation(c, p4, b);

        //General case.
        if (firstOrientation != secondOrientation && thirdOrientation != fourthOrientation) {
            return true;
        }

        //Special cases.

        if (firstOrientation == 0 && onSegment(a, c, b) || secondOrientation == 0 && onSegment(a, p4, b)
                || thirdOrientation == 0 && onSegment(c, a, p4) || fourthOrientation == 0 && onSegment(c, b, p4)) {
            return true;
        }
        return false;
    }

    /******************************************
     * getTangent means that is the slope.
     * of the line we calculating it
     * @return the calculated slope of the line.
     ******************************************/
    public double getTangent() {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        //slope formula.
        return (y2 - y1) / (x2 - x1);
    }

    /******************************************
     * we can find b from the equation.
     * y = ax+b =>  b= y-ax abd that what i does
     * @param m the slope of the line
     * @param point : line's start point.
     * @return b in the that in equation y=ax+b.
     *******************************************/

    public double getB(double m, Point point) {
        // b is equal to y - the slope * x
        return (point.getY() - m * (point.getX()));
    }

    /**************************************************************************
     * @param other is the second line we check with.
     * @return the intersection point if the lines intersect,and null otherwise.
     **************************************************************************/
    public Point intersectionWith(Line other) {
        double x, y;
        double m1 = this.getTangent(); // slope line there is function to deal
        double m2 = other.getTangent();
        double b1 = getB(m1, this.start);
        double b2 = getB(m2, other.start);

        if (this.start.equals(other.start()) || this.start.equals(other.end())) {
            return this.start;
        }
        if (this.end.equals(other.end()) || this.end.equals(other.start())) {
            return this.end;
        }
        // dealing with if have an infinity slope
        if (this.start.getX() == this.end.getX()) {
            x = this.start.getX();
            y = m2 * x + b2;
        } else if (other.start.getX() == other.end.getX()) {
            x = other.start.getX();
            y = m1 * x + b1;
        } else {
            x = (b1 - b2) / (m2 - m1); // the intersection point
            // from y1 = m1x+b1 and y2 = m2x+b2 we can play with
            // these equation and get the intersection in math
            y = (m1 * x) + b1; //i put x in these equation
        }
        return new Point(x, y);
    }


    /********************************************************************
     * check if the two lines are equal by checking  start and end points.
     * @param other : the other line that we is comparing with
     * @return true if the two lines segments are equal , false otherwise.
     ********************************************************************/
    public boolean equals(Line other) {
        Point a = this.start;
        Point b = this.end;
        // i will check if the line have the same start and
        //end point and this is equality else not
        Point c = other.start();
        Point p4 = other.end();
        if ((a.equals(c) && b.equals(p4)) || ((a.equals(p4) && b.equals(c)))) {
            return true;
        }
        return false;
    }
    /**********************************************************************
     * If this line does not intersect with the rectangle, return null.
     *   Otherwise, return the closest intersection point to the
     *  start of the line.
     * @param rect : the rectangle that we is checking on it the collision
     * @return the closest intersection point from the start of the given line.
     ************************************************************************/
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // i save the index of the minimum and return it
        // its a simple thing
        int j = 0, i;
        int index = 0;
        ArrayList<Point> point = (ArrayList<Point>) rect.intersectionPoints(this);
        // if the list no empty
        if (!point.isEmpty()) {
            double min = point.get(0).distance(this.start);
            for (i = 0; i <= point.size(); i++) {
                // i+1 because the size of the
                //list starting from 1 not 0 and the array from 0
                if (point.size() > i + 1) {
                    // the distance from the start line
                    if (min > point.get(i + 1).distance(this.start)) {
                        min = point.get(i + 1).distance(this.start);
                        index = i + 1;
                    }
                }
            }
            // at all we save the index and  i will
            // return this as a new point
            double x = point.get(index).getX();
            double y = point.get(index).getY();
            return new Point(x, y);

        } else {
            return (Point) null;
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(lineColor);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) end.getY());
    }

    @Override
    public void timePassed() {

    }
}

