package com.rikkabot.rikkabotcore.dao.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Item factory.
 * =============
 *
 * Factory for the `items` table.
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
    private String _endpoint = "items";
}
