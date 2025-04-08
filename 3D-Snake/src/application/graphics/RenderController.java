package application.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import application.GameUtils;
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
        shader = new ShaderController();

        shader.createVertexShader(GameUtils.loadResource("/shaders/vertex.vs"));
        shader.createFragmentShader(GameUtils.loadResource("/shaders/fragment.fs"));
        shader.link();
        shader.createUniform("textureSampler");
    }

    /**
     * Method to render something
     */
    public void render(Model model) {
        /* Removes all data from the screen */
        clear();

        shader.bind();
        shader.setUniform("textureSampler", 0);

        GL30.glBindVertexArray(model.getID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0); // num needs to match the value num
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());

        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

        shader.unbind();
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
