import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Author: Basil Khotaba.
 * The end screen ("Game Over" or "You Win"). It runs forever - wrap it in a KeyPressStoppableAnimation.
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean won;

    /**
     * Constructor.
     *
     * @param score : the final score.
     * @param won   : true if the player cleared all the levels.
     */
    public EndScreen(Counter score, boolean won) {
        this.score = score;
        this.won = won;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String message;
        if (this.won) {
            message = "You Win! Your score is " + this.score.getValue();
        } else {
            message = "Game Over. Your score is " + this.score.getValue();
        }
        d.setColor(Color.BLACK);
        d.drawText(150, d.getHeight() / 2, message, 32);
        d.setColor(Color.RED);
        d.drawText(250, d.getHeight() / 2 + 80, "press space to exit", 24);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
