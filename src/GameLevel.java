import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * Author: Basil Khotaba.
 * A single level of the game, built from a LevelInformation.
 */
public class GameLevel implements Animation {
    /** screen width. */
    public static final int WIDTH = 800;
    /** screen height. */
    public static final int HEIGHT = 600;
    /** height of the score bar at the top of the screen. */
    public static final int HUD = 20;
    /** thickness of the border blocks. */
    public static final int BORDER = 25;

    private static final int PADDLE_HEIGHT = 12;
    private static final int PADDLE_Y = HEIGHT - 30;
    private static final int BALL_RADIUS = 5;

    private LevelInformation levelInfo;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter score;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private boolean running;

    /**
     * Constructor.
     *
     * @param levelInfo :the level to play.
     * @param keyboard  :the keyboard sensor.
     * @param runner    :the animation runner.
     * @param score     :the score counter, shared by all levels.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * adding a collidable object to the game's environment.
     *
     * @param c : the collidable that needs to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removing a collidable object from the game's environment.
     *
     * @param c : the collidable that needs to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * adding a sprite object to the sprite collection.
     *
     * @param s : the sprite object that needs to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removing a sprite object from the sprite collection.
     *
     * @param s : the sprite object that needs to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Creates the background, borders, blocks, paddle and score display of the level.
     */
    public void initialize() {
        this.addSprite(this.levelInfo.getBackground());

        // borders: top, left, right. The bottom is a "death" region that removes balls.
        new Block(new Rectangle(new Point(0, HUD), WIDTH, BORDER), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(0, HUD + BORDER), BORDER, HEIGHT), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(WIDTH - BORDER, HUD + BORDER), BORDER, HEIGHT), Color.GRAY)
                .addToGame(this);
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, BORDER), Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(new BallRemover(this, this.remainingBalls));

        // the level blocks.
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks, scoreListener);
        List<Block> blocks = this.levelInfo.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
        }
        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());

        // the paddle.
        int width = this.levelInfo.paddleWidth();
        this.paddle = new Paddle(new Rectangle(new Point((WIDTH - width) / 2.0, PADDLE_Y), width, PADDLE_HEIGHT),
                Color.YELLOW, this.keyboard, this.levelInfo.paddleSpeed(), BORDER, WIDTH);
        this.paddle.addToGame(this);

        // score and level name at the top of the screen.
        this.addSprite(new ScoreIndicator(this.score));
        this.addSprite(new LevelNameDisplayer(this.levelInfo.levelName()));
    }

    /**
     * Creates the balls on top of the paddle, runs the countdown and then plays the level until
     * there are no more balls or no more blocks.
     */
    public void run() {
        int width = this.levelInfo.paddleWidth();
        this.paddle.setUpperLeft(new Point((WIDTH - width) / 2.0, PADDLE_Y));
        for (Velocity velocity : this.levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(WIDTH / 2.0, PADDLE_Y - BALL_RADIUS - 2, BALL_RADIUS, Color.WHITE);
            ball.setVelocity(velocity);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100); // clearing a level is worth 100 points.
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * @return true if there are balls left in the level.
     */
    public boolean hasBalls() {
        return this.remainingBalls.getValue() > 0;
    }

    /**
     * @return true if there are blocks left to remove in the level.
     */
    public boolean hasBlocks() {
        return this.remainingBlocks.getValue() > 0;
    }
}
