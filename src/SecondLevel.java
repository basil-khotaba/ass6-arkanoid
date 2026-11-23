import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 2 - "Wide Easy": ten balls, a very wide paddle and a single row of blocks.
 */
public class SecondLevel implements LevelInformation {
    private static final int BALLS = 10;

    @Override
    public int numberOfBalls() {
        return BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        // a fan of angles: -50, -40, ..., -10, 10, ..., 50 (none straight up).
        for (int i = 1; i <= BALLS / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-10 * i, 6));
            velocities.add(Velocity.fromAngleAndSpeed(10 * i, 6));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Color eggColor = new Color(0xE1F6D5);
        int[][] eggs = {{120, 60}, {300, 40}, {500, 75}, {650, 230}, {400, 225},
                {130, 240}, {80, 420}, {340, 430}, {560, 410}};
        List<Sprite> decorations = new ArrayList<>();
        for (int[] egg : eggs) {
            decorations.add(new Oval(egg[0], egg[1], 110, 160, eggColor));
        }
        return new LevelBackground(new Color(0x3A9BF6), decorations);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        int blockWidth = 50, blockHeight = 25;
        for (int i = 0; i < colors.length; i++) {
            blocks.add(new Block(new Rectangle(new Point(GameLevel.BORDER + i * blockWidth, 250),
                    blockWidth, blockHeight), colors[i]));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
