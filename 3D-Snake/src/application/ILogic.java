package application;

public interface ILogic {

    void initialize() throws Exception;

    /**
     * Tracks inputs and 'binds' them to code (basically programming what each
     * keybind does)
     */
    void input();

    /**
     * Which game stuff needs to be updated? Example: updating positions
     */
    void update();

    /**
     * 
     */
    void render();

    /**
     * What should be run when the program should terminate?
     */
    void terminate();
}
