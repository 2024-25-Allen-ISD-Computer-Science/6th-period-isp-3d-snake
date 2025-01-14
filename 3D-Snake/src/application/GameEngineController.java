package application;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class GameEngineController {

    private static final long NANOSECOND = 1000000000L; // number of nanoseconds in a second
    private static final long FRAMERATE = 1000; // 1000 frames per second

    private int fps; // the actual frames per second
    private static float frametime = 1f / FRAMERATE; // the time between frames
    boolean isRunning = false;

    private WindowController window;
    private GLFWErrorCallback errorCallback;

    public GameEngineController() {
        window = new WindowController("3D Snake", 10, 10, false, 0);

        // Setting where any errors are registered
        errorCallback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
    }

    /**
     * What should be run on start-up?
     */
    public void initialize() {
        window.initialize();
        isRunning = true;
        execute();
    }

    /**
     * What should be run on a loop? This method is called once.
     */
    public void execute() {
        isRunning = true;
        int frames = 0; // how many frames have successfully been rendered
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0; // time (in seconds) that weren't rendered

        while (isRunning) {
            boolean render = false;

            long startTime = System.nanoTime(); // when does this frame start?
            long elapsedTime = startTime - lastTime; // time between the last frame and the new one
            lastTime = startTime;

            unprocessedTime += elapsedTime / (double) NANOSECOND; // amount of time (in seconds) that weren't rendered
            frameCounter += elapsedTime; // number of frames

            // call method for getting inputs

            // Loop to render the frame
            while (unprocessedTime > frametime) {
                render = true;
                unprocessedTime -= frametime;

                if (window.isTerminated()) {
                    terminate();
                }

                if (frameCounter >= NANOSECOND) {
                    setFPS(frames);
                    frames = 0;
                    frameCounter = 0;
                    window.setTitle("3D Snake" + getFPS());
                }
            }

            if (render) {
                execute();
                render();
                frames++;
            }
        }

        // Originally had:

        // while (!window.isTerminated()) {
        // // WINDOW.execute();
        // }
    }

    public void render() {
        window.execute();
    }

    /**
     * What should be run when the game engine is stopped?
     */
    public void terminate() {

    }

    //
    // Get & Set Methods
    //
    public WindowController getWindow() {
        return window;
    }

    public int getFPS() {
        return fps;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }
}
