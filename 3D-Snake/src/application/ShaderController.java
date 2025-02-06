package application;

import org.lwjgl.opengl.GL20;

public class ShaderController {

    private final int PROGRAM_ID;
    private int vertexShaderID, fragmentShaderID;

    public ShaderController() throws Exception {
        PROGRAM_ID = GL20.glCreateProgram();
        if (PROGRAM_ID == 0) {
            // the program didn't initialize
            throw new Exception("The program couldn't be created");
        }
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
            throw new Exception("yappity yap");
        }

        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);

        // GL20.glGetShaderi() gets the information of the shader
        // if the shader isn't complete then throw an error
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception();
        }

        // links the shader to the program to render it?
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
