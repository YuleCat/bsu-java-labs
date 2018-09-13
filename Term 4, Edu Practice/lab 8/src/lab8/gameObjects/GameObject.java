package lab8.gameObjects;

import lab8.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    public static final int TILE_SIZE = 48;

    protected BufferedImage image;
    protected Vector position;

    public GameObject(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public abstract void display(Graphics2D g2);

    public static BufferedImage resizeImage(BufferedImage rawImage) {
        return resizeImage(rawImage, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage resizeImage(BufferedImage rawImage, int width, int height) {
        Image tmp = rawImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
