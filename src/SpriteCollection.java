
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Basil Khotaba.
 */
public class SpriteCollection {
    private List<Sprite> spriteCollection;

    /******************************************
     *  The Constructor.
     * creating a list that have the sprites in
     ******************************************/
    public SpriteCollection() {

        this.spriteCollection = new ArrayList<>();

    }

    /*******************************************
     *adding a new sprite to our list.
     * @param s the new sprite that i want to add
     ********************************************/
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /*********************************************
     * @param s the sprite that we want to remove
     *********************************************/

    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
    /********************************************************************
     * calling the time passed cause every sprite to active his functions.
     ********************************************************************/
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteCollection.size(); i++) {
            Sprite spriteObject = spriteCollection.get(i);
            spriteObject.timePassed();
        }
    }

    /***********************************************************
     *calling the drawAll on to draw all the sprites in our list.
     * @param d : the surface to draw all the sprites on.
     **********************************************************/
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteCollection.size(); i++) {
            Sprite spriteObject = spriteCollection.get(i);
            spriteObject.drawOn(d);
        }
    }


}