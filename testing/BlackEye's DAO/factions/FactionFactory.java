package com.rikkabot.rikkabotcore.dao.factions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Faction factory.
 * ================
 *
 * Factory for the `factions` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class FactionFactory extends Factory<Faction>
{
    /**
     * Builder object.
     */
    @Getter(lazy = true)
    private FactionBuilder _builder = new FactionBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "factions";
}
