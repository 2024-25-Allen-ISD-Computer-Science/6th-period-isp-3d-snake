package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

import org.lwjgl.system.MemoryUtil;

public class GameUtils {
    //
    // Window
    //
    public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = (int) screen.getWidth();
    public static int height = (int) screen.getHeight();

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();

        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int[] indices) {
        IntBuffer buffer = MemoryUtil.memAllocInt(indices.length);
        buffer.put(indices).flip();

        return buffer;
    }

    public static String loadResource(String fileName) throws Exception {
        String output;

        InputStream is = GameUtils.class.getResourceAsStream(fileName);
        Scanner io = new Scanner(is, StandardCharsets.UTF_8.name());
        output = io.useDelimiter("\\A").next();
        io.close();
        return output;
    }

    public static List<String> readAllLines(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Class.forName(utils.getName().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}
