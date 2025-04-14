package application;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import application.controllers.RenderController;
import application.controllers.WindowController;
import application.entities.Snake;
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
    private ArrayList<Entity> entities;
    private Entity teapot;
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

        entities = new ArrayList<Entity>();
    }

    private void createEntities() {
        /* Teapot */
        Model teapotModel = OBJECT_LOADER.loadOBJModel("../resources/models/teapot.obj");
        Texture teapotTexture;
        try {
            teapotTexture = new Texture(OBJECT_LOADER.loadTexture("./src/resources/textures/brick.png"));
            teapotModel.setTexture(teapotTexture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        teapot = new Entity(teapotModel, new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 0.2f);

        entities.add(teapot);
    }

    @Override
    public void initialize() throws Exception {
        RENDERER.initialize();
        createEntities();

        /* Initialize Entities */
        for (Entity i : entities) {

        }

        // For testing purposes only
        // float[] vertices = {
        // // Coordinates guaranteed to work
        // -0.5f, 0.5f, 0f,
        // -0.5f, -0.5f, 0f,
        // 0.5f, -0.5f, 0f,
        // 0.5f, -0.5f, 0f,
        // 0.5f, 0.5f, 0f,
        // -0.5f, 0.5f, 0f
        // };
        // // int[] indices = {
        // // 0, 0, 0,
        // // 0, 0, 0,
        // // 1, 1, 1
        // // };
        // int[] indices = {
        // 0, 1, 3,
        // 3, 1, 2
        // };

        // float[] textureCoords = {
        // 0, 0,
        // 0, 1,
        // 1, 1,
        // 1, 0
        // };

        // model = OBJECT_LOADER.loadModel(vertices, textureCoords, indices);
        // model.setTexture(new
        // Texture(OBJECT_LOADER.loadTexture("textures/grassblock.png")));
        // entity = new Entity(model, new Vector3f(1, 0, 0), new Vector3f(0, 0, 0), 1);
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
                teapot.getPos().x -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
                teapot.getPos().x += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_UP)) {
                teapot.getPos().y += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
                teapot.getPos().y -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_Q)) {
                teapot.getPos().z += 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_E)) {
                teapot.getPos().z -= 0.1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_A)) {
                teapot.getRotation().y += 1f;
            }
            if (WINDOW.isKeyPressed(GLFW.GLFW_KEY_D)) {
                teapot.getRotation().y -= 1f;
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
        for (Entity i : entities) {
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
