import jdk.nashorn.api.tree.Tree;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

/**
 * Created by Yule Cat on 17.12.2017.
 */
public class WindowApp extends JFrame implements ActionListener {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openMenuItem = new JMenuItem("Open .txt");
    private JMenuItem openXMLMenuItem = new JMenuItem("Open .xml");
    private JMenuItem saveXMLMenuItem = new JMenuItem("Save as .xml");

    private ArrayList<ExportedGoods> array = new ArrayList<>();
    private List goodsList = new List(list1);
    private List resultsList = new List(list2);
    private JTextField goodName = new JTextField(10);
    private JButton findButton = new JButton("Find");
    private JButton addButton = new JButton("Add");

    public WindowApp() {
        setTitle("Exported goods");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(new JLabel(" "));

        Box b1 = new Box(BoxLayout.Y_AXIS);
        JLabel goodsLabel = new JLabel("Exported goods: ");
        goodsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.add(goodsLabel);
        b1.add(new JScrollPane(goodsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        b1.add(goodsList);
        b1.add(new JLabel(" "));
        b1.add(addButton);
        b1.add(new JLabel(" "));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(b1);

        add(new JLabel(" "));
        add(new JLabel(" "));

        Box panel = new Box(BoxLayout.Y_AXIS);
        JLabel resultsLabel = new JLabel("Results of the search: ");
        resultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(resultsLabel);
        panel.add(resultsList);
        JPanel searchPanel = new JPanel();
        searchPanel.add(goodName);
        searchPanel.add(findButton);
        panel.add(searchPanel);
        add(panel);

        add(new JLabel(" "));

        findButton.setEnabled(false);

        fileMenu.add(openMenuItem);
        fileMenu.add(openXMLMenuItem);
        fileMenu.add(saveXMLMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        openMenuItem.addActionListener(this);
        openXMLMenuItem.addActionListener(this);
        saveXMLMenuItem.addActionListener(this);
        addButton.addActionListener(this);
        findButton.addActionListener(this);
        goodName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                findButton.setEnabled(!goodName.getText().equals(""));
            }
        });

        pack();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openMenuItem) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            chooser.setFileFilter(filter);
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                read(chooser.getSelectedFile().getName());
                showList();
                list2.clear();
            }
        } else if (e.getSource() == openXMLMenuItem) {
            boolean isGood;
            list1 = new DefaultListModel<>();

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "xml");
            chooser.setFileFilter(filter);
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(chooser.getSelectedFile().getName(), new FileInputStream(chooser.getSelectedFile().getName()));

                    while (xmlr.hasNext()) {
                        xmlr.next();
                        if (xmlr.isStartElement() && xmlr.getLocalName() == "Exported_goods") {
                            isGood = true;
                            ExportedGoods temp = new ExportedGoods();
                            while (isGood) {
                                xmlr.next();
                                if (xmlr.isStartElement()) {
                                    if (xmlr.getLocalName() == "Name_of_good") {
                                        xmlr.next();
                                        temp.setNameOfGood(xmlr.getText());
                                    } else if (xmlr.getLocalName() == "Importing_country") {
                                        xmlr.next();
                                        temp.setImportingCountry(xmlr.getText());
                                    } else if (xmlr.getLocalName() == "Amount_of_consignment") {
                                        xmlr.next();
                                        int str = Integer.parseInt(xmlr.getText());
                                        temp.setAmountOfConsignment(str);
                                        isGood = false;
                                        list1.addElement(temp);
                                    }
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException | XMLStreamException ex) {
                    ex.printStackTrace();
                }
                showList();
            }
        } else if (e.getSource() == saveXMLMenuItem) {
            if (list1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Collection is empty! Nothing to save.", "Error!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter("result.xml"));

                writer.writeStartDocument("1.0");
                writer.writeCharacters("\n");
                writer.writeStartElement("Exported_Goods_list");
                writer.writeCharacters("\n");

                for (int i = 0; i < list1.size(); ++i) {
                    ExportedGoods item = list1.get(i);
                    writer.writeStartElement("Exported_goods");
                    writer.writeCharacters("\n");

                    writer.writeStartElement("Name_of_good");
                    writer.writeCharacters(item.getNameOfGood());
                    writer.writeEndElement();
                    writer.writeCharacters("\n");

                    writer.writeStartElement("Importing_country");
                    writer.writeCharacters(item.getImportingCountry());
                    writer.writeEndElement();
                    writer.writeCharacters("\n");

                    writer.writeStartElement("Amount_of_consignment");
                    writer.writeCharacters("" + item.getAmountOfConsignment());
                    writer.writeEndElement();
                    writer.writeCharacters("\n");

                    writer.writeEndElement();
                    writer.writeCharacters("\n");
                }
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
            } catch (XMLStreamException | IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == addButton) {
            ExportedGoods temp = new ExportedGoods();
            new AddElementDialog(this, "add goods", temp);

            if(!temp.equals(new ExportedGoods())) {
                list1.addElement(temp);
                showList();
            }
        } else if (e.getSource() == findButton) {
            if (list1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Collection is empty!", "Error!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            list2.clear();

            String good = goodName.getText();
            int totalAmount = 0;
            list2.addElement("Countries:");
            MyComparator cmp = new MyComparator();
            HashMap<ExportedGoods, String> temp = new HashMap<>();
            for (int i = 0; i < list1.size(); ++i){
                if (!temp.containsValue(list1.get(i)) && cmp.compare(list1.get(i), new ExportedGoods(good, null, 0)) == 0){
                    temp.put(list1.get(i), list1.get(i).getNameOfGood());
                }
            }
            Iterator<Map.Entry<ExportedGoods, String>> it = temp.entrySet().iterator();
            while(it.hasNext()){
                ExportedGoods obj = it.next().getKey();
                list2.addElement(obj.getImportingCountry());
                totalAmount += obj.getAmountOfConsignment();
            }
            /*for (ExportedGoods el: array){
                if (cmp.compare(el, new ExportedGoods(good, null, 0)) == 0) {
                    resultsList.add(el.getImportingCountry());
                    totalAmount += el.getAmountOfConsignment();
                }
            }*/
            list2.addElement("Total amount: " + totalAmount);
        }
    }

    private void read(String filename) {
        try {
            Scanner sc = new Scanner(new FileReader(filename));
            list1 = new DefaultListModel<>();
            while (sc.hasNext())
                list1.addElement(new ExportedGoods(sc.next(), sc.next(), sc.nextInt()));

            sc.close();
        } catch (FileNotFoundException | InputMismatchException err) {
            JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void showList() {
        if (list1 != null) {
            list1.clear();
            for(int i = 0; i < list1.size(); ++i){
                list1.addElement(list1.get(i));
            }
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static void main(String[] args)
    {
        WindowApp app = new WindowApp();
    }
}
