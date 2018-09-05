package lab11;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractTableModel implements Serializable {
    private String name;
    private List<ShopItem> items;

    public static TableModel loadFromXML(File file) throws IOException, SAXException {
        Shop shop = new Shop();

        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);

        try {
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(file);
            Node root = doc.getFirstChild();
            if (!root.getNodeName().equals("Shop"))
                throw new SAXException();
            shop.setName(root.getAttributes().getNamedItem("name").getTextContent());
            NodeList items = root.getChildNodes();
            for (int i = 0; i < items.getLength(); ++i) {
                Node node = items.item(i);
                if (!node.getNodeName().equals("ShopItem"))
                    continue;

                ShopItem item = new ShopItem();
                item.setName(node.getTextContent());
                item.setCategory(node.getAttributes().getNamedItem("category").getTextContent());
                item.setCost(Double.parseDouble(node.getAttributes().getNamedItem("cost").getTextContent()));
                item.setWeight(Double.parseDouble(node.getAttributes().getNamedItem("weight").getTextContent()));

                shop.getItems().add(item);
            }
        } catch (ParserConfigurationException ignored) {}

        return shop;
    }

    public static Shop loadFromBinary(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        Shop shop = new Shop();
        shop.setName((String) stream.readObject());
        int size = stream.readInt();
        for (int i = 0; i < size; ++i)
            shop.getItems().add((ShopItem) stream.readObject());
            //shop.setItems((List<ShopItem>) stream.readObject());
        stream.close();
        return shop;
    }

    public Shop() {
        items = new ArrayList<>();
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public void setItems(List<ShopItem> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return items.get(rowIndex).getName();
            case 1:
                return items.get(rowIndex).getCategory();
            case 2:
                return items.get(rowIndex).getCost();
            case 3:
                return items.get(rowIndex).getWeight();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Category";
            case 2:
                return "Cost";
            case 3:
                return "Weight";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            default:
                return super.getColumnClass(column);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                items.get(rowIndex).setName((String) aValue);
                break;
            case 1:
                items.get(rowIndex).setCategory((String) aValue);
                break;
            case 2:
                items.get(rowIndex).setCost((Double) aValue);
                break;
            case 3:
                items.get(rowIndex).setWeight((Double) aValue);
                break;
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Shop name=\"" + name + "\">\n");

        for (ShopItem item : items)
            sb.append("\t" + item.toXML() + "\n");

        sb.append("</Shop>");
        return sb.toString();
    }

    public void saveToXML(File file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        pw.println(toXML());
        pw.close();
    }

    public void saveToBinary(File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(name);
        stream.writeInt(items.size());
        for (ShopItem item : items)
            stream.writeObject(item);
        stream.close();
    }

    public void deleteRows(int[] rows) {
        for (int i = rows.length - 1; i >= 0; --i)
            if (rows[i] < items.size())
                items.remove(rows[i]);
    }
}