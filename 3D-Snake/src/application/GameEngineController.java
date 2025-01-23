package application;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class GameEngineController {

    private static final long NANOSECOND = 1000000000L; // number of nanoseconds in a second
    private static final long FRAMERATE = 1000; // 1000 frames per time

    private int fps; // the actual frames per second
    private static float frametime = 1f / FRAMERATE; // how long it takes to render one frame, so time/frames
                                                     // (seconds/frames)
    boolean isRunning = false;

    private WindowController window;
    private GLFWErrorCallback errorCallback;

    public GameEngineController() {
        window = new WindowController("3D Snake", 10, 10, false, GLFW.glfwGetPrimaryMonitor());

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
        int f_p = 0; // frames processed
        long frameCounter = 0;
        long t_0 = System.nanoTime(); // starting time, aka time nought
        double t_u = 0; // time (in seconds) that weren't rendered

        while (isRunning) {
            boolean render = false;

            long t_1 = System.nanoTime(); // when does this frame start?
            long delta_t = t_1 - t_0; // time between the last frame and the new one
            t_0 = t_1; // cuz thats how derivatives work

            t_u += delta_t / (double) NANOSECOND; // amount of time (in seconds) that wasn't rendered
            frameCounter += delta_t; // number of unprocessed frames

            // call method for getting inputs

            // Sets bool to render and resets t_u because then the frame would be rendered
            while (t_u > frametime) {
                render = true;
                t_u -= frametime;

                if (window.isTerminated()) {
                    terminate();
                }

                // Module used to literally count the frames per second
                // could literally be removed and no one would care
                if (frameCounter >= NANOSECOND) {
                    setFPS(f_p);
                    f_p = 0;
                    frameCounter = 0;
                    window.setTitle("3D Snake" + getFPS());
                }
            }

            if (render) {
                // execute(); // Recursion bad :(
                render();
                f_p++;
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
