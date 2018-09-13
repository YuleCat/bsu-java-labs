package lab8.gameObjects.tiles;

import lab8.Vector;

import static lab8.textures.Tiles.TRACK_TILE;

public class TrackTile extends Tile {
    public TrackTile(Vector position) {
        super(position);
        image = resizeImage(TRACK_TILE);
    }
}
