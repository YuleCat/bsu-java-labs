import java.awt.event.*;
import java.awt.*;
import java.util.zip.DataFormatException;
import javax.swing.*;

public class AddElementDialog extends JDialog implements ActionListener, KeyListener {
    private JButton ok = new JButton("OK");
    private JLabel labelName = new JLabel("  Name of the good:");
    private JLabel labelCountry = new JLabel("  Importing country:");
    private JLabel labelAmount = new JLabel("  Amount:");
    private JTextField inputName = new JTextField("", 5);
    private JTextField inputCountry = new JTextField("", 5);
    private JTextField inputAmount = new JTextField("", 5);
    private ExportedGoods eg;

    public AddElementDialog(JFrame parent, String title, ExportedGoods o) {
        super(parent, title, true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        eg = o;

        Container container = this.getContentPane();
        setLayout(new GridLayout(4, 2));

        container.add(labelName);
        container.add(inputName);

        container.add(labelCountry);
        container.add(inputCountry);

        container.add(labelAmount);
        container.add(inputAmount);

        container.add(new JLabel(" "));

        inputName.addKeyListener(this);
        inputCountry.addKeyListener(this);
        inputAmount.addKeyListener(this);
        ok.addActionListener(this);

        ok.setEnabled(false);
        container.add(ok);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                eg.setNameOfGood(inputName.getText());
                eg.setImportingCountry(inputCountry.getText());
                eg.setAmountOfConsignment(Integer.parseInt(inputAmount.getText()));
                setVisible(false);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ok.setEnabled(!(inputName.getText().equals("") || inputCountry.getText().equals("") || inputAmount.getText().equals("")));
    }
}