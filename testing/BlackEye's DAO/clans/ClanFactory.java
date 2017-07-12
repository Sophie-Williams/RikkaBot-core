package com.rikkabot.rikkabotcore.dao.clans;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;
import com.rikkabot.rikkabotcore.dao.clans.diplomacies.DiplomacyFactory;

/**
 * Clan factory.
 * =============
 *
 * Factory for the `clans` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class ClanFactory extends Factory<Clan>
{
    /**
     * Diplomacy factory.
     */
    @Getter(lazy = true)
    private DiplomacyFactory _diplomacies = new DiplomacyFactory();

    /**
     * Clan builder.
     */
    @Getter(lazy = true)
    private ClanBuilder _builder = new ClanBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "clans";
}
