import biuoop.DrawSurface;

/**
 * Author: Basil Khotaba.
 */
public interface Sprite {
    /*******************************************
     * drawing the given sprite on the sprite.
     * @param d drawing the sprite on the surface
     ********************************************/
    void drawOn(DrawSurface d);

    /************************************
     * notifying that the time is passed.
     ************************************/
    void timePassed();
}