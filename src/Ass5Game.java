// 212340442 Miriam Beinhorn

import gameManagement.Game;

/**
 * The Ass5Game class serves as the entry point for the game application.
 * It initializes and starts the game by creating an instance of the gameManagement.Game class.
 */
public class Ass5Game {
    /**
     * The main method creates an instance of the gameManagement.Game class, initializes it, and starts the game.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}