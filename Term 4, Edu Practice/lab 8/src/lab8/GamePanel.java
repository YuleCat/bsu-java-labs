package lab8;

import lab8.gameObjects.tiles.WrongArrayException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static lab8.gameObjects.tiles.Tile.TILE_SIZE;

public class GamePanel extends JPanel {
    public static final int PANEL_WIDTH = 13 * TILE_SIZE + 150;
    public static final int PANEL_HEIGHT = 13 * TILE_SIZE;
    static final int DELAY = 10;

    private Timer timer;

    GamePanel() throws WrongLevelFileException, IOException, WrongArrayException {
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);

        initTimer();

        addKeyListener(new Controller());

        setFocusable(true);
    }

    private void initTimer() {
        timer = new Timer(DELAY, e -> {
            try {
                Game.update();
            } catch (WrongLevelFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (WrongArrayException e1) {
                e1.printStackTrace();
            }
            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Game.display(g2);

        g2.dispose();
    }
}
