package lab8.gameObjects.tanks;

import lab8.Vector;

import static lab8.textures.Tanks.ENEMY_WEAK_TANK;

public class WeakTank extends EnemyTank {
    public WeakTank(Vector position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_WEAK_TANK));
        setDirection(Direction.DOWN);
        speed = 1.3;
    }
}
