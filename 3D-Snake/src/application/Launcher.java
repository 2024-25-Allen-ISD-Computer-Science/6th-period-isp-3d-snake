package application;

import org.lwjgl.glfw.GLFW;

public class Launcher {
    //
    // Game Components
    //
    private static GameEngineController engine;
    private static WindowController window;
    private static SnakeGame game;

    public static void main(String[] args) throws Exception {
        window = new WindowController("3D Snake", AppUtils.height, AppUtils.height, false,
                GLFW.glfwGetPrimaryMonitor());
        game = new SnakeGame();
        engine = new GameEngineController();

        try {
            engine.initialize();
        } catch (Error e) {
            System.err.println(
                    "'The snake who doesn't show up is the snake that's a bozo.' ~ Zuan Tsu, The Art of Slithering\nThe game couldn't initialize.");
        }
    }

    //
    // Get & Set Methods
    //

    public static WindowController getWindow() {
        return window;
    }

    public static GameEngineController getEngine() {
        return engine;
    }

    public static SnakeGame getGame() {
        return game;
    }
}
