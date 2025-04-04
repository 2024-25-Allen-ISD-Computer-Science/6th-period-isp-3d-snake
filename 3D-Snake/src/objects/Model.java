package objects;

import entity.Texture;

public class Model {
    private int id;
    private int vertexCount;
    private Texture texture;

    public Model(int id, int vertexCount, Texture texture) {
        this.id = id;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }

    //
    // Get & Set Methods
    //
    public int getID() {
        return id;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
