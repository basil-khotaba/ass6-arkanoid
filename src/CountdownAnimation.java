import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Author: Basil Khotaba.
 * Shows the game screen with a countdown (e.g. 3, 2, 1) on top of it, lasting numOfSeconds in total.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long startTime;

    /**
     * Constructor.
     *
     * @param numOfSeconds : how long the whole countdown lasts.
     * @param countFrom    : the number to count down from.
     * @param gameScreen   : the sprites of the game, drawn behind the countdown.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = -1;
    }

    /**
     * @return milliseconds since the first frame.
     */
    private long elapsed() {
        return this.startTime < 0 ? 0 : System.currentTimeMillis() - this.startTime;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.startTime < 0) {
            this.startTime = System.currentTimeMillis();
        }
        this.gameScreen.drawAllOn(d);
        double millisPerNumber = this.numOfSeconds * 1000 / this.countFrom;
        int current = this.countFrom - (int) (elapsed() / millisPerNumber);
        if (current > 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2 - 15, d.getHeight() / 2 + 20, Integer.toString(current), 65);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.startTime >= 0 && elapsed() >= this.numOfSeconds * 1000;
    }
}
