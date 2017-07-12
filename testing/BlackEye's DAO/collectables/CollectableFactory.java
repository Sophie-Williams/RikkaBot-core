package com.rikkabot.rikkabotcore.dao.collectables;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Collectable factory.
 * ====================
 *
 * Factory for the `collectables` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class CollectableFactory extends Factory<Collectable>
{
    /**
     * Collectable builder.
     */
    @Getter(lazy = true)
    private CollectableBuilder _builder = new CollectableBuilder();

    /**
     * Returns API endpoint.
     *
     * @return API server endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "collectables";
}
