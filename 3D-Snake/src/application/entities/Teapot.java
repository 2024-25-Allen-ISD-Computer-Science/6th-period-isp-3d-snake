package application.entities;

import org.joml.Vector3f;

import application.Entity;
import application.objects.Model;

public class Teapot extends Entity {
    public Teapot(Model model, Vector3f pos, Vector3f rotation, float scale) {
        super(model, pos, rotation, scale);
    }

}
