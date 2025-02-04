package application;

import org.lwjgl.opengl.GL11;

import objects.ObjectLoader;

public class SnakeGame implements ILogic {
    //
    // Controllers
    //
    private RenderController renderer;
    private WindowController window;
    private ObjectLoader object;

    public SnakeGame() {
        window = Launcher.getWindow();
        renderer = new RenderController();

        loop();

        GLFW.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    @Override
    public void initialize() throws Exception {
        renderer.initialize();
    }

    @Override
    public void input() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'input'");
        GLFW.glfwSetKeyCallback(window, Snake.keyCallback);
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
        renderer.clear();
    }

    @Override
    public void terminate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

}
