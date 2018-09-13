package lab8.gameObjects.tanks;

import lab8.Game;
import lab8.Vector;
import lab8.gameObjects.DynamicObject;
import lab8.gameObjects.tiles.DestructibleTile;
import lab8.gameObjects.tiles.Tile;
import lab8.gameObjects.tiles.TrackTile;
import lab8.gameObjects.tiles.WaterTile;

import java.awt.*;
import java.util.ArrayList;

public class Tank extends DynamicObject {
    private static final double DELTA = 0.08 * TILE_SIZE;

    boolean isOnTrack;
    private Vector prevPosition;
    protected int shotDelay;

    Tank(Vector position) {
        super(position);
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }

    @Override
    public void update() {
        prevPosition = position;
        position = new Vector(position.x + speed * DELTA_X[direction.ordinal()],
                position.y + speed * DELTA_Y[direction.ordinal()]);

        checkTanks();
        checkTiles();
        checkWindowBorders();
    }

    @Override
    protected void checkTanks() {
        PlayerTank playerTank = Game.getPlayerTank();
        ArrayList<EnemyTank> currentEnemyTanks = Game.getCurrentEnemyTanks();

        if (!(this instanceof PlayerTank) && doSquaresIntersect(position, TILE_SIZE - DELTA,
                                                                playerTank.getPosition(), TILE_SIZE - DELTA)) {
            position = prevPosition;
            return;
        }

        for (EnemyTank enemyTank : currentEnemyTanks) {
            if (!(enemyTank.equals(this)) && doSquaresIntersect(position, TILE_SIZE - DELTA,
                                                                enemyTank.position, TILE_SIZE - DELTA)) {
                position = prevPosition;
                return;
            }
        }
    }

    @Override
    protected void checkTiles() {
        Tile[][] gameField = Game.getGameField();

        neighbourTiles.clear();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int row = (int) (position.x + ((j == 1) ? TILE_SIZE - DELTA : DELTA)) / TILE_SIZE;
                int col = (int) (position.y + ((i == 1) ? TILE_SIZE - DELTA : DELTA)) / TILE_SIZE;
                neighbourTiles.add(gameField[row][col]);
            }
        }

        isOnTrack = false;

        for (Tile tile : neighbourTiles) {
            checkDestructibleTiles(tile);
            checkWaterTiles(tile);
            checkTrackTiles(tile);
        }
    }

    @Override
    protected void checkDestructibleTiles(Tile tile) {
        if (tile instanceof DestructibleTile) {
            int[][] shapeOfTile = ((DestructibleTile) tile).getShapeOfTile();
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    if (shapeOfTile[i][j] == 1) {
                        Vector smallTilePosition = new Vector(tile.getPosition().x + j * TILE_SIZE / 2,
                                                              tile.getPosition().y + i * TILE_SIZE / 2);
                        if (doSquaresIntersect(smallTilePosition, TILE_SIZE / 2,
                                               position, TILE_SIZE - DELTA)) {
                            position = prevPosition;
                            return;
                        }
                    }
                }
            }
        }
    }

    private void checkWaterTiles(Tile tile) {
        if (tile instanceof WaterTile)
            position = prevPosition;
    }

    private void checkTrackTiles(Tile tile) {
        if (tile instanceof TrackTile)
            isOnTrack = true;
    }

    @Override
    protected void checkWindowBorders() {
        if (position.x <= 0)
            position.x = 0;
        else if (position.x >= TILE_SIZE * 12)
            position.x = TILE_SIZE * 12;

        if (position.y <= 0)
            position.y = 0;
        else if (position.y >= TILE_SIZE * 12)
            position.y = TILE_SIZE * 12;
    }
}