package lab11;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ShopSaxHandler extends DefaultHandler {
    private int itemsCount;
    private double minCost, maxCost, sumCost;
    private String name;

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(namespaceURI, localName, qName, atts);
        if (qName.equals("Shop")) {
            itemsCount = 0;
            minCost = 1e9;
            maxCost = 0;
            sumCost = 0;
            name = atts.getValue("name");
        }

        if (qName.equals("ShopItem")) {
            itemsCount++;
            double cost = Double.parseDouble(atts.getValue("cost"));
            minCost = Math.min(minCost, cost);
            maxCost = Math.max(maxCost, cost);
            sumCost += cost;
        }
    }

    public String getItemsCount() {
        return "" + itemsCount;
    }

    public String getMinCost() {
        return itemsCount == 0 ? "none" : "" + minCost;
    }

    public String getMaxCost() {
        return itemsCount == 0 ? "none" : "" + maxCost;
    }

    public String getAvgCost() {
        return itemsCount == 0 ? "none" : "" + sumCost / itemsCount;
    }

    public String getName() {
        return name;
    }
}