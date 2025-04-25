package application.entities.snake;

import application.entities.EntityAbstract;
import application.objects.ObjectLoader;

public class SnakeBody extends EntityAbstract {
    public SnakeBody(ObjectLoader objLoader) {
        super(objLoader, "../resources/models/snake_body.obj", "./src/resources/textures/snake_body.png");
    }
}
