package application;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class GameEngineController {

    //
    // Frames
    //
    private static final long NANOSECOND = 1000000000L; // number of nanoseconds in a second
    private static final long FRAMERATE = 1000; // 1000 frames per time

    private int fps; // the actual frames per second
    private static float frametime = 1f / FRAMERATE; // how long it takes to render one frame, so time/frames
                                                     // (seconds/frames)
    boolean isRunning = false;

    private final WindowController WINDOW;
    private GLFWErrorCallback errorCallback;

    //
    // wtf is all this help-
    //
    private ILogic logic;

    public GameEngineController() {
        WINDOW = Launcher.getWindow();
        logic = Launcher.getGame();

        // Setting where any errors are registered
        errorCallback = GLFWErrorCallback.createPrint(System.err);
        GLFW.glfwSetErrorCallback(errorCallback);
    }

    /**
     * What should be run on start-up?
     */
    public void initialize() throws Exception {
        WINDOW.initialize();
        logic.initialize();

        execute();
    }

    /**
     * What should be run on a loop? This method is called once.
     */
    public void execute() {
        isRunning = true;
        int f_p = 0; // frames processed
        long t_c = 0; // tracks the amount of time passed (between 0 and 1 seconds)
        long t_0 = System.nanoTime(); // starting time, aka time nought
        double t_u = 0; // time (in seconds) that weren't rendered

        boolean render = false;

        while (isRunning) {
            //
            // Game Logic
            //
            input();
            // execute(); // recursion bad :(
            update();

            //
            // Frame rendering
            //
            long t_1 = System.nanoTime(); // when does this frame start?
            long delta_t = t_1 - t_0; // time between the last frame and the new one
            t_0 = t_1; // cuz thats how derivatives work

            // tracks time through integral of rate of change of time
            t_u += delta_t / (double) NANOSECOND; // amount of time (in seconds) that wasn't rendered
            t_c += delta_t;

            // call method for getting inputs

            // Sets bool to render and resets t_u because then the frame would be rendered
            while (t_u > frametime) {
                render = true;
                t_u -= frametime;

                if (WINDOW.isTerminated()) {
                    terminate();
                }

                // Module used to literally count the frames per second
                // could literally be removed and no one would care
                if (t_c >= NANOSECOND) {
                    setFPS(f_p);
                    f_p = 0;
                    t_c = 0;
                    WINDOW.setTitle("3D Snake" + getFPS());
                }
            }

            if (render) {
                // execute(); // Recursion bad :(
                render();
                // System.out.println("rendering!");
                f_p++;
            }

            render = false;
        }
    }

    public void input() {
        logic.input();
    }

    public void render() {
        logic.render();
        WINDOW.execute();
    }

    public void update() {
        logic.update();
    }

    /**
     * What should be run when the game engine is stopped?
     */
    public void terminate() {
        // window.terminate(); // the window is already terminated at this point
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    //
    // Get & Set Methods
    //
    public WindowController getWindow() {
        return WINDOW;
    }

    public int getFPS() {
        return fps;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }
}
