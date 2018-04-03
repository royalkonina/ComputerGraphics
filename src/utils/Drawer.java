package utils;

import geometric_primitives.Face;
import geometric_primitives.Model;
import geometric_primitives.PointDouble;
import objects.TgaImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Drawer {
    private static final String OUTPUT_FILENAME = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_";
    private static final long SEED = 5;
    private static final double MIN_VALUE = -1e18;


    public static void draw(Model model) throws IOException {
        TgaImage imageDefault = new TgaImage("Default");
        TgaImage imageBresenham = new TgaImage("Bresenham");
        TgaImage imageWu = new TgaImage("Wu");
        for (Face face : model.getFaces()) {
            for (int i = 0; i < 3; i++) {
                PointDouble from = model.getV(face.getVidx(i));
                int x = toInt(from.x, imageDefault.getWidth());
                int y = toInt(from.y, imageDefault.getHeight());
                PointDouble to = model.getV(face.getVidx((i + 1) % 3));
                int x1 = toInt(to.x, imageDefault.getWidth());
                int y1 = toInt(to.y, imageDefault.getHeight());
                drawLine(x, y, x1, y1, imageDefault, Color.GREEN);
                drawLineBresenham(x, y, x1, y1, imageBresenham, Color.GREEN);
                drawLineWu(x, y, x1, y1, imageWu, Color.GREEN);
            }
        }
        outputImage(imageDefault);
        outputImage(imageBresenham);
        outputImage(imageWu);
    }

    private static int toInt(double coordinate, int length) {
        return (int) Math.max(0, Math.min(length - 1, coordinate * length / 2 + length / 2));
    }

    private static void outputImage(TgaImage image) throws IOException {
        long time = System.currentTimeMillis();
        image.flipVertically();
        writeImageToFile(image, OUTPUT_FILENAME + image.getName());
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

    public static void drawWithBarycentric(Model model, PointDouble camera) throws IOException {
        Random rnd = new Random(SEED);

        TgaImage image = new TgaImage("Barycentric");
        double[][] bufferZ = new double[image.getWidth()][image.getHeight()];
        for (int i = 0; i < bufferZ.length; i++) {
            Arrays.fill(bufferZ[i], MIN_VALUE);
        }
        for (Face face : model.getFaces()) {
            PointDouble p0 = model.getV(face.getVidx(0));
            PointDouble p1 = model.getV(face.getVidx(1));
            PointDouble p2 = model.getV(face.getVidx(2));
            double lightFactor = getLightFactor(p0, p1, p2, camera);
            if (lightFactor >= 0) continue;

            int xmin = toInt(Math.min(p0.x, Math.min(p1.x, p2.x)), image.getWidth());
            int xmax = toInt(Math.max(p0.x, Math.max(p1.x, p2.x)), image.getWidth());
            int ymin = toInt(Math.min(p0.y, Math.min(p1.y, p2.y)), image.getHeight());
            int ymax = toInt(Math.max(p0.y, Math.max(p1.y, p2.y)), image.getHeight());

            //Color color = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Color color = Color.LIGHT_GRAY;
            for (int yIdx = ymin; yIdx <= ymax; yIdx++) {
                double y = center(yIdx, image.getHeight());
                for (int xIdx = xmin; xIdx < xmax; xIdx++) {
                    double x = center(xIdx, image.getWidth());
                    double[] l = getBarysticCoordinates(x, y, p0, p1, p2);
                    if (inTriangle(l)) {
                        double z = p0.z * l[0] + p1.z * l[1] + p2.z * l[2];
                        if (z > bufferZ[xIdx][yIdx]) {
                            image.setPixel(xIdx, yIdx, getColorWithBrightness(color, -lightFactor));
                            bufferZ[xIdx][yIdx] = z;
                        }
                    }
                }
            }

        }
        outputImage(image);
    }

    private static boolean inTriangle(double[] l) {
        return l[0] >= 0 && l[1] >= 0 && l[2] >= 0;
    }

    private static double[] getBarysticCoordinates(double x, double y, PointDouble p0, PointDouble p1, PointDouble p2) {
        double[] l = new double[3];
        l[0] = ((y - p2.y) * (p1.x - p2.x) - (x - p2.x) * (p1.y - p2.y)) / ((p0.y - p2.y) * (p1.x - p2.x) - (p0.x - p2.x) * (p1.y - p2.y));
        l[1] = ((y - p0.y) * (p2.x - p0.x) - (x - p0.x) * (p2.y - p0.y)) / ((p1.y - p0.y) * (p2.x - p0.x) - (p1.x - p0.x) * (p2.y - p0.y));
        l[2] = ((y - p1.y) * (p0.x - p1.x) - (x - p1.x) * (p0.y - p1.y)) / ((p2.y - p1.y) * (p0.x - p1.x) - (p2.x - p1.x) * (p0.y - p1.y));
        return l;
    }

    private static double center(int idx, int length) {
        return (idx + .5) * 2 / length - 1;
    }

    private static double getLightFactor(PointDouble p0, PointDouble p1, PointDouble p2, PointDouble camera) {
        double nx = (p2.y - p0.y) * (p1.z - p0.z) - (p2.z - p0.z) * (p1.y - p0.y);
        double ny = -(p2.x - p0.x) * (p1.z - p0.z) + (p2.z - p0.z) * (p1.x - p0.x);
        double nz = (p2.x - p0.x) * (p1.y - p0.y) - (p2.y - p0.y) * (p1.x - p0.x);

        return (nx * camera.x + ny * camera.y + nz * camera.z) / norm(nx, ny, nz) / norm(camera.x, camera.y, camera.z);
    }

    private static double norm(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }
}
