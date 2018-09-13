package lab8;

import lab8.gameObjects.tiles.WrongArrayException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static lab8.GamePanel.PANEL_HEIGHT;
import static lab8.GamePanel.PANEL_WIDTH;

class BattleCityApp extends JFrame {
    private static final int WIDTH = PANEL_WIDTH + 25;
    private static final int HEIGHT = PANEL_HEIGHT + 40;

    private GamePanel gamePanel = new GamePanel();

    BattleCityApp() throws WrongArrayException, IOException, WrongLevelFileException {
        setTitle("Battle City");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        add(gamePanel, BorderLayout.CENTER);

        pack();
        setResizable(false);
        setVisible(true);
    }
}
