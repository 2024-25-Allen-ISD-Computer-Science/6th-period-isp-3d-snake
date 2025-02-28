package objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import application.GameUtils;

public class ObjectLoader {
    private List<Integer> vaos = new ArrayList<>(); // vertex array object, coords of an obj
    private List<Integer> vbos = new ArrayList<>(); // vertex buffer object, texture coords of an obj

    //
    // ObjectLoader Basic Methods
    //
    public Model loadModel(float[] vertices, int[] indices) {
        int id = createVAO();
        storeVertexBuffer(0, 3, vertices);
        // storeIndicesBuffer(indices);
        unbind();
        return new Model(id, vertices.length / 3);
    }

    private void unbind() {
        GL30.glBindVertexArray(0);
    }

    public void terminate() {
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }

        for (int vbo : vbos) {
            GL30.glDeleteBuffers(vbo);
        }
    }

    //
    //
    //
    private int createVAO() {
        int id = GL30.glGenVertexArrays();
        vaos.add(id);
        GL30.glBindVertexArray(id);
        return id;
    }

    /**
     * 
     * @param attribNo
     * @param vertexCount
     * @param data
     */
    private void storeVertexBuffer(int attribNo, int vertexCount, float[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = GameUtils.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNo, vertexCount, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    //
    //
    //
    /**
     * Used to store the memory needed to render a 3D model. Basically stores the
     * points for sides.
     * 
     * 
     * @param indices
     */
    private void storeIndicesBuffer(int[] indices) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer buffer = GameUtils.storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
}
