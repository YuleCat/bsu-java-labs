package lab8.textures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {
    public static BufferedImage GRASS_TILE = null;
    public static BufferedImage WATER_TILE = null;
    public static BufferedImage TRACK_TILE = null;
    public static BufferedImage BRICK_TILE = null;
    public static BufferedImage BRICK_DOWN_TILE = null;
    public static BufferedImage BRICK_UP_TILE = null;
    public static BufferedImage BRICK_LEFT_TILE = null;
    public static BufferedImage BRICK_RIGHT_TILE = null;
    public static BufferedImage SMALL_BRICK_TILE = null;
    public static BufferedImage STEEL_TILE = null;
    public static BufferedImage STEEL_DOWN_TILE = null;
    public static BufferedImage STEEL_UP_TILE = null;
    public static BufferedImage STEEL_LEFT_TILE = null;
    public static BufferedImage STEEL_RIGHT_TILE = null;
    public static BufferedImage SMALL_STEEL_TILE = null;
    public static BufferedImage EMPTY_TILE = null;

    static {
        try {
            GRASS_TILE = loadImage("textures/tiles/grass.png");
            WATER_TILE = loadImage("textures/tiles/water.png");
            TRACK_TILE = loadImage("textures/tiles/track.png");
            BRICK_TILE = loadImage("textures/tiles/brick.png");
            BRICK_DOWN_TILE = loadImage("textures/tiles/brick down.png");
            BRICK_UP_TILE = loadImage("textures/tiles/brick up.png");
            BRICK_LEFT_TILE = loadImage("textures/tiles/brick left.png");
            BRICK_RIGHT_TILE = loadImage("textures/tiles/brick right.png");
            SMALL_BRICK_TILE = loadImage("textures/tiles/brick 4x4.png");
            STEEL_TILE = loadImage("textures/tiles/steel.png");
            STEEL_DOWN_TILE = loadImage("textures/tiles/steel down.png");
            STEEL_UP_TILE = loadImage("textures/tiles/steel up.png");
            STEEL_LEFT_TILE = loadImage("textures/tiles/steel left.png");
            STEEL_RIGHT_TILE = loadImage("textures/tiles/steel right.png");
            SMALL_STEEL_TILE = loadImage("textures/tiles/steel 4x4.png");
            EMPTY_TILE = loadImage("textures/tiles/empty.png");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(new File(imageName));
    }
}
