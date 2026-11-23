import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 1 - "Direct Hit": one ball flies straight up into a single block.
 */
public class FirstLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 95;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        // a target around the block.
        List<Sprite> decorations = new ArrayList<>();
        decorations.add(new Line(260, 184, 380, 184, Color.BLUE));
        decorations.add(new Line(420, 184, 540, 184, Color.BLUE));
        decorations.add(new Line(400, 60, 400, 164, Color.BLUE));
        decorations.add(new Line(400, 204, 400, 324, Color.BLUE));
        decorations.add(new Circles(400, 184, 60, Color.BLUE, "dont fill"));
        decorations.add(new Circles(400, 184, 90, Color.BLUE, "dont fill"));
        decorations.add(new Circles(400, 184, 120, Color.BLUE, "dont fill"));
        return new LevelBackground(Color.BLACK, decorations);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // centered at x = 400, directly above the ball's starting point.
        blocks.add(new Block(new Rectangle(new Point(386, 170), 28, 28), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
