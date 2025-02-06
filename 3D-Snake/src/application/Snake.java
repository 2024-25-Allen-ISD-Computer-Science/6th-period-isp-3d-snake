package application;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Snake {
    private Vector3f position;
    private Vector3f direction;
    private float speed = 1.0f;

    public Snake() {
        position = new Vector3f(0, 0, 0);
        direction = new Vector3f(0, 0, -1);
    }

    //
    // Movement
    //
    public void keyCallback(long window, int key, int scancode, int action, int mods) {
        // move all this into input?

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

    public void turnUp() {
        if (direction.z != 0 || direction.x != 0) {
            direction.set(0, 1, 0);
        }
    }

    public void turnDown() {
        if (direction.z != 0 || direction.x != 0) {
            direction.set(0, -1, 0);
        }
    }

    public void turnLeft() {
        if (direction.x != 0) {
            direction.set(0, 0, -direction.x);
        } else if (direction.z != 0) {
            direction.set(-direction.z, 0, 0);
        }
    }

    public void turnRight() {
        if (direction.x != 0) {
            direction.set(0, 0, direction.x);
        } else if (direction.z != 0) {
            direction.set(direction.z, 0, 0);
        }
    }
}
