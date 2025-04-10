package application.controllers;

public abstract class ControllerAbstract {
    /**
     * Initializes the Controller. Runs when the game boots up.
     */
    public abstract void initialize();

    /**
     * What the controller runs. Only needs to be called once.
     */
    public abstract void execute();

    /**
     * Runs when the Controller ends.
     */
    public abstract void terminate();
}
