import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yule Cat on 11.12.2017.
 */
public class PaintingArea extends JPanel {
    Color curColor = Color.BLACK;
    private Line curLine = new Line();
    private ArrayList<Line> lines = new ArrayList<>();

    public PaintingArea(){
        setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curLine.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                curLine = new Line(curColor);
                lines.add(curLine);
                curLine.add(new Point(e.getX(), e.getY()));
            }
        });
    }

    public void setColor(Color color){
        curColor = color;
    }

    public void openTextFile(Scanner sc) {
        lines.clear();
        while (sc.hasNext()) {
            Line templine = new Line(Color.BLACK);
            lines.add(templine);
            int size = sc.nextInt();
            for (int i = 0; i < size; i++)
                templine.add(new Point(sc.nextInt(), sc.nextInt()));
            templine.setColor(new Color(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        repaint();
    }

    public void saveAsText(PrintWriter wr){
        for (Line line : lines) {
            wr.write(line.size() + " ");
            for (int i = 0; i < line.size(); ++i) {
                wr.write((int)line.get(i).getX() + " " + (int)line.get(i).getY() + " ");
            }
            wr.write(line.getColor().getRed() + " " + line.getColor().getGreen() + " " + line.getColor().getBlue() + " ");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line : lines) {
            line.draw(g);
        }
        setBackground(Color.WHITE);
    }

    protected class Line extends ArrayList<Point>{
        Color color = Color.BLACK;

        Line(){
            super();
        }

        Line(Color color){
            this.color = color;
        }

        public Color getColor(){
            return color;
        }

        public void setColor(Color color){
            this.color = color;
        }

        public void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            for (int i = 0; i < size() - 1; i++) {
                g2.setColor(color);
                g2.drawLine((int)get(i).getX(), (int)get(i).getY(), (int)get(i + 1).getX(), (int)get(i + 1).getY());
            }
        }
    }
}
