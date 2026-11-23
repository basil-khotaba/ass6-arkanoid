/**
 * Author: Basil Khotaba.
 */
public class Point {

    private double x;
    private double y;
    /******************************************
     * @param x is the x coordinates of a point
     * @param y is the x coordinates of a point
     * THIS IS THE constructor of Point
     ******************************************/
    public Point(double x , double y) {
        //the x coordinate of the point
        this.x = x;
        //the y coordinate of the point
        this.y = y;
    }
    /******************************************
     * @return  the x value of this point
     ******************************************/
    public double getX() {
        //return the x coordinate of the point
        return this.x;
    }
    /******************************************
     * @return  the y value of this point
     ******************************************/
    public double getY() {
        //return the y coordinate of the point
        return this.y;
    }
    /*************************************************************
     * @param start is the start point of a line
     * @param end is the end point of a line
     * @return the middle x of a line between end and start points
     *************************************************************/
    //calculates the middle point x coordinate given a start and an end point .
    public double dsitmiddleX(Point start , Point end) {
        return (start.x + end.x) / 2;
    }

    /*************************************************************
     * @param start is the start point of a line
     * @param end is the end point of a line
     * @return the middle y of a line between end and start points
     *************************************************************/
    //calculates the middle point y coordinate given a start and an end point .
    public double dsitmiddleY(Point start , Point end) {
        return (start.y + end.y) / 2;
    }

    /*************************************************************
     * @param  other is an a other point
     * @return the distance of this point to the other point
     *************************************************************/
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

    }
    /****************************************************************
     * @param  other is an a other point
     * @return true or false if tow points is equals one to the other
     ****************************************************************/
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }


    /*************************************************
     * sets a new x to the point.
     * @param newX - new X position we want to set.
     *************************************************/
    public void setX(double newX) {

        this.x = newX;
    }


}
