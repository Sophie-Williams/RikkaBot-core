package com.rikkabot.rikkabotcore.dao.ships;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.dao.Builder;
import com.rikkabot.rikkabotcore.dao.rewards.RewardBuilder;
import org.json.JSONObject;

/**
 * Ship builder.
 * =============
 *
 * Builds `Ship` objects.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShipBuilder implements Builder<Ship>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Ship build(JSONObject json)
    {
        Ship ship = null;

        try {
            ship = new Ship(
                    json.getInt("id"),
                    json.getInt("items_id"),
                    json.getInt("health"),
                    json.getInt("speed"),
                    json.getInt("cargo"),
                    json.getInt("batteries"),
                    json.getInt("rockets"),
                    json.getInt("lasers"),
                    json.getInt("hellstorms"),
                    json.getInt("generators"),
                    json.getInt("extras"),
                    RewardBuilder.build(json.getJSONArray("rewards")),
                    json.getInt("gfx")
            );
        } catch(Exception e) {
            Console.println("Couldn't build ship for `"+ json.toString() +"`!");
            Console.debug(e.getMessage());
        }

        return ship;
    }
}
