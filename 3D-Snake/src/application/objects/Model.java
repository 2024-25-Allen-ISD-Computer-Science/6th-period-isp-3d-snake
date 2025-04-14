package application.objects;

public class Model {
    private int id;
    private int vertexCount;
    private Texture texture;

    public Model(int id, int vertexCount) {
        this.id = id;
        this.vertexCount = vertexCount;
    }

    public Model(int id, int vertexCount, Texture texture) {
        this.id = id;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }

    public Model(Model model, Texture texture) {
        this.id = model.getID();
        this.vertexCount = model.getVertexCount();
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
