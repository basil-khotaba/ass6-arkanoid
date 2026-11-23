# Arkanoid – Multiple Levels

A Java version of the classic brick-breaker game, built for Assignment 6 of the Object-Oriented Programming course at Bar-Ilan University.
Bounce the balls off the paddle and break every block to clear a level.
Clear all the levels to win.

## Features

- **Three levels**, each with its own background, balls, paddle and block layout
- **3-2-1 countdown** before each level starts
- **Pause screen**: press `p` to pause and `space` to continue
- **Score** carried across all the levels:
  - every block hit adds points
  - clearing a level adds a 100-point bonus
- **End screen**:
  - *"You Win! Your score is X"* after clearing every level
  - *"Game Over. Your score is X"* after losing all the balls
- **Pick the levels to play**, and their order, from the command line

## Levels

| # | Name | Balls | Paddle | Blocks |
|---|------|-------|--------|--------|
| 1 | Direct Hit | 1 | small | a single block in the middle of a target |
| 2 | Wide Easy | 10 | very wide | one row of 15 blocks |
| 3 | Green 3 | 2 | small | a staircase of 40 blocks (10, 9, 8, 7, 6) |

## Controls

| Key | Action |
|-----|--------|
| `←` / `→` | Move the paddle |
| `p` | Pause |
| `space` | Resume from pause / exit from the end screen |

## Requirements

- Java 8 or newer (tested with Java 17)
- [Apache Ant](https://ant.apache.org/) (optional; you can also use `javac` and `java` directly)
- `biuoop-1.4.jar`, the course's GUI library, which is included in the repo

## Build and run

### With Ant (Linux / macOS)

```bash
ant compile                              # compile src/ into bin/
ant run                                  # play levels 1, 2, 3
ant -Dargs="3 1" run                     # play level 3, then level 1
```

### Without Ant (Windows)

```bash
javac -cp "biuoop-1.4.jar" -d bin src/*.java
java -cp "bin;biuoop-1.4.jar" Ass6Game          # play levels 1, 2, 3
java -cp "bin;biuoop-1.4.jar" Ass6Game 3 1      # play level 3, then level 1
```

On Linux or macOS, put `:` instead of `;` between the classpath entries.

### Choosing levels

Each argument is a level number, and the levels are played in the order given.
Anything that isn't `1`, `2` or `3` is ignored. For example:

```bash
java -cp "bin;biuoop-1.4.jar" Ass6Game 1 3 2 1 9 1 bla 3 3
# plays levels 1, 3, 2, 1, 1, 3, 3   ("9" and "bla" are ignored)
```

### Checkstyle

```bash
java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java
```

## Project structure

```
src/
├── Ass6Game.java                  # entry point: reads the arguments and starts the game
├── GameFlow.java                  # runs the levels in order, keeps the score, shows the end screen
├── GameLevel.java                 # one level: borders, blocks, paddle, balls, game loop
│
├── Animation.java                 # interface: doOneFrame() + shouldStop()
├── AnimationRunner.java           # runs any Animation at 60 frames per second
├── CountdownAnimation.java        # 3-2-1 countdown drawn over the level
├── PauseScreen.java               # "paused -- press space to continue"
├── EndScreen.java                 # "You Win!" / "Game Over" with the final score
├── KeyPressStoppableAnimation.java# decorator: stops any animation when a key is pressed
│
├── LevelInformation.java          # interface describing a level
├── FirstLevel.java                # level 1 - Direct Hit
├── SecondLevel.java               # level 2 - Wide Easy
├── ThirdLevel.java                # level 3 - Green 3
├── LevelBackground.java           # background sprite: a color plus decorations
│
├── Ball.java, Paddle.java, Block.java          # game objects
├── Sprite.java, SpriteCollection.java          # things that are drawn
├── Collidable.java, GameEnvironment.java, CollisionInfo.java   # collision detection
├── HitListener.java, HitNotifier.java          # observer pattern for block hits
├── BlockRemover.java, BallRemover.java         # remove blocks / balls when hit
├── ScoreTrackingListener.java, ScoreIndicator.java, Counter.java   # score
├── LevelNameDisplayer.java                     # shows the level name at the top
└── Point.java, Line.java, Rectangle.java, Velocity.java, Circles.java, Oval.java   # geometry and shapes
```

## Design highlights

- **Template method:** `AnimationRunner` holds the frame loop (timing, drawing, showing the frame).
  Each screen only fills in `doOneFrame()` and `shouldStop()`.
- **Decorator:** the pause and end screens run forever on their own.
  `KeyPressStoppableAnimation` wraps them and stops them when a key is pressed.
  It ignores a key that was already held down when the screen opened, so one key press can't skip two screens in a row.
- **Observer:** blocks notify `HitListener`s when they are hit.
  This is how blocks get removed, how balls are lost at the bottom of the screen, and how the score is updated.
- **Levels as data:** each level is a `LevelInformation` object.
  `GameLevel` builds any level from it, so adding a new level only needs one new class.

## Author

Basil Khotaba
