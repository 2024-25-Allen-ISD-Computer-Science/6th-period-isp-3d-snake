public enum GameState {
    PLAYING,
    PAUSED,
    GAME_OVER
}

public class Game {
    private GameState state = GameState.PLAYING;
    private Snake snake = new Snake();

    public void run() {
        init();
        while (!windowShouldClose()) {
            processInput();
            update();
            render();
        }
    }
    private void processInput() {
        // Toggle pause/resume with the 'P' key.
        if (isKeyPressed("P")) {
            if (state == GameState.PLAYING) {
                state = GameState.PAUSED;
            } else if (state == GameState.PAUSED) {
                state = GameState.PLAYING;
            }
        }
        // Restart the game in GAME_OVER state with the 'R' key.
        if (state == GameState.GAME_OVER && isKeyPressed("R")) {
            snake = new Snake();
            state = GameState.PLAYING;
        }

    // Update game logic based on the current state.
    private void update() {
        if (state == GameState.PLAYING) {
            snake.move();
            if (checkGameOver()) {
                state = GameState.GAME_OVER;
            }
        }
    }

    // Render the game based on the current state.
    private void render() {
        clearScreen();
        if (state == GameState.PLAYING || state == GameState.PAUSED) {
            drawGame();
            if (state == GameState.PAUSED) {
                drawPausedOverlay();
            }
        } else if (state == GameState.GAME_OVER) {
            drawGameOverScreen();
        }
        swapBuffers();
    }
