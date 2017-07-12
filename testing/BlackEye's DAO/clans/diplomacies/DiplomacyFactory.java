package com.rikkabot.rikkabotcore.dao.clans.diplomacies;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.Factory;

/**
 * Diplomacy factory.
 * ==================
 *
 * Factory for the `clans_diplomacy` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Value @EqualsAndHashCode(callSuper = true)
public class DiplomacyFactory extends Factory<Diplomacy>
{
    /**
     * Diplomacy builder.
     */
    @Getter(lazy = true)
    private DiplomacyBuilder _builder = new DiplomacyBuilder();

    /**
     * API endpoint.
     */
    @Getter(lazy = true)
    private String _endpoint = "clans_diplomacy";

    /**
     * Returns all the diplomacies of a given clan.
     *
     * @param id Clan ID.
     *
     * @return Diplomacies from/to clan ID.
     */
    public Map<Integer, Diplomacy> byClanID(int id)
    {
        Map<Integer, Diplomacy> diplomacies = new HashMap<>();

        Map<String, String> params = new HashMap<>();
        params.put("from_clans_id", id +"");

        diplomacies.putAll(super.where(params));

        params.remove("from_clans_id");
        params.put("to_clans_id", id +"");

        diplomacies.putAll(super.where(params));

        return diplomacies;
    }
}
