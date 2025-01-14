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

    public void key_callback(long window, int key, int scancode, int action, int intmods) { // maybe its an int?

        int wState = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W);
        int aState = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A);
        int sState = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S);
        int dState = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D);

        if (key == GLFW.GLFW_KEY_W && wState == GLFW.GLFW_PRESS) {
            turnUp();
        } else if (key == GLFW.GLFW_KEY_A && aState == GLFW.GLFW_PRESS) {
            turnLeft();
        } else if (key == GLFW.GLFW_KEY_S && sState == GLFW.GLFW_PRESS) {
            turnDown();
        } else if (key == GLFW.GLFW_KEY_D && dState == GLFW.GLFW_PRESS) {
            turnRight();
        }

    }
}
