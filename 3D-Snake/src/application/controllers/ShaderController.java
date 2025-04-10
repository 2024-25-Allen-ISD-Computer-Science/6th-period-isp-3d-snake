package application.controllers;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

public class ShaderController {

    private final int PROGRAM_ID;
    private int vertexShaderID, fragmentShaderID;

    private final Map<String, Integer> uniforms;

    public ShaderController() throws Exception {
        PROGRAM_ID = GL20.glCreateProgram();
        if (PROGRAM_ID == 0) {
            // the program didn't initialize
            throw new Exception("The program couldn't be created");
        }

        uniforms = new HashMap<>();
    }

    public void createUniform(String uniformName) throws Exception {
        int uniformLocation = GL20.glGetUniformLocation(PROGRAM_ID, uniformName);
        if (uniformLocation < 0) {
            throw new Exception("Could not find uniform " + uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            GL20.glUniformMatrix4fv(uniforms.get(uniformName), false, value.get(stack.mallocFloat(16)));
        }
    }

    public void setUniform(String uniformName, Vector4f value) {
        GL20.glUniform4f(uniforms.get(uniformName), value.x, value.y, value.z, value.w);
    }

    public void setUniform(String uniformName, Vector3f value) {
        GL20.glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
    }

    public void setUniform(String uniformName, boolean value) {
        // float res = 0;
        // if (value) {
        // res = 1;
        // }
        GL20.glUniform1f(uniforms.get(uniformName), value ? 1 : 0);
    }

    public void setUniform(String uniformName, float value) {
        GL20.glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, int value) {
        GL20.glUniform1i(uniforms.get(uniformName), value);
    }

    /**
     * Changes the attributes of the vertices.
     * 
     * @param shaderCode The file containing the code for the vertex shader (as a
     *                   .vs)
     * @throws Exception The shader failed to initialize.
     */
    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
    }

    /**
     * The fragment shader controls the colors of each pixel in the area bounded by
     * the vertices.
     * 
     * @param shaderCode The file containing the code for the fragment shader (as a
     *                   .vs)
     * @throws Exception The shader failed to initialize.
     */
    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
    }

    /**
     * 
     * 
     * @param shaderCode
     * @param shaderType
     * @return the ID of the generated shader
     * @throws Exception
     */
    public int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderID = GL20.glCreateShader(shaderType);
        if (shaderID == 0) {
            // If the shader failed to be created
            throw new Exception("Error creating shader.  Type: " + shaderType);
        }

        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);

        // GL20.glGetShaderi() gets the information of the shader
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            // if the shader isn't complete then throw an error
            throw new Exception("Error compiling shader.  Type: " + shaderType
                    + "\nInfo: " + GL20.glGetShaderInfoLog(shaderID, 1024));
        }

        /* Link the shader to the program to render it */
        GL20.glAttachShader(PROGRAM_ID, shaderID);

        return shaderID;
    }

    /**
     * Combines multiple shaders into one action that can be displayed.
     * 
     * @throws Exception
     */
    public void link() throws Exception {
        GL20.glLinkProgram(PROGRAM_ID);
        if (GL20.glGetProgrami(PROGRAM_ID, GL20.GL_LINK_STATUS) == 0) {
            throw new Exception("There was an error linking shaders\nInfo: "
                    + GL20.glGetShaderInfoLog(PROGRAM_ID, 1024));
        }

        if (vertexShaderID != 0) {
            GL20.glDetachShader(PROGRAM_ID, vertexShaderID);
        }
        if (fragmentShaderID != 0) {
            GL20.glDetachShader(PROGRAM_ID, fragmentShaderID);
        }

        GL20.glValidateProgram(PROGRAM_ID);
        if (GL20.glGetProgrami(PROGRAM_ID, GL20.GL_VALIDATE_STATUS) == 0) {
            throw new Exception("Unable to validate shader code. \nInfo: "
                    + GL20.glGetProgramInfoLog(PROGRAM_ID, 1024));
        }
    }

    public void bind() {
        GL20.glUseProgram(PROGRAM_ID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void terminate() {
        unbind();
        if (PROGRAM_ID != 0) {
            GL20.glDeleteProgram(PROGRAM_ID);
        }
    }

}
