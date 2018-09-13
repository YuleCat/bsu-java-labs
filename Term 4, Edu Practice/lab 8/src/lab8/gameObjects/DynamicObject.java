package lab8.gameObjects;

import lab8.Vector;
import lab8.WrongLevelFileException;
import lab8.gameObjects.tiles.Tile;
import lab8.gameObjects.tiles.WrongArrayException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

import static lab8.gameObjects.DynamicObject.Direction.RIGHT;
import static lab8.gameObjects.DynamicObject.Direction.UP;

public abstract class DynamicObject extends GameObject {
    public enum Direction {
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    protected static final int[] DELTA_X = {-1, 0, 1, 0};
    protected static final int[] DELTA_Y = {0, -1, 0, 1};

    protected BufferedImage[] rotatedImages = new BufferedImage[4];

    protected Direction direction;
    protected double speed;
    protected HashSet<Tile> neighbourTiles = new HashSet<>();

    public static boolean doSquaresIntersect(Vector sq1, double length1, Vector sq2, double length2) {
        return doSegmentsIntersect(sq1.x, sq1.x + length1, sq2.x, sq2.x + length2) &&
                doSegmentsIntersect(sq1.y, sq1.y + length1, sq2.y, sq2.y + length2);
    }

    private static boolean doSegmentsIntersect(double x1, double x2, double y1, double y2) {
        return isPointInSegment(x1, y1, y2) || isPointInSegment(x2, y1, y2) ||
                isPointInSegment(y1, x1, x2) || isPointInSegment(y2, x1, x2);
    }

    private static boolean isPointInSegment(double x, double x1, double x2) {
        return x >= x1 && x <= x2;
    }

    private static BufferedImage rotateTank(BufferedImage rawImage, Direction direction) {
        int width = rawImage.getWidth();
        int height = rawImage.getHeight();

        BufferedImage result = new BufferedImage(width, height, rawImage.getType());
        Graphics2D g2 = result.createGraphics();
        g2.rotate(Math.toRadians(90 * direction.ordinal()), width / 2, height / 2);
        g2.drawImage(rawImage, null, 0, 0);
        g2.dispose();

        return result;
    }

    public DynamicObject(Vector position) {
        super(position);
    }

    public abstract void update() throws WrongLevelFileException, IOException, WrongArrayException;

    protected abstract void checkTanks() throws WrongArrayException, IOException, WrongLevelFileException;
    protected abstract void checkTiles();
    protected abstract void checkDestructibleTiles(Tile tile);
    protected abstract void checkWindowBorders();

    protected void initRotatedImages(BufferedImage initImage) {
        rotatedImages[0] = initImage;
        rotatedImages[1] = rotateTank(initImage, UP);;
        rotatedImages[2] = rotateTank(initImage, RIGHT);
        rotatedImages[3] = rotateTank(initImage, Direction.DOWN);
    }

    public void setDirection(int direction) {
        setDirection(Direction.values()[direction]);
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
        image = rotatedImages[direction.ordinal()];
    }

    public Direction getDirection() {
        return direction;
    }
}
