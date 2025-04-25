package application.entities;

import org.joml.Vector3f;

import application.objects.Model;
import application.objects.ObjectLoader;
import application.objects.Texture;

/**
 * General use class to extend to all entities.
 */
public class EntityAbstract {
    /* Components */
    private Model model;
    private String modelPath;
    private String texturePath;

    /* Transformation */
    private Vector3f pos, rotation;
    private float scale;

    public EntityAbstract(Model model, Vector3f pos, Vector3f rotation, float scale) {
        this.model = model;
        this.pos = pos;
        this.rotation = rotation;
        this.scale = scale;
    }

    public EntityAbstract(ObjectLoader objLoader, String modelPath, String texturePath) {
        /* Components */
        this.modelPath = modelPath;
        this.texturePath = texturePath;
        initModel(objLoader);

        /* Transformation */
        this.pos = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1;
    }

    //
    // Position Methods
    //
    public final Vector3f getPos() {
        return pos;
    }

    public final void incPos(float x, float y, float z) {
        this.pos.x += x;
        this.pos.y += y;
        this.pos.z += z;
    }

    public final void setPos(float x, float y, float z) {
        this.pos.x = x;
        this.pos.y = y;
        this.pos.z = z;
    }

    public final Vector3f getRotation() {
        return rotation;
    }

    public final void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public final void incRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public final float getScale() {
        return scale;
    }

    public final void setScale(float scale) {
        this.scale = scale;
    }

    //
    // Component Methods
    //
    public final Model getModel() {
        return model;
    }

    public final void setModel(Model model) {
        this.model = model;
    }

    private final void initModel(ObjectLoader objLoader) {
        Model tempModel = objLoader.loadOBJModel(modelPath);
        Texture teapotTexture;
        try {
            teapotTexture = new Texture(objLoader.loadTexture(texturePath));
            tempModel.setTexture(teapotTexture);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.model = tempModel;
    }
}