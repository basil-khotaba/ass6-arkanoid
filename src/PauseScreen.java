import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Author: Basil Khotaba.
 * The pause screen. It runs forever - wrap it in a KeyPressStoppableAnimation to stop it.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param k : the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // a red "play" sign above the text.
        d.setColor(Color.RED);
        d.fillRectangle(250, 110, 300, 140);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 70; i++) { // fill the triangle line by line.
            d.drawLine(370 + i, 140 + i * 40 / 70, 370 + i, 220 - i * 40 / 70);
        }
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
