package application;

import org.lwjgl.glfw.GLFW;

public class Snake {
    public Snake() {

    }

    //
    // Movement
    //

    float xPos, yPos, zPos;
    float speed = 1.0f;

    public void keyCallback(long window, int key, int scancode, int action, int mods) {

        if (key == GLFW.GLFW_KEY_W && action == GLFW.GLFW_PRESS) {
            turnUp();
        } else if (key == GLFW.GLFW_KEY_A && action == GLFW.GLFW_PRESS) {
            turnLeft();
        } else if (key == GLFW.GLFW_KEY_S && action == GLFW.GLFW_PRESS) {
            turnDown();
        } else if (key == GLFW.GLFW_KEY_D && action == GLFW.GLFW_PRESS) {
            turnRight();
        } else {
            return 0;
        }

    }

    public void 
}
