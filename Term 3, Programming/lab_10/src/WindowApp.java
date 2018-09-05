import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowApp extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;

    private JButton button = new JButton("Hey!");
    private JLabel mouseCoordinates = new JLabel("x: , y: ");

    public WindowApp(){
        button.setBounds(100, 150, 100 + 4 * button.getText().length(), 100);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.add(button);

        JPanel coordPanel = new JPanel();
        coordPanel.setLayout(new BorderLayout());
        coordPanel.add(mouseCoordinates, BorderLayout.SOUTH);
        coordPanel.setBackground(Color.WHITE);

        add(buttonPanel);
        add(coordPanel, BorderLayout.SOUTH);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseCoordinates.setText("x: " + e.getX() + ", y: " + e.getY());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setLocation(e.getX() - 7, e.getY() - 30);
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseCoordinates.setText("x: " + button.getX() + ", y: " + button.getY());
                if (e.isControlDown()) button.setLocation(button.getX() + e.getX(), button.getY() + e.getY());
            }
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    if (button.getText().length() != 0) {
                        StringBuilder sb = new StringBuilder(button.getText());
                        sb.delete(sb.length() - 1, sb.length());
                        button.setText(String.valueOf(sb));
                    }
                }
                else if (e.getKeyCode() != KeyEvent.VK_CONTROL ) {
                    button.setText(button.getText() + e.getKeyText(e.getKeyCode()));
                }
                button.setSize(100 + 4 * button.getText().length(), 100);
            }
        });

        setTitle("Lab 10");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args){
        WindowApp frame = new WindowApp();
    }
}
