
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the ScoreIndicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private Counter counter;

    /**
     * the constructor.
     *
     * @param counter : counter of score.
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }

    /**
     * displaying the score on the screen.
     * @param d : the surface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), GameLevel.HUD);
        d.setColor(Color.white);
        d.drawText(360, 15, "Score:" + this.counter.getValue(), 17);
    }
    /**
     * notifying the sprite that time has passed .
     */
    @Override
    public void timePassed() {

    }
}
