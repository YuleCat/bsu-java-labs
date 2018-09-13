package lab8.gameObjects.tanks;

import lab8.Vector;

import static lab8.textures.Tanks.ENEMY_FAST_TANK;

public class FastTank extends EnemyTank {
    public FastTank(Vector position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_FAST_TANK));
        setDirection(Direction.DOWN);
        speed = 2.0;
    }
}
