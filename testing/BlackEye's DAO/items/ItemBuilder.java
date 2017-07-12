package com.rikkabot.rikkabotcore.dao.items;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Item builder.
 * =============
 *
 * Builds `Item` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ItemBuilder implements Builder<Item>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Item build(JSONObject json)
    {
        Item item = null;

        try {
            item = new Item(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("category"),
                    json.getString("description"),
                    json.getInt("price"),
                    json.getString("type"),
                    (json.getInt("is_elite") == 1),
                    (json.getInt("is_buyable") == 1),
                    (json.getInt("is_event") == 1),
                    this._buildValues(json.getString("name"))
            );
        } catch(Exception e) {
            Console.println("Couldn't build item for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }

        return item;
    }

    /**
     * Builds and returns item's values.
     *
     * @param name Item name.
     *
     * @return Item values.
     */
    private List<Item.Value> _buildValues(String name)
    {
        List<Item.Value> values = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse("items/"+ name +".xml");

            Element e = dom.getDocumentElement();

            NodeList list = e.getElementsByTagName("value");
            for(int i = 0; i < list.getLength(); i++) {
                Element node = (Element)list.item(i);

                double value     = Double.parseDouble(node.getAttribute("value"));
                String type      = node.getAttribute("type");
                String operation = node.getAttribute("operation");

                values.add(new Item.Value(value, Item.Value.Type.valueOf(type), Item.Value.Operation.valueOf(operation)));
            }
        } catch(Exception e) {
            Console.debug("Couldn't parse values for item `"+ name +"`!");
            Console.debug(e.getMessage());
        }

        return values;
    }
}
