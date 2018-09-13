package lab8.gameObjects;

import lab8.Game;
import lab8.Vector;
import lab8.WrongLevelFileException;
import lab8.gameObjects.tanks.*;
import lab8.gameObjects.tiles.*;
import lab8.sounds.Sound;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static lab8.textures.Tanks.BULLET;

public class Bullet extends DynamicObject {
    private static final int BULLET_SIZE = TILE_SIZE / 8;

    private boolean isEnemy;
    private boolean isDestroySteel;

    public Bullet(Tank tank) {
        super(new Vector(tank.getPosition().x + TILE_SIZE / 2 * (1 + DELTA_X[tank.getDirection().ordinal()]) - BULLET_SIZE / 2,
                         tank.getPosition().y + TILE_SIZE / 2 * (1 + DELTA_Y[tank.getDirection().ordinal()]) - BULLET_SIZE / 2));
        initRotatedImages(resizeImage(BULLET, BULLET_SIZE, BULLET_SIZE));
        setDirection(tank.getDirection());
        isEnemy = tank instanceof EnemyTank;
        speed = 2.0;
        isDestroySteel = tank instanceof PlayerTank && ((PlayerTank) tank).getLevel() > 2;
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }

    @Override
    public void update() throws WrongLevelFileException, IOException, WrongArrayException {
        position = new Vector(position.x + speed * DELTA_X[direction.ordinal()],
                              position.y + speed * DELTA_Y[direction.ordinal()]);

        checkBullets();
        checkTanks();
        checkTiles();
        checkWindowBorders();
    }

    private void checkBullets() {
        ArrayList<Bullet> bullets = Game.getBullets();

        for (Bullet bullet : bullets) {
            if (!bullet.equals(this) && doSquaresIntersect(position, BULLET_SIZE, bullet.getPosition(), BULLET_SIZE)) {
                Game.addToRemoveList(this);
                Game.addToRemoveList(bullet);
                return;
            }
        }
    }

    @Override
    protected void checkTanks() throws WrongArrayException, IOException, WrongLevelFileException {
        if (isEnemy) {
            PlayerTank playerTank = Game.getPlayerTank();
            if (doSquaresIntersect(position, BULLET_SIZE, playerTank.getPosition(), TILE_SIZE)) {
                playerTank.tryKill();
                Game.addToRemoveList(this);

                Sound.playSound("sounds/destroyed tank.wav");
            }
        } else {
            ArrayList<EnemyTank> currentEnemyTanks = Game.getCurrentEnemyTanks();

            for (EnemyTank enemyTank : currentEnemyTanks) {
                if (doSquaresIntersect(enemyTank.getPosition(), TILE_SIZE, position, BULLET_SIZE)) {
                    Game.addToRemoveList(this);
                    Game.addToRemoveList(enemyTank);

                    Sound.playSound("sounds/destroyed tank.wav");

                    Game.increaseScore(enemyTank);
                    return;
                }
            }
        }
    }

    @Override
    protected void checkTiles() {
        Tile[][] gameField = Game.getGameField();

        neighbourTiles.clear();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int row = (int) (position.x + ((j == 1) ? BULLET_SIZE : 0)) / TILE_SIZE;
                int col = (int) (position.y + ((i == 1) ? BULLET_SIZE : 0)) / TILE_SIZE;
                if (row != 13 && col != 13)
                    neighbourTiles.add(gameField[row][col]);
            }
        }

        for (Tile tile : neighbourTiles) {
            checkDestructibleTiles(tile);
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
                                position, BULLET_SIZE)) {
                            if (tile instanceof  BrickTile || tile instanceof SteelTile && isDestroySteel) {
                                shapeOfTile[i][j] = 0;
                                ((DestructibleTile) tile).setShapeOfTile(shapeOfTile);
                            }
                            Game.addToRemoveList(this);

                            Sound.playSound("sounds/destroyed brick.wav");
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void checkWindowBorders() {
        if (position.x <= 0 || position.x >= TILE_SIZE * 13 - BULLET_SIZE ||
                position.y <= 0 || position.y >= TILE_SIZE * 13 - BULLET_SIZE)
            Game.addToRemoveList(this);
    }
}
