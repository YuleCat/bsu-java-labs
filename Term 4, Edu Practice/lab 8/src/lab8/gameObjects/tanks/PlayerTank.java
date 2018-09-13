package lab8.gameObjects.tanks;

import lab8.Controller;
import lab8.Game;
import lab8.Vector;
import lab8.WrongLevelFileException;
import lab8.gameObjects.tiles.WrongArrayException;
import lab8.sounds.Sounds;

import java.io.IOException;

import static lab8.textures.Tanks.PLAYER_TANK_1;

public class PlayerTank extends Tank {
    private int level;
    private int lives;

    public PlayerTank(Vector position) {
        super(position);
        initRotatedImages(resizeImage(PLAYER_TANK_1));
        setDirection(Direction.UP);
        level = 1;
        speed = 1.6;
        lives = 3;
    }

    @Override
    public void update() {
        if (Controller.isKeyPressed() || isOnTrack) {
            super.update();
        }

        if (shotDelay > 0)
            shotDelay--;
    }

    public boolean isShotAllowed() {
        if (shotDelay == 0) {
            shotDelay = 50;
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void tryKill() throws WrongLevelFileException, IOException, WrongArrayException {
        position = new Vector(6 * TILE_SIZE, 12 * TILE_SIZE);
        --lives;
    }
}
