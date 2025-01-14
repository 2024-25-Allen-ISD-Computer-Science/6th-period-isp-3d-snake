import org.lwjgl.glfw.GLFW;

import application.*;

public class App {
    public static void main(String[] args) throws Exception {
        WindowController window = new WindowController("3D Snake", 200, 200, false, GLFW.glfwGetPrimaryMonitor());
        window.initialize();

        while (!window.isTerminated()) {
            window.execute();
        }
    }
}
