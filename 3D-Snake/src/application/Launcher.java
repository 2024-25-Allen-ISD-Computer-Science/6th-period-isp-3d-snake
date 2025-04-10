package application;

import org.lwjgl.glfw.GLFW;

import application.controllers.GameEngineController;
import application.controllers.WindowController;
import utils.GameUtils;

public class Launcher {
    //
    // Game Components
    //
    private static GameEngineController engine;
    private static WindowController window;
    private static SnakeGame game;

    public static void main(String[] args) throws Exception {
        window = new WindowController("3D Snake", GameUtils.height, GameUtils.width, false,
                GLFW.glfwGetPrimaryMonitor());
        game = new SnakeGame();
        engine = new GameEngineController();

        try {
            engine.initialize();
        } catch (Error e) {
            e.printStackTrace();
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
