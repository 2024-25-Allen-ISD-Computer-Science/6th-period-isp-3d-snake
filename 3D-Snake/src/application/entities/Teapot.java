package application.entities;

import application.objects.ObjectLoader;

public class Teapot extends EntityAbstract {
    public Teapot(ObjectLoader objLoader) {
        super(objLoader, "../resources/models/teapot.obj", "./src/resources/textures/brick.png");
    }
}
