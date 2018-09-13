package lab8.gameObjects.tiles;

import lab8.Vector;

import static lab8.textures.Tiles.SMALL_BRICK_TILE;

public class BrickTile extends DestructibleTile {
    public BrickTile(Vector position, int[] shapeOfTile) throws WrongArrayException {
        super(position, shapeOfTile);
        image = resizeImage(SMALL_BRICK_TILE, TILE_SIZE / 2, TILE_SIZE / 2);
    }
}
