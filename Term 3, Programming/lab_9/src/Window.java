import series.Exponential;
import series.Liner;
import series.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Yule Cat on 17.11.2017.
 */
public class Window extends JFrame implements ActionListener {
    private JLabel firstElemLabel = new JLabel("First element: ");
    private JTextField firstElem = new JTextField("0.5",5);
    private JLabel stepLabel = new JLabel("Step: ");
    private JTextField step = new JTextField("2.0",5);
    private JLabel nLabel = new JLabel("n: ");
    private JTextField n = new JTextField("7",5);
    private JRadioButton liner = new JRadioButton("Liner");
    private JRadioButton exponential = new JRadioButton("Exponential");
    private JLabel filenameLabel = new JLabel("Filename: ");
    private JTextField filename = new JTextField("result", 10);
    private JButton show = new JButton("Show");
    private JButton save = new JButton("Save in file");
    private Series series;

    public Window(){
        super("Series");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        ButtonGroup radioB = new ButtonGroup();
        radioB.add(liner);
        radioB.add(exponential);

        firstElemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        stepLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        filenameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        liner.setHorizontalAlignment(SwingConstants.CENTER);
        exponential.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridLayout(6, 2));
        add(firstElemLabel);
        add(firstElem);
        add(stepLabel);
        add(step);
        add(nLabel);
        add(n);
        add(liner);
        liner.setSelected(true);
        add(exponential);
        add(filenameLabel);
        add(filename);
        add(show);
        add(save);

        show.addActionListener(this);
        save.addActionListener(this);

        pack();
    }

    private void setSeries() throws NumberFormatException, ZeroEqualsException{
        double f = Double.parseDouble(firstElem.getText());
        double s = Double.parseDouble(step.getText());
        int number = Integer.parseInt(n.getText());
        if (f != 0 && s!= 0 && number > 0) {
            series.setFirst(f);
            series.setStep(s);
            series.setN(number);
        }
        else throw new ZeroEqualsException("One of parameters equals zero!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // listeners for radio & edits
            series = (liner.isSelected()) ? new Liner() : new Exponential();
            setSeries();

            if (e.getSource() == show) {
                JOptionPane.showMessageDialog(getContentPane(), series,
                        liner.isSelected() ? liner.getText() : exponential.getText(), JOptionPane.INFORMATION_MESSAGE);
            }
            else if (e.getSource() == save) {
                series.saveInFile(filename.getText());
            }
        } catch(ZeroEqualsException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Some data is incorrect! Please try again",
                    "alert", JOptionPane.WARNING_MESSAGE);
        } catch(FileNotFoundException ex){}
    }
}