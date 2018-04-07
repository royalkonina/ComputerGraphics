package utils;

import objects.TgaImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class PictureIO {

    private static final String OUTPUT_FILENAME = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_";
    private static int offset;

    private PictureIO() {

    }

    public static void outputImage(TgaImage image) throws IOException {
        long time = System.currentTimeMillis();
        image.flipVertically();
        writeImageToFile(image, OUTPUT_FILENAME + image.getName());
        System.err.println(String.format("%s is drawn, time spent: %d ms", image.getName(), System.currentTimeMillis() - time));
    }

    private static void writeImageToFile(TgaImage image, String filename) throws IOException {
        ImageIO.write(image.getImage(), "PNG", new File(filename));
    }

    public static TgaImage readImage(String filename, String name) throws IOException {
        BufferedImage image = getImage(filename);
        return new TgaImage(image, name);
    }

    public static BufferedImage getImage(String fileName) throws IOException {
        File f = new File(fileName);
        byte[] buf = new byte[(int) f.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        bis.read(buf);
        bis.close();
        return decode(buf);
    }

    private static int btoi(byte b) {
        int a = b;
        return (a < 0 ? 256 + a : a);
    }

    private static int read(byte[] buf) {
        return btoi(buf[offset++]);
    }

    public static BufferedImage decode(byte[] buf) throws IOException {
        offset = 0;

        // Reading header
        for (int i = 0; i < 12; i++)
            read(buf);
        int width = read(buf) + (read(buf) << 8);
        int height = read(buf) + (read(buf) << 8);
        read(buf);
        read(buf);

        // Reading data
        int n = width * height;
        int[] pixels = new int[n];
        int idx = 0;

        while (n > 0) {
            int nb = read(buf);
            if ((nb & 0x80) == 0) {
                for (int i = 0; i <= nb; i++) {
                    int b = read(buf);
                    int g = read(buf);
                    int r = read(buf);
                    pixels[idx++] = 0xff000000 | (r << 16) | (g << 8) | b;
                }
            } else {
                nb &= 0x7f;
                int b = read(buf);
                int g = read(buf);
                int r = read(buf);
                int v = 0xff000000 | (r << 16) | (g << 8) | b;
                for (int i = 0; i <= nb; i++)
                    pixels[idx++] = v;
            }
            n -= nb + 1;
        }

        BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bimg.setRGB(0, 0, width, height, pixels, 0, width);
        return bimg;
    }

}
