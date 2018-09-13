package lab8.gameObjects.tiles;

import lab8.Vector;

import java.awt.*;

import static lab8.textures.Tiles.GRASS_TILE;

public class GrassTile extends Tile {
    public GrassTile(Vector position) {
        super(position);
        image = resizeImage(GRASS_TILE);
    }
}
