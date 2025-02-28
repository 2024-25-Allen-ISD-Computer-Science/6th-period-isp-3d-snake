package application;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import objects.Model;
import objects.ObjectLoader;

public class SnakeGame implements ILogic {
    //
    // Controllers
    //
    private final RenderController RENDERER;
    private final WindowController WINDOW;
    private final ObjectLoader OBJECT_LOADER;

    private Model model;

    //
    // Variables
    //
    float color = 0;
    int inputTick = 0;

    // private GLFWKeyCallback keyCallback;

    public SnakeGame() {
        window = Launcher.getWindow();
        renderer = new RenderController();
        Snake snake = new Snake();

        loop();

        GLFW.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    @Override
    public void initialize() throws Exception {
        RENDERER.initialize();

        // For testing purposes only
        float[] vertices = {
                0f, 0.5f, 0f,
                0f, 1f, 0f,
                0.5f, 1f, 0f,

                // 0.9f, -0.9f, 0f,
                // 0.5f, 0.9f, 0f,
                // -0.5f, 0.5f, 0f
        };
        int[] indices = {
                0, 0, 0,
                0, 0, 0,
                1, 1, 1
        };

        model = OBJECT_LOADER.loadModel(vertices, indices);
    }

    /**
     * The method mapping a key to an action or method. The if statements should not
     * be chained together, as then only one input would be registered per run.
     */
    @Override
    public void input() {
        // Render testing purposes only
        if (inputTick > 20000) {
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_UP)) {
                color += .0005;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
                color -= .0005;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_0)) {
                color = 0;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_1)) {
                color = 1;
            }
            inputTick = 0;
        } else {
            inputTick++;
        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'input'");
        int w_state = glfwGetKey(window, GLFW.GLFW_KEY_W);
        int a_state = glfwGetKey(window, GLFW.GLFW_KEY_A);
        int s_state = glfwGetKey(window, GLFW.GLFW_KEY_S);
        int d_state = glfwGetKey(window, GLFW.GLFW_KEY_D);


        if (w_state == GLFW.GLFW_PRESS) {
            snake.turnUp();
        } else if (a_state == GLFW.GLFW_PRESS) {
            snake.turnLeft();
        } else if (s_state == GLFW.GLFW_PRESS) {
            snake.turnDown();
        } else if (d_state == GLFW.GLFW_PRESS) {
            snake.turnRight();
        } else {
            System.out.println("Miscellaneous input.");
        }
    }

    /**
     * This method runs periodically.
     */
        

    @Override
    public void update() {
        if (color > 1) {
            color = 1;
        } else if (color < 0) {
            color = 0;
        }
    }

    @Override
    public void render() {
        if (WINDOW.getResize()) {
            GL11.glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            WINDOW.setResize(true);
        }

        WINDOW.setClearColor(color, color, color, 0f);
        RENDERER.render(model);
        // RENDERER.clear(); // makes the renderer not render models
    }

    @Override
    public void terminate() {
        WINDOW.terminate();
        RENDERER.terminate();
        OBJECT_LOADER.terminate();

        // keyCallback.free(); // must be object of GLFWKeyCallback then do obj.free()
        GLFW.glfwDestroyWindow(WINDOW.getWindowID());

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

}
