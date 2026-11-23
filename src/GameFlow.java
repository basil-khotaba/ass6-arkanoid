import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Author: Basil Khotaba.
 * Runs a list of levels one after the other and shows the end screen.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;

    /**
     * Constructor.
     *
     * @param animationRunner :the animation runner.
     * @param keyboardSensor  :the keyboard sensor.
     * @param gui             :the window, closed when the game ends.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * Runs the levels in order. Losing all the balls ends the game; clearing the last level wins it.
     *
     * @param levels : the levels to play.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean won = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            while (level.hasBalls() && level.hasBlocks()) {
                level.run();
            }
            if (level.hasBlocks()) { // the level ended because no balls were left.
                won = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.score, won)));
        this.gui.close();
    }
}
