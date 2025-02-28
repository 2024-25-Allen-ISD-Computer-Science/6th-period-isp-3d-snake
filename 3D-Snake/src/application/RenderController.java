package application;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import objects.Model;

public class RenderController {
    //
    // Variables
    //
    private ShaderController shader;

    public RenderController() {

    }

    /**
     * What should be run on start-up?
     */
    public void initialize() throws Exception {
        // shader = new ShaderController();
        // shader.createVertexShader(GameUtils.loadResource("/shaders/vertex.vs"));
        // shader.createFragmentShader(GameUtils.loadResource("/shaders/fragment.fs"));
        // shader.link();
    }

    /**
     * Method to render something
     */
    public void render(Model model) {
        // Removes all data from the screen
        clear();

        // shader.bind();

        GL30.glBindVertexArray(model.getID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);

        // shader.unbind();
    }

    /**
     * Clears the renderer (as in it clears all models)
     */
    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * What should be run when the controller is finished?
     */
    public void terminate() {
        shader.terminate();
    }
}
