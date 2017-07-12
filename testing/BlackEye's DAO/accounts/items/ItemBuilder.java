package com.rikkabot.rikkabotcore.dao.accounts.items;

import org.json.JSONObject;

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
                    json.getInt("items_id"),
                    json.getInt("levels_id"),
                    json.getInt("amount")
            );
        } catch(Exception e) {
            Console.println("Couldn't  build item for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return item;
    }
}
