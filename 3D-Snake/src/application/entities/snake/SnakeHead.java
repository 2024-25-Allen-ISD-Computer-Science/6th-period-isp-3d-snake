package application.entities.snake;

import application.entities.EntityAbstract;
import application.objects.ObjectLoader;

public class SnakeHead extends EntityAbstract {

    public SnakeHead(ObjectLoader objLoader) {
        super(objLoader, "../resources/models/snake_front.obj", "src/resources/textures/snake_front.png");
    }
}
