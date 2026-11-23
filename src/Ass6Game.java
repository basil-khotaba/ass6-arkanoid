import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Basil Khotaba.
 */
public class Ass6Game {

    /**
     * Creates a level from its number.
     *
     * @param number : the level number (1 to 3).
     * @return the level, or null if there is no level with this number.
     */
    private static LevelInformation levelByNumber(int number) {
        switch (number) {
            case 1:
                return new FirstLevel();
            case 2:
                return new SecondLevel();
            case 3:
                return new ThirdLevel();
            default:
                return null;
        }
    }

    /**
     * Runs the game.
     *
     * @param args : level numbers to play, in order. Non-numbers and numbers out of range are ignored.
     *             With no arguments the levels are played in order 1, 2, 3.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            for (int i = 1; i <= 3; i++) {
                levels.add(levelByNumber(i));
            }
        } else {
            for (String arg : args) {
                try {
                    LevelInformation level = levelByNumber(Integer.parseInt(arg));
                    if (level != null) {
                        levels.add(level);
                    }
                } catch (NumberFormatException e) {
                    continue; // not a number - ignore it.
                }
            }
        }

        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow game = new GameFlow(runner, gui.getKeyboardSensor(), gui);
        game.runLevels(levels);
    }
}
