package com.rikkabot.rikkabotcore.dao.accounts.items;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Item factory.
 * =============
 *
 * Factory for the `accounts_items` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class ItemFactory extends Factory<Item>
{
    /**
     * Item builder.
     */
    @Getter(lazy = true)
    private ItemBuilder _builder = new ItemBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "accounts_items";

    /**
     * Returns all items of a given account.
     *
     * @param id Account ID.
     *
     * @return All items of account.
     */
    public Map<Integer, Item> byAccountsID(int id)
    {
        Map<String, String> params = new HashMap<>();
        params.put("accounts_id", id +"");

        return super.where(params);
    }
}
