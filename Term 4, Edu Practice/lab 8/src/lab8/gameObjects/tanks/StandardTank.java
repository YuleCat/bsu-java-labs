package lab8.gameObjects.tanks;

import lab8.Vector;

import static lab8.textures.Tanks.ENEMY_STANDARD_TANK;

public class StandardTank extends EnemyTank {
    public StandardTank(Vector position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_STANDARD_TANK));
        setDirection(Direction.DOWN);
        speed = 1.6;
    }
}
