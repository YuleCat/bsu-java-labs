package lab8.gameObjects.tiles;

import lab8.Vector;
import lab8.gameObjects.GameObject;

import java.awt.*;

public abstract class Tile extends GameObject {
    Tile(Vector position) {
        super(position);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }
}
