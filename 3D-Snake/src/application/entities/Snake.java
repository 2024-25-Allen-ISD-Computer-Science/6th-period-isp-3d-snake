package application.entities;

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

    // public void keyCallback(long window, int key, int scancode, int action, int
    // mods) {

    // }

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

    public void resetGame() {
        snake = new LinkedList<>();
        snake.add(new Vector3i(worldSize / 2, worldSize / 2, worldSize / 2));
        direction = new Vector3i(1, 0, 0); // Moving along X-axis initially
        spawnFood();
        gameOver = false;
    }

    public void update() {
        if (gameOver)
            return;

        Vector3i newHead = new Vector3i(snake.getFirst()).add(direction);

        // Check wall collision
        if (newHead.x < 0 || newHead.x >= worldSize ||
                newHead.y < 0 || newHead.y >= worldSize ||
                newHead.z < 0 || newHead.z >= worldSize) {
            gameOver = true;
            return;
        }

        // Check self-collision
        if (snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        // Move snake
        snake.addFirst(newHead);

        // Check food collision
        if (newHead.equals(food)) {
            spawnFood();
        } else {
            snake.removeLast(); // Only grow when eating
        }
    }

    private void spawnFood() {
        do {
            food = new Vector3i(random.nextInt(worldSize),
                    random.nextInt(worldSize),
                    random.nextInt(worldSize));
        } while (snake.contains(food));
    }

    public List<Vector3i> getSnake() {
        return Collections.unmodifiableList(snake);
    }

    public Vector3i getFood() {
        return new Vector3i(food);
    }

    public Vector3i getDirection() {
        return new Vector3i(direction);
    }

}
