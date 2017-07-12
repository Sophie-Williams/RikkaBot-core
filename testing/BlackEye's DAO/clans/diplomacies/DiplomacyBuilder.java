package com.rikkabot.rikkabotcore.dao.clans.diplomacies;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * Diplomacy builder.
 * ==================
 *
 * Builds `Diplomacy` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class DiplomacyBuilder implements Builder<Diplomacy>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Diplomacy build(JSONObject json)
    {
        Diplomacy diplomacy = null;

        try {
            diplomacy = new Diplomacy(
                    json.getInt("id"),
                    json.getInt("from_clans_id"),
                    json.getInt("to_clans_id"),
                    json.getInt("type")
            );
        } catch(Exception e) {
            Console.println("Couldn't build diplomacy for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return diplomacy;
    }
}
