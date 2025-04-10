package application.controllers;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class WindowController {
    private long window;
    //
    // dunno yet
    //
    private static final float FOV = (float) Math.toRadians(60);
    private static final float Z_FAR = 0.01f;
    private static final float Z_NEAR = 1000f;

    //
    // Display
    //
    private final Matrix4f projectMatrix;
    private String title;
    private int width;
    private int height;
    private WindowDaemon daemon;

    //
    // Functions
    //
    private boolean resize;
    private boolean isTerminated;
    private boolean maximized;

    //
    // Settings
    //
    private long monitor;
    private boolean vSync;

    public WindowController(String title, int height, int width, boolean vSync, long monitor) {
        //
        // Display
        //
        this.title = title;
        this.height = height;
        this.width = width;
        projectMatrix = new Matrix4f();

        //
        // Functions
        //
        isTerminated = false;
        maximized = false;

        //
        // Settings
        //
        this.vSync = vSync;
        // 0 for windowed, number for full screen on a certain monitor
        this.monitor = monitor;
    }

    /**
     * Initializes the window controller.
     */
    public void initialize() {
        // Where all error msgs get printed?
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if (!GLFW.glfwInit()) {
            throw new Error("'GLFW used Bozo'n Around! It was super effective!'\nGLFW couldn't initialize.");
        }

        //
        // Window Settings
        //

        // Resets all window settings
        GLFW.glfwDefaultWindowHints();

        // Sets window settings
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
        // Which OpenGL version should be used?
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);

        // Check if it should be maximized.
        if (width == 0 || height == 0) {
            width = 100;
            height = 100;
            GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
            maximized = true;
        }

        // Creates the window
        window = GLFW.glfwCreateWindow(width, height, title, monitor, 0);

        // If the window wasn't created?
        if (window == MemoryUtil.NULL) {
            throw new Error("'Window used L Bozo! It was super effective!'\nThe window couldn't initialize.");
        }

        if (maximized || monitor == 0) {
            maximized = true;
            GLFW.glfwMaximizeWindow(window);
        } else {
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
            GLFW.glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
        }

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        GL11.glClearColor(0f, 0f, 0f, 0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);

        daemon = new WindowDaemon(this);
        daemon.setDaemon(true);
        daemon.start();
    }

    /**
     * What should be run every tick?
     */
    public void execute() {
        // Swaps the loaded screen with what should be loaded
        GLFW.glfwSwapBuffers(window);
        // Loads events into the event handler.
        GLFW.glfwPollEvents();

        // Check for termination
        if (GLFW.glfwWindowShouldClose(window)) {
            terminate();
        }
    }

    public void callbackMethods() {
        // Handles how the window is resized
        GLFW.glfwSetWindowSizeCallback(window, (window, width, height) -> {
            this.width = width;
            this.height = height;
        });

        // Area of memory that holds color data for the screen
        GLFW.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            this.width = width;
            this.height = height;
            // System.out.println("w: " + width + " h: " + height);
            this.setResize(true);
        });

    }

    /**
     * What should happen when the window is terminated?
     */
    public void terminate() {
        isTerminated = true;
        daemon.terminate();
        GLFW.glfwDestroyWindow(window);
    }

    //
    // Boolean Methods
    //
    public boolean isKeyPressed(int keycode) {
        return GLFW.glfwGetKey(window, keycode) == GLFW.GLFW_PRESS;
    }

    //
    // Get & Set Methods
    //
    public void setResize(boolean resize) {
        this.resize = resize;
    }

    public boolean getResize() {
        return resize;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }

    public long getWindowID() {
        return window;
    }

    /**
     * 
     * @param r the red value of the screen
     * @param g the green value of the screen
     * @param b the blue value of the screen
     * @param a the alpha (brightness) value of the screen
     */
    public void setClearColor(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
    }

    //
    // Projection Matrix (whatever that is)
    //
    public Matrix4f getMatrix() {
        return projectMatrix;
    }

    public Matrix4f updateMatrix() {
        float aspectRatio = (float) width / height;
        return projectMatrix.setPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
    }

    public Matrix4f updateMatrix(Matrix4f matrix, int height, int width) {
        float aspectRatio = (float) width / height;
        return matrix.setPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
    }
}

class WindowDaemon extends Thread {
    WindowController window;
    boolean shouldRun = true;

    public WindowDaemon(WindowController window) {
        super("goofy");

        this.window = window;
    }

    @Override
    public void run() {
        while (shouldRun) {
            window.callbackMethods();
        }
    }

    public void terminate() {
        shouldRun = false;
    }
}
