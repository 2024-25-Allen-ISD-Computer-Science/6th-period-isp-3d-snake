package application;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import objects.ObjectLoader;

public class SnakeGame implements ILogic {
    //
    // Controllers
    //
    private RenderController renderer;
    private WindowController window;
    private ObjectLoader object;

    private GLFWKeyCallback keyCallback;

    public SnakeGame() {
        window = Launcher.getWindow();
        renderer = Launcher.getRenderer();
        GLFWKeyCallback keyCallback = GLFW.glfwSetKeyCallback(window, Snake.keyCallback()); // fix later
    }

    @Override
    public void initialize() throws Exception {
        renderer.initialize();
    }

    @Override
    public void input() {

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void render() {
        // if (window.getResize()) {
        // GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
        // window.setResize(true);
        // }

        window.setClearColor(0, 0, 0, 0);
        renderer.clear(); // might need to change -> renderer.render()
    }

    @Override
    public void terminate() {
        window.terminate();

        keyCallback.free(); // must be object of GLFWKeyCallback then do obj.free()
        GLFW.glfwDestroyWindow(window.getWindowID());

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

}
