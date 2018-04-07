package objects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TgaImage {

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 800;
    private BufferedImage image;
    private String name;

    public TgaImage(String name) {
        this(name, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public TgaImage(String name, int width, int height) {
        this.name = name + ".png";
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public TgaImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name + ".png";
    }

    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public void setPixel(int x, int y, Color color) {
        image.setRGB(Math.max(0, Math.min(x, image.getWidth() - 1)),
                Math.max(0, Math.min(y, image.getHeight() - 1)),
                color.getRGB());
    }

    public int getColor(int x, int y) {
        return image.getRGB(x, y);

    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void flipVertically() {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        image = createTransformed(image, at);
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
