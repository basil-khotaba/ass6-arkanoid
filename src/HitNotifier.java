
/**
 * Author: Basil Khotaba.
 */
public interface HitNotifier {
    /*******************************************************************
     * Adds hl as a listener to hit events.
     *
     * @param hl : the listener the will be added to the listeners list.
     ********************************************************************/
    void addHitListener(HitListener hl);

    /***********************************************************************
     * removes hl as a listener to hit events.
     *
     * @param hl : the listener the will be removed from the listeners list.
     ***********************************************************************/
    void removeHitListener(HitListener hl);
}