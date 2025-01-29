package objects;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class ObjectUtils {

    public static FloatBuffer storeData(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

}
