package application;

import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;

import org.lwjgl.*;
import org.lwjgl.glfw.*;

/**
 * 
 */
public class OpenGL {
    long window = 0;

    // note: double buffering
    // one buffer is for what is displayed
    // the other buffer is what is being rendered
    public OpenGL() {
        init();
    }

    //
    // Initialize Methods
    //
    public void init() {
        // Initializes libraries
        GLFW.glfwInit(); // needed for the window to display

        //
        // Window
        //
        window = GLFW.glfwCreateWindow(AppConstants.width / 2, AppConstants.height / 2,
                "3D Snake", 0, 0);
        GLFW.glfwMakeContextCurrent(window);

        // Needs to be after window creation
        GL.createCapabilities(); // necessary for OpenGL
    }

    /**
     * Runs the OpenGL app.
     */
    public void run() {
        loop();

        // Properly terminates the window
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    //
    // Event Methods
    //

    /**
     * The main event/render loop.
     */
    public void loop() {
        gridTest();

        while (!GLFW.glfwWindowShouldClose(window)) {
            // Poll Events to manage them
            GLFW.glfwPollEvents();

            // Event loop
        }
    }

    //
    // Render Methods
    //

    /**
     * Displays a random shade of red. <em>Be cautious, as the colors flash!</em>
     */
    public void twoDRenderTest() {
        GL20.glClearColor((float) Math.random(), 0f, 0f, 0f); // to change the color
        GL20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // to make the change occur
        GLFW.glfwSwapBuffers(window); // to show the change
    }

    /**
     * Displays a grid
     */
    public void gridTest() {
        // Note: only create VBOs once, and update when needed!
        GL20.glClearColor(0f, 0f, 0f, 0f);

        int vertices = 3;
        int dimension = 2;

        // Upload vertex data to GPU ig?
        FloatBuffer vertexData = BufferUtils.createFloatBuffer(vertices * dimension);
        vertexData.put(new float[] { 0f, 0f });
        vertexData.put(new float[] { 1f, 1f });
        vertexData.put(new float[] { .5f, .5f });

        // VBO (Vertex Buffer Object)
        // "Uploads" vertex data to the GPU
        int vboVertex = GL20.glGenBuffers();

        // Upload actual data
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, vboVertex);

        // Copy buffer data
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, vertexData, GL20.GL_STATIC_DRAW);
        // Static draw: uploaded once, and drawn many times (the world apparently)
        // Dynamic Draw: uploaded once, change from time to time, and drawn more times
        // Stream draw: vertex data is uploaded once and drawn once.

        //
        // Colors
        //
        int colors = 3;

        FloatBuffer colorData = BufferUtils.createFloatBuffer(vertices * colors);
        colorData.put(new float[] { .5f, 0f, 0f });
        colorData.put(new float[] { .5f, 0f, 0f });
        colorData.put(new float[] { .5f, 0f, 0f });

        int vboColor = GL20.glGenBuffers();
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, vboColor);
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, colorData, GL20.GL_STATIC_DRAW);

        //
        // Rendering
        //
        GL20.glVertexPointer(dimension, GL20.GL_FLOAT, 0, vertexData);
        GL20.glColorPointer(dimension, GL20.GL_FLOAT, 0, colorData);

        // // figure out what this is for
        // GL20.glEnableClientState(GL20.GL_COLOR_ARRAY);
        // GL20.glEnableClientState(GL20.GL_VERTEX_ARRAY);

        // the method of rendering
        // the first vertex in the array
        // the number of vertices
        GL20.glDrawArrays(GL20.GL_TRIANGLES, 0, vertices); // constructs geometry
        int vShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER); // rasterization?
        GL20.glCompileShader(vShader); // finished product?

        // GL20.glDisableClientState(GL20.GL_COLOR_ARRAY);
        // GL20.glDisableClientState(GL20.GL_VERTEX_ARRAY);

        // GL20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwSwapBuffers(window);
    }

}
