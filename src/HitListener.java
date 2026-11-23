/**
 * Author: Basil Khotaba.
 * HitListener class.
 *
 */
public interface HitListener {
    /**
     * This method is called whenever the "beingHit" object is hit.
     *
     * @param beingHit : the block that is being hit.
     * @param hitter   : the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}