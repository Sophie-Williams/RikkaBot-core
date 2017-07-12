package com.rikkabot.rikkabotcore.dao.galaxygates;

import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.Builder;
import com.rikkabot.rikkabotcore.dao.rewards.RewardBuilder;

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
                    json.getInt("galaxygates_waves_id"),
                    json.getInt("parts"),
                    RewardBuilder.build(json.getJSONArray("rewards"))
            );
        } catch(Exception e) {
            Console.println("Couldn't build galaxygate for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return gg;
    }
}
