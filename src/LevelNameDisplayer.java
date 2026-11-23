
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The LevelNameDisplayer class.
 */
public class LevelNameDisplayer implements Sprite {
    private String levelName;

    /**
     * the constructor.
     *
     * @param levelName : the name of the current level.
     */
    public LevelNameDisplayer(String levelName) {
        this.levelName = levelName;
    }

    /**
     * draws the level name at the top of the screen.
     *
     * @param d : the surface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(520, 15, "Level Name: " + this.levelName, 17);
    }

    /**
     * notifying the sprite that time has passed .
     */
    @Override
    public void timePassed() {

    }
}
