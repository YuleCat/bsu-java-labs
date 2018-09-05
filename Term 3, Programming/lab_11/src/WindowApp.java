import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yule Cat on 10.12.2017.
 */
public class WindowApp extends JFrame implements ActionListener {
    private static final Color PURPLE = new Color(139,18,191);

    private PaintingArea paintingArea = new PaintingArea();
    private JButton blackColor = new JButton("");
    private JButton purpleColor = new JButton("");
    private JButton orangeColor = new JButton("");
    private JButton openButton = new JButton("Open picture");
    private JButton saveTextButton = new JButton("Save as text");

    public WindowApp(){
        JFrame frame = new JFrame("Paint");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box panel = new Box(BoxLayout.Y_AXIS);
        panel.add(new JScrollPane(paintingArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        frame.add(panel, BorderLayout.CENTER);

        JPanel settingsPanel = new JPanel(new FlowLayout());
        settingsPanel.add(blackColor);
        blackColor.setBackground(Color.BLACK);
        blackColor.addActionListener(this);
        blackColor.setPreferredSize(new Dimension(40,25));

        settingsPanel.add(purpleColor);
        purpleColor.setBackground(PURPLE);
        purpleColor.addActionListener(this);
        purpleColor.setPreferredSize(new Dimension(40,25));

        settingsPanel.add(orangeColor);
        orangeColor.setBackground(Color.ORANGE);
        orangeColor.addActionListener(this);
        orangeColor.setPreferredSize(new Dimension(40,25));

        settingsPanel.add(openButton);
        openButton.addActionListener(this);

        settingsPanel.add(saveTextButton);
        saveTextButton.addActionListener(this);

        frame.add(settingsPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(550, 400));
        frame.setMinimumSize(new Dimension(400, 200));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == blackColor)
            paintingArea.setColor(Color.BLACK);

        if(e.getSource() == purpleColor)
            paintingArea.setColor(PURPLE);

        else if(e.getSource() == orangeColor)
            paintingArea.setColor(Color.ORANGE);

        else if(e.getSource() == openButton){
            Scanner sc = null;
            try {
                sc = new Scanner(GetFile("Text files (.txt)", "txt", true));
                paintingArea.openTextFile(sc);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (NullPointerException ex) {
            } finally {
                if (sc != null)
                    sc.close();
            }
        }

        else if(e.getSource() == saveTextButton){
            PrintWriter wr = null;
            try {
                wr = new PrintWriter(GetFile("Text files (.txt)", "txt", false));
                paintingArea.saveAsText(wr);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (NullPointerException ex) {
            } finally {
                if (wr != null)
                    wr.close();
            }
        }
    }

    private File GetFile(String filtername, String fileext, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if (open) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(filtername, fileext);
            chooser.setFileFilter(filter);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                return chooser.getSelectedFile();
            else
                return null;
        } else {
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                return chooser.getSelectedFile();
            else
                return null;
        }
    }

    public static void main(String[] args){
        WindowApp app = new WindowApp();
    }
}
