//212340442 Miriam Beinhorn
package gameManagement;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collections.GameEnvironment;
import collections.SpriteCollection;
import collidables.Block;
import collidables.Paddle;
import generalHelpers.Counter;
import generalHelpers.Methods;
import geometry.Ball;
import geometry.Point;
import interfaces.Collidable;
import interfaces.Sprite;
import movement.Velocity;
import score.ScoreIndicator;
import score.ScoreTrackingListener;

import java.awt.Color;

/**
 * The gameManagement.Game class manages the game environment, including the sprites and collidables.
 * It initializes the game, runs the game loop, and handles the animation and updates.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    /**
     * Constructs a gameManagement.Game object and initializes the sprite collection and game environment,
     * and sets all Counters to 0.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game.
     *
     * @param c The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes a new game by creating the blocks, balls, and paddle, and adding them to the game.
     */
    public void initialize() {
        this.remainingBlocks.increase(12 + 11 + 10 + 9 + 8 + 7); //initial amount of blocks.
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.remainingBalls.increase(3); //the game has 3 balls
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        this.gui = new GUI("Arkanoid", 800, 600);
        //add the balls:
        Ball ball = new Ball(new Point(400, 500), 5, Color.black);
        ball.setVelocity(Velocity.fromAngleAndSpeed(240, 5));
        ball.setGame(this.environment);
        ball.addToGame(this);
        ball = new Ball(new Point(400, 500), 5, Color.black);
        ball.setVelocity(Velocity.fromAngleAndSpeed(260, 5));
        ball.setGame(this.environment);
        ball.addToGame(this);
        ball = new Ball(new Point(400, 500), 5, Color.black);
        ball.setVelocity(Velocity.fromAngleAndSpeed(270, 5));
        ball.setGame(this.environment);
        ball.addToGame(this);
        //add the keyboard:
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard);
        paddle.addToGame(this);
        //add the "frame" blocks:
        Block wall = new Block(new Point(0, 20), 20, 600, Color.white);
        wall.addToGame(this);
        wall = new Block(new Point(0, 20), 800, 20, Color.white);
        wall.addToGame(this);
        wall = new Block(new Point(780, 20), 20, 600, Color.white);
        wall.addToGame(this);
        wall = new Block(new Point(0, 0), 800, 20, Color.lightGray);
        wall.addToGame(this); //the block to show the score on
        wall = new Block(new Point(0, 610), 800, 20, Color.white);
        wall.addHitListener(ballRemover); //when hit the bottom rectangle, a ball needs to be removed
        wall.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        //add the blocks:
        int k = 12; //amount of blocks in the longest line
        for (int i = 0; i < 6; i++) { //6 rows in total
            for (int j = 0; j < k; j++) {
                Block b = new Block(new Point(730 - 50 * j, 100 + 20 * i), 50, 20, Methods.COLORS[i]);
                b.addHitListener(blockRemover); //all of those blocks may be removed
                b.addHitListener(scoreListener); //when hit one of those blocks, the score is changed
                b.addToGame(this);
            }
            k--; //every row has 1 block less than the previous row
        }
    }

    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (this.remainingBlocks.getValue() == 0) { //when all blocks are gone
                this.score.increase(100);
                System.out.println("You won! your score is " + this.score.getValue());
                gui.close();
            }
            if (this.remainingBalls.getValue() == 0) { //when you lost all balls
                System.out.println("LOSER!");
                System.out.println("The score you got is: " + this.score.getValue());
                gui.close();
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}