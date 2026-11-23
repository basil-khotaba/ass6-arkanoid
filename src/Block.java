

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Basil Khotaba.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private java.awt.Color color;
    private int hitsNum;

    /**************************************
     * The constructor.
     * @param rectangle the rect
     * @param color  the color of the rect
     **************************************/
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rect = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /************************************
     * @return returning the blocks.
     ***********************************/
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**************************************************************************************************************
     *  Notify the object that we collided with it at collisionPoint with
     *  a given velocity.
     *  The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint  the place of occurring collision
     * @param currentVelocity the current velocity of the object
     * @param hitter ball hitting
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *****************************************************************************************************************/
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // blocks lines.
        Line upLine = this.rect.getUpLine();
        Line lowLine = this.rect.getLowLine();
        Line leftLine = this.rect.getLeftLine();
        Line rightLine = this.rect.getRightLine();


        double dx = 0, dy = 0;
        double collisionX = collisionPoint.getX(), collisionY = collisionPoint.getY();

        // if  we have hit the block's angles.

        if (collisionX == leftLine.start().getX() && collisionY == upLine.start().getY()
                || collisionX == leftLine.start().getX() && collisionY == lowLine.start().getY()
                || collisionX == rightLine.start().getX() && collisionY == upLine.start().getY()
                || collisionX == rightLine.start().getX() && collisionY == lowLine.start().getY()) {

            dx = -currentVelocity.getDx();
            dy = -currentVelocity.getDy();

            // if we have hit the block from right or left.
        } else if (collisionY == upLine.start().getY() || collisionY == lowLine.start().getY()) {
            dx = currentVelocity.getDx();
            dy = -(currentVelocity.getDy());
        } else if (collisionX == rightLine.start().getX() || collisionX == leftLine.start().getX()) {
            dx = -(currentVelocity.getDx());
            dy = currentVelocity.getDy();
        }

        this.notifyHit(hitter);

        return new Velocity(dx, dy);

    }


    /**
     * drawing the block on a given DrawSurface.
     *
     * @param surface : the surface to draw the block on.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        //drawing the frame


        //drawing the hitsCounter on the middle of the block

    }


    /*********************************************
     * notify the block that  the time has passed.
     ********************************************/

    public void timePassed() {
    }


    /********************************
     * add the block to the game.
     * @param g : our "Arkanoid" game.
     ********************************/
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * removing the block from specified game , (the block is also a collidable and a sprite).
     *
     * @param game : our "Arkanoid" game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * adding a hitListener the the listeners list.
     *
     * @param hl : the listener the will be added to the listeners list.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removing the hitListener from the listeners list.
     *
     * @param hl : the listener the will be removed from the listeners list.
     */

    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifying all listeners about a hit event , (we made a copy of te hitListeners before iterating over them to
     * avoid exception errors.
     *
     * @param hitter : the ball the made the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * returns the blocks current hitPoints.
     *
     * @return : the blocks current hitPoints.
     */
    public int getHitPoints() {
        return this.hitsNum;
    }


}
