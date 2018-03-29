package utils;

import geometric_primitives.Face;
import geometric_primitives.Model;
import geometric_primitives.PointDouble;
import objects.TgaImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Drawer {
    private static final String OUTPUT_FILENAME_DEFAULT = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_default.png";
    private static final String OUTPUT_FILENAME_BRESENHAM = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_bresenham.png";
    private static final String OUTPUT_FILENAME_WU = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_wu.png";

    public static void draw(Model model) throws IOException {
        TgaImage imageDefault = new TgaImage("Default");
        TgaImage imageBresenham = new TgaImage("Bresenham");
        TgaImage imageWu = new TgaImage("Wu");
        for (Face face : model.getFaces()) {
            for (int i = 0; i < 3; i++) {
                PointDouble from = model.getV(face.getVidx(i));
                int x = (int) ((from.getX()) * imageDefault.getWidth() / 2);
                int y = (int) ((from.getY()) * imageDefault.getHeight() / 2);
                PointDouble to = model.getV(face.getVidx((i + 1) % 3));
                int x1 = (int) ((to.getX()) * imageDefault.getWidth() / 2);
                int y1 = (int) ((to.getY()) * imageDefault.getHeight() / 2);
                drawLine(x, y, x1, y1, imageDefault, Color.GREEN);
                drawLineBresenham(x, y, x1, y1, imageBresenham, Color.GREEN);
                drawLineWu(x, y, x1, y1, imageWu, Color.GREEN);
            }
        }
        outputImage(imageDefault, OUTPUT_FILENAME_DEFAULT);
        outputImage(imageBresenham, OUTPUT_FILENAME_BRESENHAM);
        outputImage(imageWu, OUTPUT_FILENAME_WU);
    }

    private static void outputImage(TgaImage image, String filename) throws IOException {
        long time = System.currentTimeMillis();
        image.flipVertically();
        writeImageToFile(image, filename);
        System.err.println(String.format("%s is drawn, time spent: %d ms", image.getName(), System.currentTimeMillis() - time));
    }

    private static void writeImageToFile(TgaImage image, String filename) throws IOException {
        ImageIO.write(image.getImage(), "PNG", new File(filename));
    }

    public static void drawLine(int x0, int y0, int x1, int y1, TgaImage image, Color color) {
        for (double t = 0; t < 1.0; t += .05) {
            int x = (int) (x0 * (1.0 - t) + x1 * t);
            int y = (int) (y0 * (1.0 - t) + y1 * t);
            image.setPixel(x, y, color);
        }
    }

    public static void drawLineBresenham(int x0, int y0, int x1, int y1, TgaImage image, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        boolean steep = false;
        if (Math.abs(x0 - x1) < Math.abs(y0 - y1)) { // if the line is steep, we transpose the image
            int temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;
            steep = true;
        }
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        dx = x1 - x0;
        dy = y1 - y0;

        double derror = Math.abs(((dy + .0) / dx));
        double error = 0;
        int y = y0;

        for (int x = x0; x <= x1; x++) {
            if (steep) {
                image.setPixel(y, x, color);
            } else {
                image.setPixel(x, y, color);
            }
            error += derror;
            if (error > 0.5) {
                y += (y1 > y0 ? 1 : -1);
                error -= 1.0;
            }
        }
    }

    public static void drawLineWu(int x0, int y0, int x1, int y1, TgaImage image, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        boolean steep = false;
        if (Math.abs(x0 - x1) < Math.abs(y0 - y1)) { // if the line is steep, we transpose the image
            int temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;
            steep = true;
        }
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        dx = x1 - x0;
        dy = y1 - y0;
        double derror = Math.abs((dy + .0) / dx);
        double error = 0;
        int y = y0;
        int sy = (y1 > y0 ? 1 : -1);

        for (int x = x0; x <= x1; x++) {
            Color c = getColorWithBrightness(color, (1.0 - error));
            if (steep) {
                image.setPixel(y, x, c);
            } else {
                image.setPixel(x, y, c);
            }
            c = getColorWithBrightness(color, (1.0 - error));
            if (steep) {
                image.setPixel(y + sy, x, c);
            } else {
                image.setPixel(x, y + sy, c);
            }
            error += derror;
            if (error > 1.0) {
                y += sy;
                error -= 1;
            }
        }
    }

    private static Color getColorWithBrightness(Color color, double brightness) {
        return new Color((int) (color.getRed() * brightness),
                (int) (color.getGreen() * brightness),
                (int) (color.getBlue() * brightness));
    }

}
