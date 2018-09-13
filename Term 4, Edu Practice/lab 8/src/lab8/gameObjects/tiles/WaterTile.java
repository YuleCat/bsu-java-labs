package lab8.gameObjects.tiles;

import lab8.Vector;

import java.awt.*;

import static lab8.textures.Tiles.WATER_TILE;

public class WaterTile extends Tile {
    public WaterTile(Vector position) {
        super(position);
        image = resizeImage(WATER_TILE);
    }
}
