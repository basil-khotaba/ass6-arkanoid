

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Author: Basil Khotaba.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line leftLine, rightLine, upLine, downLine;

    /*********************************************************
     * creating a Rectangle from upperLeft point.
     * @param upperLeft the upper left point of the Rectangle
     * @param width  the width of the Rectangle
     * @param height the height of the Rectangle
     ********************************************************/
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // we put + height because the y axis in the gui is to the down
        // not as we know put in the math must be - height
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        // setting the lines given one point left
        Point point1 = new Point(upperLeft.getX(), upperLeft.getY()), point2 = new Point(upperLeft.getX() + width,
                upperLeft.getY()), point3 = new Point(upperLeft.getX(), upperLeft.getY() + height),
                point4 = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upLine = new Line(point1, point2);
        this.leftLine = new Line(point1, point3);
        this.rightLine = new Line(point2, point4);
        this.downLine = new Line(point3, point4);
    }

    /********************************************
     * @return returning the rectangle's width.
     ********************************************/
    public double getWidth() {
        return this.width;
    }

    /********************************************
     * @return  returning the rectangle's height.
     *******************************************/
    public double getHeight() {
        return this.height;
    }

    /******************************************************
     * @return returning the rectangle's upper left point.
     ******************************************************/
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /************************************************
     * @return returning the rectangle's left line.
     ************************************************/
    public Line getLeftLine() {
        return this.leftLine;
    }

    /*************************************************
     * @return  returning the rectangle's right line.
     *************************************************/
    public Line getRightLine() {
        return this.rightLine;
    }

    /************************************************
     * @return  returning the rectangle's upper line.
     ************************************************/

    public Line getUpLine() {
        return this.upLine;
    }

    /**********************************************
     * @return , returning the rectangle's low line.
     **********************************************/

    public Line getLowLine() {
        return this.downLine;
    }

    /**********************************************************************************
     * in this function we find all the intersection point max 2 min 0 of a given line.
     * and with a given rectangle checking intersecting with the line of the rectangle
     * @param line : the line to find intersection points between it and the rectangle.
     * @return List of intersection points with the specified line (possibly empty).
     **********************************************************************************/

    public List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectList = new ArrayList<Point>();

        if (line.isIntersecting(this.upLine)) {

            intersectList.add(this.upLine.intersectionWith(line));
        }
        if (line.isIntersecting(this.downLine)) {

            intersectList.add(this.downLine.intersectionWith(line));

        }
        if (line.isIntersecting(this.leftLine)) {

            intersectList.add(this.leftLine.intersectionWith(line));

        }
        if (line.isIntersecting(this.rightLine)) {

            intersectList.add(this.rightLine.intersectionWith(line));

        }
        if (!intersectList.isEmpty()) {
            return intersectList;
        } else {
            return new ArrayList<>(Collections.emptyList());
        }
    }


}