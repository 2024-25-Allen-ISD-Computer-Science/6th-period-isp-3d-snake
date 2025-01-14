package application;

public class Snake {
    public Snake() {

    }

    //
    // Movement
    //

    float xPos, yPos, zPos;
    float speed = 1.0f;

    void key_callback(GLFWindow* window, int key, int scancode, int action, intmods);

    int wState = glfwGetKey(window, GLFW_KEY_W);
    int aState = glfwGetKey(window, GLFW_KEY_A);
    int sState = glfwGetKey(window, GLFW_KEY_S);
    int dState = glfwGetKey(window, GLFW_KEY_D);

    if (key == GLFW_KEY_W && wState = GLFW_PRESS) {
        turnUp();
    }
    else if (key == GLFW_KEY_A && aState = GLFW_PRESS) {
        turnLeft();
    }
    else if (key == GLFW_KEY_S && sState = GLFW_PRESS) {
        turnDown();
    }
    else if (key == GLFW_KEY_D && dState = GLFW_PRESS) {
        turnRight();
    }

}
