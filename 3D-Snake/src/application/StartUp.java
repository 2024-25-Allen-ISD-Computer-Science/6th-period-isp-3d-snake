public class SnakeGame {
    // The window handle
    private long window;
    private Snake snake;
    
    public void run() {
        System.out.println("Starting 3D Snake Game!");
        init();
        loop();
        // Clean up and free resources
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
