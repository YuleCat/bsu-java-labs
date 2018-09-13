package lab8.textures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tanks {
    public static BufferedImage PLAYER_TANK_1 = null;
    public static BufferedImage PLAYER_TANK_2 = null;
    public static BufferedImage PLAYER_TANK_3 = null;
    public static BufferedImage PLAYER_TANK_4 = null;

    public static BufferedImage ENEMY_WEAK_TANK = null;
    public static BufferedImage ENEMY_FAST_TANK = null;
    public static BufferedImage ENEMY_STANDARD_TANK = null;
    public static BufferedImage ENEMY_MASSIVE_TANK = null;

    public static BufferedImage BULLET = null;

    public static BufferedImage TANKS_LEFT = null;

    static {
        try {
            PLAYER_TANK_1 = loadImage("textures/player tank/level 1.png");
            PLAYER_TANK_2 = loadImage("textures/player tank/level 2.png");
            PLAYER_TANK_3 = loadImage("textures/player tank/level 3.png");
            PLAYER_TANK_4 = loadImage("textures/player tank/level 4.png");

            ENEMY_WEAK_TANK = loadImage("textures/enemy tanks/weak.png");
            ENEMY_FAST_TANK = loadImage("textures/enemy tanks/fast.png");
            ENEMY_STANDARD_TANK = loadImage("textures/enemy tanks/standard.png");
            ENEMY_MASSIVE_TANK = loadImage("textures/enemy tanks/massive.png");

            BULLET = loadImage("textures/bullet.png");

            TANKS_LEFT = loadImage("textures/tanks left.png");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(new File(imageName));
    }
}
