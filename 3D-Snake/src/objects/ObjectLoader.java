package objects;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import application.GameUtils;

public class ObjectLoader {
    private List<Integer> vaos = new ArrayList<>(); // vertex array object, coords of an obj
    private List<Integer> vbos = new ArrayList<>(); // vertex buffer object, texture coords of an obj

    //
    // ObjectLoader Basic Methods
    //

    public Model loadOBJModel(String fileName) {
        List<String> lines = GameUtils.readAllLines(fileName);

        List<Vector3f> vertices = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3i> faces = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "v":
                    Vector3f verticesVec = new Vector3f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3]));
                    vertices.add(verticesVec);
                    break;
                case "vt":
                    Vector2f textureVec = new Vector2f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]));

                    textures.add(textureVec);
                    break;
                case "vn":
                    Vector3f normalsVec = new Vector3f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3]));
                    vertices.add(normalsVec);
                    break;
                case "f":
                    processFace(tokens[1], faces);
                    processFace(tokens[2], faces);
                    processFace(tokens[3], faces);
                    break;
                default:
                    break;
            }
        }

        // nw - 8:03 / 12:27
        List<Integer> indices = new ArrayList<>();
        float[] verticesArr = new float[vertices.size() + 3];
        int i = 0;
        for (Vector3f pos : vertices) {
            verticesArr[i + 3] = pos.x;
            verticesArr[i + 3 + 1] = pos.y;
            verticesArr[i + 3 + 2] = pos.z;
            i++;
        }

        float[] textCoordArr = new float[vertices.size() + 2];
        float[] normalArr = new float[vertices.size() + 3];
<<<<<<< HEAD

        for (Vector3i face : faces) {
            processVertex(face.x, face.y, face.z, textures, normals, indices, textCoordArr, normalArr);
        }

        int [] indicesArr = indices.stream().mapToInt((Integer v) -> v).toArray();

        return loadModel(verticesArr, textCoordArr, indicesArr);
    }

    private static void processVertex(int pos, int textCoord, int normal, List<Vector2f> texCoordList, List<Vector3f> normalList, List<Integer> indicesList, float[] texCoordArr, float[] normalArr) {
        indicesList.add(pos);

        if (texCoord >= 0) {
            Vector2f texCoordVec = texCoordList.get(textCoord);
            texCoordArr[pos * 2] = texCoordVec.x;
            texCoordArr[pos * 2 + 1] = 1 - texCoordVec.y;
        }

        if (normal >= 0) {
            Vector3f normalVec = normalList.get(normal);
            normalArr[pos * 3] = normalVec.x;
            normalArr[pos * 3 + 1] = normalVec.y;
            normalArr[pos * 3 + 2] = normalVec.z;
        }
=======
>>>>>>> 09251d18c14d6c0a6c6a13cf077cd786bef26085
    }

    private static void processFace(String token, List<Vector3i> faces) {
        String[] lineToken = token.split("/");
        int length = lineToken.length;
        int pos = -1, coords = -1, normal = -1;
        pos = Integer.parseInt(lineToken[0]) - 1;

        if (length > 1) {
            String textCoord = lineToken[1];
            coords = textCoord.length() > 0 ? Integer.parseInt(textCoord) - 1 : -1;
            if (length > 2) {
                normal = Integer.parseInt(lineToken[2]) - 1;
            }
        }

        Vector3i facesVec = new Vector3i(pos, coords, normal);
        faces.add(facesVec);
    }

    public Model loadModel(float[] vertices, float[] textureCoords, int[] indices) {
        int id = createVAO();
        storeVertexBuffer(0, 3, vertices);
        storeVertexBuffer(1, 2, textureCoords);
        storeIndicesBuffer(indices);
        unbind();
        return new Model(id, indices.length)
    }

    public int loadTexture(String fileName) throws Exception {
        int width, height;
        ByteBuffer buffer;

        MemoryStack stack = MemoryStack.stackPush();

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
    // GPU methods
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
