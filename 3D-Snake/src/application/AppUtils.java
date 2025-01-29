package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class AppUtils {
    // public static boolean gameLoop = true;
    //
    // IDK
    //
    static Charset charset = Charset.forName("UTF-8");

    //
    // Window
    //
    public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = (int) screen.getWidth();
    public static int height = (int) screen.getHeight();

    public static ByteBuffer stringToBB(String input) {
        CharsetEncoder encoder = charset.newEncoder();

        try {
            return encoder.encode(CharBuffer.wrap(input));
        } catch (Exception e) {
            System.out.println("error");
        }

        return null;
    }
}
