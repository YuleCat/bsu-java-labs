import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Yule Cat on 13.12.2017.
 */
public class WindowApp extends JFrame {
    public WindowApp() {
        setTitle("Lab 12");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Lists", new ImageIcon("img/papyrus.png"), new Tab1(), "Lists");
        tabbedPane.addTab("Buttons", new ImageIcon("img/mask.png"), new Tab2(), "Buttons");
        tabbedPane.addTab("Radio", new ImageIcon("img/paper-fan.png"), new Tab3(), "RadioButtons");

        add(tabbedPane);

        pack();
        setVisible(true);
    }

    class Tab1 extends JPanel {
        public Tab1() {
            setLayout(new BorderLayout());
            JPanel central = new JPanel();
            central.setLayout(new BorderLayout());

            final String[] DATA1 = {"Ctulhu", "Lovecraft", "R'lyeh", "Hello world"};
            final String[] DATA2 = {"C++", "Java", "Asm", "Lisp"};

            DefaultListModel leftlistModel = new DefaultListModel();
            JList leftlist = new JList(leftlistModel);
            DefaultListModel rightlistModel = new DefaultListModel();
            JList rightlist = new JList(rightlistModel);

            for (String el : DATA1)
                leftlistModel.addElement(el);

            for (String el : DATA2)
                rightlistModel.addElement(el);

            leftlist.setPreferredSize(new Dimension(100, 300));
            add(new JScrollPane(leftlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.WEST);
            rightlist.setPreferredSize(new Dimension(100, 300));
            add(new JScrollPane(rightlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);

            JButton right = new JButton(new ImageIcon("img/next.png"));
            right.setPreferredSize(new Dimension(50,50));
            central.add(right, BorderLayout.NORTH);
            JButton left = new JButton(new ImageIcon("img/back.png"));
            left.setPreferredSize(new Dimension(50,50));
            central.add(left, BorderLayout.SOUTH);
            add(central, BorderLayout.CENTER);

            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!leftlist.isSelectionEmpty()) {
                        rightlistModel.addElement(leftlist.getSelectedValue());
                        leftlistModel.remove(leftlist.getSelectedIndex());
                        if (!leftlistModel.isEmpty())
                            leftlist.setSelectedIndex(-1);

                        right.setEnabled(!leftlistModel.isEmpty());
                        left.setEnabled(!rightlistModel.isEmpty());
                    }
                }
            });
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!rightlist.isSelectionEmpty()) {
                        leftlistModel.addElement(rightlist.getSelectedValue());
                        rightlistModel.remove(rightlist.getSelectedIndex());
                        if (!rightlistModel.isEmpty())
                            rightlist.setSelectedIndex(-1);

                        right.setEnabled(!leftlistModel.isEmpty());
                        left.setEnabled(!rightlistModel.isEmpty());
                    }
                }
            });
            pack();
            setResizable(false);
        }
    }

    class Tab2 extends JPanel {
        private final Color BLUE = new Color(0,76,153);
        private static final int btnNumber = 4;
        private static final int btnSize = 80;
        private Color defBtnColor;
        private String defName;

        public Tab2() {
            setLayout(new GridLayout(btnNumber, btnNumber));
            ExMouseAdapter listener = new ExMouseAdapter();
            for (int i = 0; i < btnNumber; i++)
                for (int j = 0; j < btnNumber; j++) {
                    JButton bn = new JButton(i * btnNumber + j + 1 + "");
                    bn.addMouseListener(listener);
                    add(bn);
                }
            setPreferredSize(new Dimension(btnSize * btnNumber,btnSize * btnNumber));
        }

        class ExMouseAdapter extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e) {
                JButton tmp = ((JButton) e.getSource());
                defName = tmp.getText();
                tmp.setText("Clicked!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton tmp = ((JButton) e.getSource());
                tmp.setText(defName);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton tmp = ((JButton) e.getSource());
                defBtnColor = tmp.getBackground();
                tmp.setBackground(BLUE);
                tmp.setForeground(BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton tmp = ((JButton) e.getSource());
                tmp.setBackground(defBtnColor);
                tmp.setForeground(Color.BLACK);
            }
        }
    }

    class Tab3 extends JPanel {
        public Tab3() {
            Box box = new Box(BoxLayout.Y_AXIS);
            ButtonGroup radioGroup = new ButtonGroup();
            final ImageIcon[] icons = new ImageIcon[] {
                    new ImageIcon("img/pie.png"),
                    new ImageIcon("img/cheese.png"),
                    new ImageIcon("img/breakfast.png"),
                    new ImageIcon("img/watermelon.png"),
            };

            for (int i = 0; i < 4; i++) {
                JRadioButton temp = new JRadioButton(icons[0]);
                temp.setPressedIcon(icons[1]);
                temp.setRolloverIcon(icons[2]);
                temp.setSelectedIcon(icons[3]);
                radioGroup.add(temp);
                box.add(temp);
            }
            add(box);
        }
    }

    public static void main(String[] args) {
        WindowApp app = new WindowApp();
    }
}