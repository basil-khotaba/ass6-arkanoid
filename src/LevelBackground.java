import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A level background: a full-screen color with decorations drawn on top of it.
 */
public class LevelBackground implements Sprite {
    private Color color;
    private List<Sprite> decorations;

    /**
     * Constructor.
     *
     * @param color       : the background color.
     * @param decorations : the shapes drawn on top of the color.
     */
    public LevelBackground(Color color, List<Sprite> decorations) {
        this.color = color;
        this.decorations = new ArrayList<>(decorations);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        for (Sprite s : this.decorations) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
    }
}
