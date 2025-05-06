package application;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import application.controllers.RenderController;
import application.controllers.WindowController;
import application.entities.EntityAbstract;
import application.entities.Snake;
import application.entities.Teapot;
import application.objects.Model;
import application.objects.ObjectLoader;
import application.objects.Texture;

public class SnakeGame implements ILogic {
    //
    // Controllers
    //
    private final RenderController RENDERER;
    private final WindowController WINDOW;
    private final ObjectLoader OBJECT_LOADER;
    private final Snake snake;

    /* Entities and Models */
    private ArrayList<EntityAbstract> entities;
    private EntityAbstract apple;
    // private Model model;
    // private Entity entity;

    //
    // Variables
    //
    float color = 0;
    private int inputTick = 0;

    // private GLFWKeyCallback keyCallback;

    public SnakeGame() {
        WINDOW = Launcher.getWindow();
        RENDERER = new RenderController();
        OBJECT_LOADER = new ObjectLoader();
        snake = new Snake();

        entities = new ArrayList<EntityAbstract>();
    }

    private final void createEntities() {
        /* Teapot */
        // teapot = new Teapot(OBJECT_LOADER);
        // teapot.setScale(0.2f);
        // entities.add(teapot);

        /* Apple */
        apple = new EntityAbstract(OBJECT_LOADER, Constants.FruitConstants.APPLE_MODEL,
                Constants.FruitConstants.APPLE_TEXTURE);
        apple.setScale(0.2f);
        entities.add(apple);
    }

    @Override
    public void initialize() throws Exception {
        RENDERER.initialize();
        createEntities();
    }

    /**
     * The method mapping a key to an action or method. The if statements should not
     * be chained together, as then only one input would be registered per run.
     */
    @Override
    public void input() {
        // Render testing purposes only
        if (inputTick > Constants.InputConstants.INPUT_TICKS) {
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_LEFT)) {
                apple.getPos().x -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
                apple.getPos().x += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_UP)) {
                apple.getPos().y += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
                apple.getPos().y -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_Q)) {
                apple.getPos().z += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_E)) {
                apple.getPos().z -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_A)) {
                apple.getRotation().y += 1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_D)) {
                apple.getRotation().y -= 1f;
            }
            inputTick = 0;
        } else {
            inputTick++;
        }

        // switch the below to boolean, and use the WINDOW.isKeyPressed() method
        // int w_state = glfwGetKey(window, GLFW.GLFW_KEY_W);
        // int a_state = glfwGetKey(window, GLFW.GLFW_KEY_A);
        // int s_state = glfwGetKey(window, GLFW.GLFW_KEY_S);
        // int d_state = glfwGetKey(window, GLFW.GLFW_KEY_D);
        int w_state = 0;
        int a_state = 0;
        int s_state = 0;
        int d_state = 0;

        // update conditionals and add a snake variable
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
        // if (entity.getPos().x < -1.5f) {
        // entity.setPos(1.5f, 0, 0);
        // }
    }

    @Override
    public void render() {
        if (WINDOW.getResize()) {
            GL11.glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            WINDOW.setResize(false);
        }

        WINDOW.setClearColor(color, color, color, 0f);

        /* Rendering entities */
        for (EntityAbstract i : entities) {
            RENDERER.render(i);
        }
        // RENDERER.render(entity);
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
