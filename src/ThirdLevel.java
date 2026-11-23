import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 3 - "Green 3": two balls, a staircase of blocks above a police car on a road.
 */
public class ThirdLevel implements LevelInformation {
    private static final int BALLS = 2;

    @Override
    public int numberOfBalls() {
        return BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-30, 7));
        velocities.add(Velocity.fromAngleAndSpeed(30, 7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> d = new ArrayList<>();
        Color white = new Color(0xFFFFF9);
        Color black = new Color(0x010101);
        // siren lights
        d.add(new Block(new Rectangle(new Point(340, 310), 100, 50), new Color(0x0025F6)));
        d.add(new Block(new Rectangle(new Point(340, 310), 50, 50), new Color(0xF64831)));
        // car body
        d.add(new Block(new Rectangle(new Point(200, 360), 250, 120), white));
        d.add(new Block(new Rectangle(new Point(450, 420), 50, 60), white));
        d.add(new Block(new Rectangle(new Point(170, 410), 100, 70), white));
        d.add(new Block(new Rectangle(new Point(500, 430), 10, 15), black));
        d.add(new Block(new Rectangle(new Point(500, 455), 10, 15), black));
        // windows
        d.add(new Block(new Rectangle(new Point(220, 375), 90, 40), new Color(0xFCFCFC)));
        d.add(new Block(new Rectangle(new Point(340, 375), 90, 40), new Color(0xFCFCFC)));
        // road and lane marks (drawn before the wheels so the wheels sit on the road)
        d.add(new Block(new Rectangle(new Point(0, 495), 800, 105), Color.GRAY));
        for (int x = 40; x < 800; x += 130) {
            d.add(new Block(new Rectangle(new Point(x, 540), 100, 12), Color.WHITE));
        }
        // wheels
        d.add(new Circles(250, 485, 28, new Color(0x6B6B6B), "Fill"));
        d.add(new Circles(400, 485, 28, new Color(0x6B6B6B), "Fill"));
        d.add(new Circles(250, 485, 18, new Color(0xA1A1A1), "Fill"));
        d.add(new Circles(400, 485, 18, new Color(0xA1A1A1), "Fill"));
        return new LevelBackground(new Color(0x4B55F6), d);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] rowColors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int blockWidth = 50, blockHeight = 25, startY = 150;
        // 5 rows of 10, 9, 8, 7, 6 blocks aligned to the right wall.
        for (int row = 0; row < 5; row++) {
            for (int j = 0; j < 10 - row; j++) {
                Point upperLeft = new Point(GameLevel.WIDTH - GameLevel.BORDER - blockWidth * (j + 1),
                        startY + blockHeight * row);
                blocks.add(new Block(new Rectangle(upperLeft, blockWidth, blockHeight), rowColors[row]));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
