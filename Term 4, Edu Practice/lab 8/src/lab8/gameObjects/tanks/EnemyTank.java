package lab8.gameObjects.tanks;

import lab8.Game;
import lab8.Vector;

import java.util.Random;

public class EnemyTank extends Tank {
    private int changeDirectionDelay;

    EnemyTank(Vector position) {
        super(position);
        changeDirectionDelay = 0;
        shotDelay = 50;
    }

    @Override
    public void update() {
        Random random = new Random();
        if (changeDirectionDelay == 0)
            setDirection(random.nextInt(4));
        changeDirectionDelay = (++changeDirectionDelay) % 100;

        if (shotDelay == 0)
            Game.addBullet(this);
        shotDelay = (++shotDelay) % 100;

        super.update();
    }
}