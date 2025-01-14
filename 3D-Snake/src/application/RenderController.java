package application;

import application.WindowController;

public class RenderController {

    private final WindowController WINDOW;
    private final GameEngineController ENGINE;

    public RenderController(GameEngineController engine) {
        this.ENGINE = engine;
        WINDOW = engine.getWindow();
    }

    /**
     * What should be run on start-up?
     */
    public void initialize() {

    }

    /**
     * Method to render something
     */
    public void render() {

    }

    /**
     * Clears the renderer
     */
    public void clear() {

    }

    /**
     * What should be run when the controller is finished?
     */
    public void terminate() {

    }

}
