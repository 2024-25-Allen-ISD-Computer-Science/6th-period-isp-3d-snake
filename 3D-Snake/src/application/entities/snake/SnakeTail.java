package application.entities.snake;

import application.entities.EntityAbstract;
import application.objects.ObjectLoader;

public class SnakeTail extends EntityAbstract {
    public SnakeTail(ObjectLoader objLoader) {
        super(objLoader, "../resources/models/snake_tail.obj", "./src/resources/textures/snake_tail.png");
    }
}
