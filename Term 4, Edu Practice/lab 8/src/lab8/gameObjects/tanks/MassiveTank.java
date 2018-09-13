package lab8.gameObjects.tanks;

import lab8.Vector;

import static lab8.textures.Tanks.ENEMY_MASSIVE_TANK;

public class MassiveTank extends EnemyTank {
    public MassiveTank(Vector position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_MASSIVE_TANK));
        setDirection(Direction.DOWN);
        speed = 1.0;
    }
}
