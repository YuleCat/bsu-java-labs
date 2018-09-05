package lab11;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends JFrame {
    private JTable shopTable;
    private JTextField shopNameField;

    private void loadSettings() {
        setResizable(false);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.put("Table.gridColor", new ColorUIResource(Color.gray));
    }

    private void loadTable() {
        shopTable = new JTable(new Shop());

        add(new JScrollPane(shopTable), BorderLayout.CENTER);
    }

    private void loadMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem saveToXML = new JMenuItem("Save to XML");
        saveToXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    ((Shop) shopTable.getModel()).setName(shopNameField.getText());
                    ((Shop) shopTable.getModel()).saveToXML(chooser.getSelectedFile());
                } catch (FileNotFoundException err) {
                    JOptionPane.showMessageDialog(this, "Incorrect file path", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToXML);

        JMenuItem loadFromXML = new JMenuItem("Load from XML");
        loadFromXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    shopTable.setModel(Shop.loadFromXML(chooser.getSelectedFile()));
                    shopNameField.setText(((Shop) shopTable.getModel()).getName());
                    JOptionPane.showMessageDialog(this, "Loaded shop with name " + ((Shop) shopTable.getModel()).getName(), "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Incorrect XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(loadFromXML);

        JMenuItem xmlInfo = new JMenuItem("Show XML quick info");
        xmlInfo.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    ShopSaxHandler shopHandler = new ShopSaxHandler();
                    parser.parse(chooser.getSelectedFile(), shopHandler);
                    JOptionPane.showMessageDialog(this,
                            "Shop name: " + shopHandler.getName() +
                                    "\nItems count: " + shopHandler.getItemsCount() +
                                    "\nMinimum item cost: " + shopHandler.getMinCost() +
                                    "\nMaximum item cost: " + shopHandler.getMaxCost() +
                                    "\nAverage item cost: " + shopHandler.getAvgCost(),
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Incorrect XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        file.add(xmlInfo);

        JMenuItem saveToBinary = new JMenuItem("Save to binary");
        saveToBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary files", "bin");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    ((Shop) shopTable.getModel()).setName(shopNameField.getText());
                    ((Shop) shopTable.getModel()).saveToBinary(chooser.getSelectedFile());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(this, "Incorrect file path", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToBinary);

        JMenuItem loadFromBinary = new JMenuItem("Load from binary");
        loadFromBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary files", "bin");
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    shopTable.setModel(Shop.loadFromBinary(chooser.getSelectedFile()));
                    shopNameField.setText(((Shop) shopTable.getModel()).getName());
                    JOptionPane.showMessageDialog(this, "Loaded shop with name " + ((Shop) shopTable.getModel()).getName(), "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Incorrect binary file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(loadFromBinary);

        menuBar.add(file);

        JMenu data = new JMenu("Data");

        JMenuItem addRow = new JMenuItem("Add row");
        addRow.addActionListener(e -> {
            ((Shop) shopTable.getModel()).getItems().add(new ShopItem());
            shopTable.updateUI();
        });
        data.add(addRow);

        JMenuItem deleteRow = new JMenuItem("Delete row(s)");
        deleteRow.addActionListener(e -> {
            ((Shop) shopTable.getModel()).deleteRows(shopTable.getSelectedRows());
            shopTable.updateUI();
        });
        data.add(deleteRow);

        menuBar.add(data);

        add(menuBar, BorderLayout.NORTH);
    }

    private void loadField() {
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.add(new JLabel("Shop name:"));
        shopNameField = new JTextField("");
        southPanel.add(shopNameField);

        add(southPanel, BorderLayout.SOUTH);
    }

    public App() {
        super("Lab 11");

        loadSettings();
        loadTable();
        loadMenu();
        loadField();

        try {
            Thread.sleep(7500);
        } catch (InterruptedException ignored) {}
        setVisible(true);
        toFront();
    }

    public static void main(String[] args) {
        new App();
    }
}