package com.rikkabot.rikkabotcore.dao.accounts.galaxygates;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;

/**
 * GalaxyGate builder.
 * ===================
 *
 * Builds `GalaxyGate` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class GalaxyGateBuilder implements Builder<GalaxyGate>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public GalaxyGate build(JSONObject json)
    {
        GalaxyGate gg = null;

        try {
            gg = new GalaxyGate(
                    json.getInt("id"),
                    json.getInt("galaxygates_id"),
                    json.getInt("parts"),
                    json.getInt("lifes"),
                    json.getInt("wave"),
                    json.getInt("times"),
                    (json.getInt("is_completed") == 1)
            );
        } catch(Exception e) {
            Console.println("Couldn't build galaxygate for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return gg;
    }
}
