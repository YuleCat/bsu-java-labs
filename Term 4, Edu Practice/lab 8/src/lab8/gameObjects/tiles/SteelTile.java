package lab8.gameObjects.tiles;


import lab8.Vector;

import static lab8.textures.Tiles.SMALL_STEEL_TILE;

public class SteelTile extends DestructibleTile {
    public SteelTile(Vector position, int[] shapeOfTile) throws WrongArrayException {
        super(position, shapeOfTile);
        image = resizeImage(SMALL_STEEL_TILE, TILE_SIZE / 2, TILE_SIZE / 2);
    }
}
