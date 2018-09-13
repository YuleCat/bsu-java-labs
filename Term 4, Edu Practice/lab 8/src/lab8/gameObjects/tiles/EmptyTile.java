package lab8.gameObjects.tiles;

import lab8.Vector;

import java.awt.*;

import static lab8.textures.Tiles.EMPTY_TILE;

public class EmptyTile extends Tile {
    public EmptyTile(Vector position) {
        super(position);
        //image = resizeImage(EMPTY_TILE);
    }

    @Override
    public void display(Graphics2D g2) {

    }
}
