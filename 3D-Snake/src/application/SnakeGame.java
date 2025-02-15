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
        Snake snake = new Snake();

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
