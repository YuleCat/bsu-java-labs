package lab8;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller extends KeyAdapter {
    private static boolean isGamePaused = false;
    private static boolean isKeyPressed;
    private static ArrayList<Integer> pressedKeys = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
        analysePressedKeys(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        analysePressedKeys(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
                pressedKeys.remove((Integer) e.getKeyCode());
                if (pressedKeys.isEmpty())
                    isKeyPressed = false;
                else
                    Game.getPlayerTank().setDirection(pressedKeys.get(pressedKeys.size() - 1) - 37);
                break;

            case KeyEvent.VK_SPACE:
                if (Game.getPlayerTank().isShotAllowed())
                    Game.addBullet(Game.getPlayerTank());
                break;

            case KeyEvent.VK_P:
                isGamePaused = !isGamePaused;
                break;

        }
    }

    private void analysePressedKeys(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
                if (!pressedKeys.contains(keyCode))
                    pressedKeys.add(keyCode);
                isKeyPressed = true;
                Game.getPlayerTank().setDirection(keyCode - 37);
                break;
        }
    }

    public static boolean isKeyPressed() {
        return isKeyPressed;
    }

    public static boolean isGamePaused() {
        return isGamePaused;
    }
}
