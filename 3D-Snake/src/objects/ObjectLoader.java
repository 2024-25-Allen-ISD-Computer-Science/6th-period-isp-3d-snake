package objects;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class ObjectLoader {

    private List<Integer> vaos = new ArrayList<>(); // vertex array object, coords of an obj
    private List<Integer> vbos = new ArrayList<>(); // vertex buffer object, texture coords of an obj

    public Model loadModel(float[] vertices) {

    }

    private int createVAO() {

    }

    private void storeData(int attribNo, int vertexCount, float[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = ObjectConstants.storeData(data);
    }

    private void unbind() {
        GL30.glBindVertexArray(0);
    }

    private void terminate() {
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }

        for (int vbo : vbos) {
            GL30.glDeleteBuffers(vbo);
        }
    }

}
